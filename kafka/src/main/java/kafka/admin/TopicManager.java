package kafka.admin;


import java.util.Set;

public interface TopicManager {

  Boolean createTopic(String topic, int partitions, short replication);

  Boolean deleteTopic(String topic);

  Set<String> getTopics();

  String getKafkaBootstrapServer();

  void setKafkaBootstrapServer(String kafkaBootstrapServer);
}