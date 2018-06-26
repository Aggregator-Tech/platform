package platform.data.provider.impl;

import org.jvnet.hk2.annotations.Service;
import platform.data.constant.DataRepositoryType;
import platform.data.provider.KeyValueProvider;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import javax.inject.Inject;

@Service(name = DataRepositoryType.REDIS)
public class RedisKeyValueProvider implements KeyValueProvider {
  RedisClient redisClient;
  StatefulRedisConnection<String, String> statefulRedisConnection;

  @Inject
  public RedisKeyValueProvider(RedisClient redisClient) {
    this.redisClient = redisClient;
    statefulRedisConnection = redisClient.connect();
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
    RedisCommands<String, String> syncCommands = statefulRedisConnection.sync();
    return syncCommands;
  }
}
