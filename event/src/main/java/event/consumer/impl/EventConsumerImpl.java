package event.consumer.impl;

import event.consumer.EventConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class EventConsumerImpl implements EventConsumer {

  volatile KafkaConsumer<String, String> kafkaConsumer;

  public static void main(String[] args) {
    EventConsumer eventConsumer = new EventConsumerImpl();
    eventConsumer.subscribe("first");
  }

  @Override
  public Boolean subscribe(String topic) {
    getKafkaConsumer().subscribe(Stream.of(topic).collect(Collectors.toList()));
    processEvents(getKafkaConsumer());
    return true;
  }

  protected void processEvents(KafkaConsumer<String, String> kafkaConsumer) {
    new Thread() {
      @Override
      public void run() {
        while (true) {
          ConsumerRecords<String, String> records = getKafkaConsumer().poll(100);
          for (ConsumerRecord<String, String> record : records) {
            System.out.printf("offset = %d, key = %s, value = %s%n",
                record.offset(), record.key(), record.value());
          }
        }
      }
    }.start();
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
    props.put("bootstrap.servers", "localhost:9091");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("group.id", "group1");
    return new KafkaConsumer<String, String>(props);
  }

}
