package platform.common.io.system;

import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ClasspathDescriptorFileFinder;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jvnet.hk2.annotations.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class SystemHelper {

  public Optional<String> readConfigurationProperty(String propertyName) {
    String propertyValue = System.getenv(propertyName);
    if (propertyValue == null || propertyValue.isEmpty()) {
      propertyValue = System.getProperty(propertyName);
    }
    return Optional.ofNullable(propertyValue);
  }

  public static Optional<String> getProperty(String propertyName) {
    ServiceLocator serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
    ServiceLocatorUtilities.bind(serviceLocator, new AbstractBinder() {
      @Override
      protected void configure() {
        bind(SystemHelper.class).to(SystemHelper.class);
      }
    });
//    DynamicConfigurationService dynamicConfigurationService = serviceLocator.getService(DynamicConfigurationService.class);
//    try {
//      dynamicConfigurationService.getPopulator().populate(new ClasspathDescriptorFileFinder(SystemHelper.class.getClassLoader()));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    SystemHelper systemHelper = serviceLocator.getService(SystemHelper.class);
    return systemHelper.readConfigurationProperty(propertyName);
  }
}
