package event.producer;

import org.apache.kafka.clients.producer.KafkaProducer;

public interface EventProducer {
  Boolean publish(String topic, String eventPayload);

  KafkaProducer<String, String> getKafkaProducer();
}
