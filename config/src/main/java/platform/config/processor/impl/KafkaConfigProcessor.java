package platform.config.processor.impl;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import platform.config.processor.ConfigProcessor;
import platform.config.resource.Config;
import platform.kafka.admin.TopicManager;

import javax.inject.Inject;

import static platform.config.ConfigConstants.PROPERTY_KAFKA_URL;
import static platform.config.ConfigConstants.TOPIC_CONFIG_EVENTS;

@Service(name = PROPERTY_KAFKA_URL)
public class KafkaConfigProcessor implements ConfigProcessor {
  private final TopicManager topicManager;
  private final ServiceLocator serviceLocator;

  @Inject
  public KafkaConfigProcessor(ServiceLocator serviceLocator, TopicManager topicManager) {
    this.serviceLocator = serviceLocator;
    this.topicManager = topicManager;
  }

  @Override
  public void process(String propertyId, String propertyValue) {
    new Thread(() -> {
      String kafkaUrl = serviceLocator.getService(Config.class).getConfig(PROPERTY_KAFKA_URL);
      topicManager.setKafkaBootstrapServer(kafkaUrl);
      //TODO Make createTopic parameters dynamic
      System.out.println("List of topics: " + topicManager.getTopics());
      topicManager.createTopic(TOPIC_CONFIG_EVENTS, 1, (short) 1);
      System.out.println("Created topic: " + TOPIC_CONFIG_EVENTS);
    }).start();
  }
}
