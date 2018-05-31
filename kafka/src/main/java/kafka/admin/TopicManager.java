package kafka.admin;


import java.util.Set;

public interface TopicManager {
  Boolean createTopic(String topic, int partitions, short replication);

  Set<String> getTopics();
}