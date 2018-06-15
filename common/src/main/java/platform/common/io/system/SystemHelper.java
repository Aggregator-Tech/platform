package platform.common.io.system;

import org.jvnet.hk2.annotations.Service;
import platform.common.ConfigProperty;

import java.util.Optional;

@Service
public class SystemHelper {

  /**
   * Read the value of configuration property with the following precedence:
   * 1. Environment variable.
   * 2. Java system property.
   *
 * @param configProperty The configuration property name.
   */
  public Optional<String> readConfigurationProperty(ConfigProperty configProperty) {
    return readConfigurationProperty(configProperty, null);
  }

  /**
   * Read the value of configuration property with the following precedence:
   * 1. Environment variable.
   * 2. Java system property.
   *
   * @param configProperty The configuration property name.
   * @param defaultValue The default value if configuration property not specified.
   */
  public Optional<String> readConfigurationProperty(ConfigProperty configProperty,
                                                    String defaultValue) {
    String propertyName = configProperty.toString();
    String propertyValue;
    propertyValue = System.getenv(propertyName);
    if (propertyValue == null) {
      propertyValue = System.getProperty(propertyName);
    }
    if (propertyValue == null) {
      propertyValue = defaultValue;
    }
    return Optional.ofNullable(propertyValue);
  }

}
