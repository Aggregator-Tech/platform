package platform.kafka.admin.impl;

import org.glassfish.hk2.api.PostConstruct;
import org.glassfish.hk2.runlevel.RunLevel;
import org.jvnet.hk2.annotations.Service;
import platform.kafka.admin.TopicManager;

import javax.inject.Inject;

@Service
@RunLevel(5)
public class KafkaAdminInitializer implements PostConstruct {

  private final TopicManager topicManager;

  @Inject
  public KafkaAdminInitializer(TopicManager topicManager) {
    this.topicManager = topicManager;
  }

  @Override
  public void postConstruct() {
    System.out.println("Inside KafkaAdminInitializer: topicManager = " + topicManager);
  }
}
