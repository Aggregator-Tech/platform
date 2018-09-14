package platform.data.provider.impl;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.glassfish.hk2.api.Factory;
import org.jvnet.hk2.annotations.Service;
import platform.common.Constants;

@Service
public class RedisClientFactory implements Factory<RedisClient> {
  @Override
  public RedisClient provide() {
    return buildRedisClient();
  }

  private RedisClient buildRedisClient() {
    RedisClient redisClient =
        RedisClient.create(
            RedisURI.Builder
                .redis(Constants.SERVICE_REDIS, 6379)
                .withDatabase(0).build());
//                .redis("redis-17291.c1.us-west-2-2.ec2.cloud.redislabs.com", 17291)
//          .withDatabase(0).withPassword("jkyMnnc0ZxKMXKCzf2ieilsTrqbwIkIZ").build());

    return redisClient;
  }

  @Override
  public void dispose(RedisClient instance) {

  }
}
