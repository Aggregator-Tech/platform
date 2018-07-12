package platform.kafka.admin.impl;


import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;
import platform.kafka.admin.TopicManager;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@PerLookup
class TopicManagerImpl implements TopicManager {

  volatile AdminClient adminClient;
  private String kafkaBootstrapServer;

  public static void main(String[] args) {
    TopicManagerImpl topicManager = new TopicManagerImpl();
    topicManager.setKafkaBootstrapServer("http://slc12nog:9092");
    System.out.println("Getting list of topics...");
    System.out.println("List of topics: " + topicManager.getTopics());
//    topicManager.createTopic("testTopic1",1 , (short)1);
//    topicManager.deleteTopic("second");
//    System.out.println("List of topics: " + topicManager.getTopics());
  }

  @Override
  public Boolean createTopic(String topic, int partitions, short replication) {
    Map<String, String> configs = new HashMap<>();
    getKafkaAdminClient().createTopics(
        Stream.of(new NewTopic(topic, partitions, replication)
            .configs(configs)).collect(Collectors.toList()));
    return true;
  }

  @Override
  public Boolean deleteTopic(String topic) {
    getKafkaAdminClient().deleteTopics(Stream.of(topic).collect(Collectors.toList()));
    return true;
  }

  @Override
  public Set<String> getTopics() {
    ListTopicsResult listTopicsResult = getKafkaAdminClient().listTopics();
    try {
      return listTopicsResult.names().get(5, TimeUnit.SECONDS);
    } catch (TimeoutException e) {
      e.printStackTrace();
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

  protected AdminClient buildKafkaAdminClient() {
    Properties config = new Properties();
    config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBootstrapServer());
    AdminClient adminClient = AdminClient.create(config);
    return adminClient;
  }

  @Override
  public String getKafkaBootstrapServer() {
    return kafkaBootstrapServer;
  }

  @Override
  public void setKafkaBootstrapServer(String kafkaBootstrapServer) {
    this.kafkaBootstrapServer = kafkaBootstrapServer;
  }

}
