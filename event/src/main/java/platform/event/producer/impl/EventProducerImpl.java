package platform.event.producer.impl;

import platform.event.producer.EventProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class EventProducerImpl implements EventProducer {

  volatile KafkaProducer<String, String> kafkaProducer;

  public static void main(String[] args) {
    EventProducer eventProducer = new EventProducerImpl();
    eventProducer.publish("second", "event1");
  }

  @Override
  public Boolean publish(String topic, String eventPayload) {
    ProducerRecord<String, String> producerRecord =
        new ProducerRecord<String, String>(topic, eventPayload, eventPayload);
    getKafkaProducer().send(producerRecord);
    getKafkaProducer().close();
    return Boolean.TRUE;
  }


  @Override
  public KafkaProducer<String, String> getKafkaProducer() {
    if (kafkaProducer == null) {
      synchronized (this) {
        if (kafkaProducer == null) {
          kafkaProducer = buildKafkaProducer();
        }
      }
    }
    return kafkaProducer;
  }

  protected KafkaProducer<String, String> buildKafkaProducer() {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    return new KafkaProducer<String, String>(props);
  }
}
