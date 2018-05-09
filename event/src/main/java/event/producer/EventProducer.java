package event.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class EventProducer {

    public Boolean publish(String eventPayload) {
        KafkaProducer<String, String> producer = buildKafkaProducer();
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("first", eventPayload, eventPayload);
        producer.send(producerRecord);
        return Boolean.TRUE;
    }


    public KafkaProducer<String,String> buildKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9091");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<String, String>(props);
    }
}
