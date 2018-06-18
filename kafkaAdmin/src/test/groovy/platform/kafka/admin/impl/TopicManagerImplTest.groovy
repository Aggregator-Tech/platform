package platform.kafka.admin.impl

import platform.kafka.admin.TopicManager
import spock.lang.Shared
import spock.lang.Specification

class TopicManagerImplTest extends Specification{

    @Shared
    String topic = "testTopic1";
    def "Test getTopics" () {
        setup:
        TopicManager topicManager = new TopicManagerImpl();

    }
}
