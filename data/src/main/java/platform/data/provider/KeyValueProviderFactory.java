package platform.data.provider;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface KeyValueProviderFactory {
  public KeyValueProvider getInstance();
}
