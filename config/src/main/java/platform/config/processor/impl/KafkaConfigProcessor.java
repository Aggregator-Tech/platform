package platform.config.processor.impl;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import platform.common.io.log.Log;
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
  private final Log log;

  @Inject
  public KafkaConfigProcessor(ServiceLocator serviceLocator, TopicManager topicManager) {
    this.serviceLocator = serviceLocator;
    this.topicManager = topicManager;
    log = serviceLocator.getService(Log.class);
  }

  @Override
  public void process(String propertyId, String propertyValue) {
    new Thread(() -> {
      String kafkaUrl = serviceLocator.getService(Config.class).getConfig(PROPERTY_KAFKA_URL);
      topicManager.setKafkaBootstrapServer(kafkaUrl);
      //TODO Make createTopic parameters dynamic
      topicManager.createTopic(TOPIC_CONFIG_EVENTS, 1, (short) 1);
      log.info("Created topic: " + TOPIC_CONFIG_EVENTS);
      log.info("List of topics: " + topicManager.getTopics());
    }).start();
  }
}
