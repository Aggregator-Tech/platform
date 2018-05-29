package event.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public interface EventConsumer {
  Boolean subscribe(String topic);

  KafkaConsumer<String, String> getKafkaConsumer();
}