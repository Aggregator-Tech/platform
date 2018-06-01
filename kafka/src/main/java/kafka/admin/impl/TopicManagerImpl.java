package kafka.admin.impl;


import kafka.admin.TopicManager;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TopicManagerImpl implements TopicManager {

  volatile AdminClient adminClient;

  public static void main(String[] args) {
    TopicManagerImpl topicManager = new TopicManagerImpl();
    System.out.println("Getting list of topics...");
    System.out.println("List of topics: " + topicManager.getTopics());
//    topicManager.createTopic("second",1 , (short)1);
//    System.out.println("List of topics: " + topicManager.getTopics());
  }
  @Override
  public Boolean createTopic(String topic, int partitions, short replication) {
    Map<String, String> configs = new HashMap<>();
    getKafkaAdminClient().createTopics(Stream.of(new NewTopic(topic, partitions, replication).configs(configs)).collect(Collectors.toList()));
    return null;
  }

  @Override
  public Set<String> getTopics() {
    ListTopicsResult listTopicsResult = getKafkaAdminClient().listTopics();
    try {
      return listTopicsResult.names().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return null;
  }

  protected AdminClient getKafkaAdminClient() {
    if (adminClient == null) {
      synchronized (this) {
        if (adminClient == null) {
          adminClient = buildKafkaAdminClient();
        }
      }
    }
    return adminClient;

  }

  private AdminClient buildKafkaAdminClient() {
    Properties config  = new Properties();
    config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    AdminClient adminClient = AdminClient.create(config);
    return adminClient;
  }
}
