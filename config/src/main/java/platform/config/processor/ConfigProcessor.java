package platform.config.processor;

import org.jvnet.hk2.annotations.Contract;
import org.jvnet.hk2.annotations.Service;

import static platform.config.ConfigConstants.PROPERTY_KAFKA_URL;

@Contract
public interface ConfigProcessor {

  public void process(String propertyId, String propertyValue);
}
