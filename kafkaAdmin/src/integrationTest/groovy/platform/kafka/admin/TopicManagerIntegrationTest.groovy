package platform.kafka.admin

import platform.common.io.system.SystemHelper
import platform.kafka.admin.impl.TopicManagerImpl
import spock.lang.Specification

class TopicManagerIntegrationTest extends Specification{
    def "test getTopics" () {
        setup:
        TopicManager topicManager = new TopicManagerImpl();
        when:
        String kafkaBootstrapServer = new SystemHelper().readConfigurationProperty(KafkaConfigProperty.KAFKA_BOOTSTRAP_SERVER, "localhost:9092").get();
        topicManager.setKafkaBootstrapServer(kafkaBootstrapServer)
        Set<String> topics = topicManager.getTopics();
        then:
        assert topics != null
    }
}
