package event.consumer.impl;

import event.consumer.EventConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.stream.Collectors;
import java.util.stream.Stream;

class EventConsumerImpl implements EventConsumer {
  @Override
  public Boolean subscribe(String topic) {
    getKafkaConsumer().subscribe(Stream.of(topic).collect(Collectors.toList()));
    return true;
  }

  @Override
  public KafkaConsumer<String, String> getKafkaConsumer() {
    return null;
  }
}
