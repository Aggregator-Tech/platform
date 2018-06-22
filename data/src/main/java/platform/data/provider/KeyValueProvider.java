package platform.data.provider;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface KeyValueProvider {

  String setString(String key, String value);

  String getString(String key);
}
