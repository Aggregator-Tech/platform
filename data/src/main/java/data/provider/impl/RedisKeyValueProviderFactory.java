package data.provider.impl;

import data.provider.KeyValueProvider;
import data.provider.KeyValueProviderFactory;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

public class RedisKeyValueProviderFactory implements KeyValueProviderFactory {

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
    volatile private static RedisClient redisClient;

    private RedisClient getRedisClient() {
        if(redisClient == null) {
            synchronized (this) {
                if(redisClient == null) {
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
                                .redis("redis-17291.c1.us-west-2-2.ec2.cloud.redislabs.com",17291)
                                .withDatabase(0).withPassword("jkyMnnc0ZxKMXKCzf2ieilsTrqbwIkIZ").build());

        return redisClient;
    }

    @Override
    public KeyValueProvider getInstance() {
        return new RedisKeyValueProvider(getRedisClient());
    }


}
