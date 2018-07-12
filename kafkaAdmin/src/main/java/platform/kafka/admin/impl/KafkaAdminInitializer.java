package platform.kafka.admin.impl;

import org.glassfish.hk2.api.PostConstruct;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.runlevel.RunLevel;
import org.jvnet.hk2.annotations.Service;
import platform.common.io.log.Log;
import platform.kafka.admin.TopicManager;

import javax.inject.Inject;

@Service
@RunLevel(5)
public class KafkaAdminInitializer implements PostConstruct {

  private final TopicManager topicManager;
  private final Log log;

  @Inject
  public KafkaAdminInitializer(ServiceLocator serviceLocator, TopicManager topicManager) {
    this.topicManager = topicManager;
    this.log = serviceLocator.getService(Log.class);
  }

  @Override
  public void postConstruct() {
    log.info("Inside KafkaAdminInitializer: topicManager = " + topicManager);
  }
}
