package platform.kafka.admin;


import org.jvnet.hk2.annotations.Contract;

import java.util.Set;

@Contract
public interface TopicManager {

  Boolean createTopic(String topic, int partitions, short replication);

  Boolean deleteTopic(String topic);

  Set<String> getTopics();

  String getKafkaBootstrapServer();

  void setKafkaBootstrapServer(String kafkaBootstrapServer);
}