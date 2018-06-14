package platform.data.provider

import platform.data.provider.KeyValueProvider
import platform.data.provider.impl.RedisKeyValueProvider
import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import spock.lang.Specification

class RedisKeyValueProviderTest extends Specification{
    def "Test getString"() {
        setup:
        String key = "key1"
        String value = "value1"
        RedisClient mockRedisClient = Mock();
        StatefulRedisConnection<String, String> mockStatefulRedisConnection = Mock()
        mockRedisClient.connect() >> mockStatefulRedisConnection
        RedisCommands<String, String> mockSyncCommands = Mock()
        mockStatefulRedisConnection.sync() >> mockSyncCommands
        mockSyncCommands.get(key) >> value;
        KeyValueProvider keyValueProvider = new RedisKeyValueProvider(mockRedisClient);

        when:
        String returnValue = keyValueProvider.getString(key);

        then:
        returnValue == value
    }
}
