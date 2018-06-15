package platform.common.io.system;

import org.jvnet.hk2.annotations.Service;
import platform.common.ConfigProperty;

import java.util.Optional;

@Service
public class SystemHelper {

  public Optional<String> readConfigurationProperty(ConfigProperty configProperty) {
    String propertyName = configProperty.toString();
    String propertyValue;
    propertyValue = System.getenv(propertyName);
    if (propertyValue == null || propertyValue.isEmpty()) {
      propertyValue = System.getProperty(propertyName);
    }
    return Optional.ofNullable(propertyValue);
  }

}
