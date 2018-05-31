package event.consumer.impl;

import event.consumer.EventConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class EventConsumerImpl implements EventConsumer {

  volatile KafkaConsumer<String, String> kafkaConsumer;

  public static void main(String[] args) {
    EventConsumer eventConsumer = new EventConsumerImpl();
    eventConsumer.subscribe("second");
  }

  @Override
  public Boolean subscribe(String topic) {
    getKafkaConsumer().subscribe(Stream.of(topic).collect(Collectors.toList()));
    processEvents(getKafkaConsumer());
    return true;
  }

  protected void processEvents(KafkaConsumer<String, String> kafkaConsumer) {
    EventProcessor eventProcessor = new EventProcessor();
    new Thread(new EventProcessor()).start();
    Runtime.getRuntime().addShutdownHook(new Thread(eventProcessor::shutdown));
  }

  public class EventProcessor implements Runnable {
    private final AtomicBoolean closed = new AtomicBoolean(false);

    public void run() {
      try {
        while (!closed.get()) {
          ConsumerRecords<String, String> records = getKafkaConsumer().poll(100);
          for (ConsumerRecord<String, String> record : records) {
            System.out.printf("offset = %d, key = %s, value = %s%n",
                record.offset(), record.key(), record.value());
          }
        }
      } catch (WakeupException e) {
        // Ignore exception if closing
        if (!closed.get()) {
          throw e;
        }
      } finally {
        getKafkaConsumer().close();
      }
    }

    // Shutdown hook which can be called from a separate thread
    public void shutdown() {
      closed.set(true);
      getKafkaConsumer().wakeup();
    }
  }

  @Override
  public KafkaConsumer<String, String> getKafkaConsumer() {
    if (kafkaConsumer == null) {
      synchronized (this) {
        if (kafkaConsumer == null) {
          kafkaConsumer = buildKafkaConsumer();
        }
      }
    }
    return kafkaConsumer;
  }

  protected KafkaConsumer<String, String> buildKafkaConsumer() {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("group.id", "group1");
    return new KafkaConsumer<String, String>(props);
  }

}
