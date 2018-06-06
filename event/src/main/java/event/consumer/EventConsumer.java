package event.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public interface EventConsumer {
  Boolean subscribe(String topic);

  Boolean unSubscribe();

  KafkaConsumer<String, String> getKafkaConsumer();
}