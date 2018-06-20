package platform.data.provider.impl;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import platform.common.io.system.SystemHelper;
import platform.data.DataConfigProperty;
import platform.data.provider.KeyValueProviderFactory;

import javax.inject.Inject;

import static platform.data.constant.DataRepositoryType.REDIS;

@Service
public class KeyValueProviderFactoryFactory implements Factory<KeyValueProviderFactory> {
  private SystemHelper systemHelper;
  private ServiceLocator serviceLocator;

  @Inject
  public KeyValueProviderFactoryFactory(SystemHelper systemHelper,
                                        ServiceLocator serviceLocator) {
    this.systemHelper = systemHelper;
    this.serviceLocator = serviceLocator;
  }

  @Override
  public KeyValueProviderFactory provide() {
    //TODO read defautl value from resource file ( User guava api to read resource file)
    String dataRepositoryType =
        systemHelper.readConfigurationProperty(
            DataConfigProperty.DATA_REPOSITORY_TYPE, REDIS).get();
    KeyValueProviderFactory keyValueProviderFactory =
        serviceLocator.getService(KeyValueProviderFactory.class, dataRepositoryType);
    return keyValueProviderFactory;
  }

  @Override
  public void dispose(KeyValueProviderFactory instance) {

  }
}
