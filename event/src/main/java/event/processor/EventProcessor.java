package event.processor;

import org.apache.kafka.clients.consumer.ConsumerRecord;

@FunctionalInterface
public interface EventProcessor {
    public void process(ConsumerRecord<String, String> consumerRecord);
}
