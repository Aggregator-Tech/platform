package platform.data.provider.impl;

import platform.data.provider.KeyValueProvider;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisKeyValueProvider implements KeyValueProvider {
  RedisClient redisClient;

  public RedisKeyValueProvider(RedisClient redisClient) {
    this.redisClient = redisClient;
  }

  @Override
  public String setString(String key, String value) {
    getSyncCommands().set(key, value);
    return null;
  }

  @Override
  public String getString(String key) {
    String value = getSyncCommands().get(key);
    return value;
  }

  private RedisCommands<String, String> getSyncCommands() {
    StatefulRedisConnection<String, String> statefulRedisConnection =
        redisClient.connect();
    RedisCommands<String, String> syncCommands = statefulRedisConnection.sync();
    return syncCommands;
  }
}
