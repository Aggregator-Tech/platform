package event.producer

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import spock.lang.Shared
import spock.lang.Specification

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo

class EventProducerTest extends Specification {
    @Shared
    String eventPayload = "payload"

    def 'Test Publish Event'() {
        setup:
        KafkaProducer<String,String> mockKafkaProducer = Mock()
        EventProducer spyEventProducer = Spy(EventProducer)

        when:
        spyEventProducer.buildKafkaProducer() >> mockKafkaProducer
        Boolean result = spyEventProducer.publish(eventPayload)

        then:
        1 * mockKafkaProducer.send({it.key ==  eventPayload && it.value == eventPayload})
        assertThat(result, equalTo(Boolean.TRUE))
    }

    def 'Test buildKafkaProducer'() {
        setup:
        EventProducer eventProducer = new EventProducer()

        when:
        KafkaProducer<String, String> kafkaProducer = eventProducer.buildKafkaProducer()

        then:
        kafkaProducer != null
    }
}
