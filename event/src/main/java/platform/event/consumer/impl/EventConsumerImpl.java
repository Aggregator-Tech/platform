package platform.event.consumer.impl;

import platform.event.consumer.EventConsumer;
import platform.event.processor.EventProcessor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class EventConsumerImpl implements EventConsumer {

  private final EventProcessor eventProcessor;
  volatile KafkaConsumer<String, String> kafkaConsumer;
  private EventDispatcher eventDispatcher;

  public static void main(String[] args) {
    EventConsumer eventConsumer =
            new EventConsumerImpl(record ->
                    System.out.printf("offset = %d, key = %s, value = %s%n",
                        record.offset(), record.key(), record.value()));
    eventConsumer.subscribe("second");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    eventConsumer.unSubscribe();

  }
  EventConsumerImpl(EventProcessor eventProcessor) {
    this.eventProcessor = eventProcessor;
  }

  @Override
  public Boolean subscribe(String topic) {
    getKafkaConsumer().subscribe(Stream.of(topic).collect(Collectors.toList()));
    startEventDispatcher(getKafkaConsumer());
    return true;
  }

  @Override
  public Boolean unSubscribe() {
    buildEventDispatcherStopper().start();
    return true;
  }

  protected void startEventDispatcher(KafkaConsumer<String, String> kafkaConsumer) {
    eventDispatcher = new EventDispatcher();
    new Thread(eventDispatcher).start();
    Runtime.getRuntime().addShutdownHook(buildEventDispatcherStopper());
  }

  private Thread buildEventDispatcherStopper() {
    return new Thread(eventDispatcher::shutdown);
  }

  public class EventDispatcher implements Runnable {
    private final AtomicBoolean closed = new AtomicBoolean(false);

    public void run() {
      try {
        while (!closed.get()) {
          ConsumerRecords<String, String> records = getKafkaConsumer().poll(100);
          for (ConsumerRecord<String, String> record : records) {
            eventProcessor.process(record);
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
