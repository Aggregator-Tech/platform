package platform.data.provider

import platform.data.provider.impl.RedisKeyValueProviderFactory
import spock.lang.Specification

class RedisKeyValueProviderFactoryTest extends Specification{

    def "Verify Set and Get entry in Redis" () {
        setup:
        String key = "key1";
        String value = "value1";
        RedisKeyValueProviderFactory redisKeyValueProviderFactory =
                new RedisKeyValueProviderFactory();
        KeyValueProvider keyValueProvider = redisKeyValueProviderFactory.getInstance();

        when:
        keyValueProvider.setString(key, value);
        String returnValue = keyValueProvider.getString(key);

        then:
        returnValue == value
    }
}
