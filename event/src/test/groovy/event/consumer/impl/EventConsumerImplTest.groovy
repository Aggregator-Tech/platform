package event.consumer.impl

import event.consumer.EventConsumer
import org.apache.kafka.clients.consumer.KafkaConsumer
import spock.lang.Shared
import spock.lang.Specification

class EventConsumerImplTest extends Specification{

    @Shared
    String topic = "testTopic1";
    def "Test Subscribe Event" () {
        setup:
        EventConsumer eventConsumer = Spy(EventConsumerImpl);
        KafkaConsumer<String, String> mockKafkaConsumer = Mock()
        eventConsumer.getKafkaConsumer() >> mockKafkaConsumer
        eventConsumer.processEvents(_) >> null

        when:
        Boolean response = eventConsumer.subscribe(topic)

        then:
        response == Boolean.TRUE
        1 * mockKafkaConsumer.subscribe({((Collection)it).contains(topic)})
    }
}
