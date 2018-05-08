package event.producer

import spock.lang.Specification

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo

class EventProducerTest extends Specification {
    def 'Test Publish Event()'() {
        setup:
        EventProducer eventProducer = new EventProducer()

        when:
        String eventPayload = "payload"
        Boolean result = eventProducer.publish(eventPayload)

        then:
        assertThat(result, equalTo(Boolean.TRUE))
    }
}
