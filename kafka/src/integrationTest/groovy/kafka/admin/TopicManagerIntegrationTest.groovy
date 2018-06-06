package kafka.admin

import kafka.admin.impl.TopicManagerImpl
import spock.lang.Specification

class TopicManagerIntegrationTest extends Specification{
    def "test getTopics" () {
        setup:
        TopicManager topicManager = new TopicManagerImpl();
        when:
        Set<String> topics = topicManager.getTopics();
        then:
        assert topics != null
    }
}
