package event.producer.impl

import event.producer.EventProducer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import spock.lang.Shared
import spock.lang.Specification

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo

class EventProducerImplTest extends Specification {
    @Shared
    String topic = "topic"

    @Shared
    String eventPayload = "eventPayload"

    def 'Test Publish Event'() {
        setup:
        KafkaProducer<String, String> mockKafkaProducer = Mock()
        EventProducer spyEventProducer = Spy(EventProducerImpl)

        when:
        spyEventProducer.getKafkaProducer() >> mockKafkaProducer
        Boolean result = spyEventProducer.publish(topic, eventPayload)

        then:
        1 * mockKafkaProducer.send({
            ((ProducerRecord) it).topic == topic && it.key == eventPayload && it.value == eventPayload
        })
        assertThat(result, equalTo(Boolean.TRUE))
    }

    def 'Test buildKafkaProducer'() {
        setup:
        EventProducerImpl eventProducer = new EventProducerImpl()

        when:
        KafkaProducer<String, String> kafkaProducer = eventProducer.buildKafkaProducer()

        then:
        kafkaProducer != null
    }
}
