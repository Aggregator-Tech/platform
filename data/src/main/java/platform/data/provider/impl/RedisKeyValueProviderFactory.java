package platform.data.provider.impl;

import org.jvnet.hk2.annotations.Service;
import platform.data.constant.DataRepositoryType;
import platform.data.provider.KeyValueProvider;
import platform.data.provider.KeyValueProviderFactory;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;


@Service(name = DataRepositoryType.REDIS)
public class RedisKeyValueProviderFactory implements KeyValueProviderFactory {

  /**
   * Test Client.
   */
  public static void main(String[] args) {
    String key = "key1";
    String value = "value1";
    RedisKeyValueProviderFactory redisKeyValueProviderFactory =
        new RedisKeyValueProviderFactory();
    KeyValueProvider keyValueProvider = redisKeyValueProviderFactory.getInstance();
    keyValueProvider.setString(key, value);
    String returnValue = keyValueProvider.getString(key);
    System.out.println("returnValue = " + returnValue);

  }

  private static volatile RedisClient redisClient;

  private RedisClient getRedisClient() {
    if (redisClient == null) {
      synchronized (this) {
        if (redisClient == null) {
          redisClient = buildRedisClient();
        }
      }
    }
    return redisClient;
  }

  private RedisClient buildRedisClient() {
    RedisClient redisClient =
        RedisClient.create(
            RedisURI.Builder
                .redis("localhost", 6379)
                .withDatabase(0).build());
//                .redis("redis-17291.c1.us-west-2-2.ec2.cloud.redislabs.com", 17291)
//          .withDatabase(0).withPassword("jkyMnnc0ZxKMXKCzf2ieilsTrqbwIkIZ").build());

    return redisClient;
  }

  @Override
  public KeyValueProvider getInstance() {
    return new RedisKeyValueProvider(getRedisClient());
  }


}
