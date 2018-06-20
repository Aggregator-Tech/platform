package platform.data.provider

import org.glassfish.hk2.api.ServiceLocator
import platform.common.ServiceLocatorHelper
import platform.common.io.system.SystemHelper
import platform.common.test.BaseSpecification
import platform.data.DataConfigProperty
import platform.data.constant.DataRepositoryType
import spock.lang.Shared

class RedisKeyValueProviderFactoryTest extends BaseSpecification{

    def setupSpec() {
        println "setup RedisKeyValueProviderFactoryTest";
    }
    def "Verify Set and Get entry in Redis" () {
        setup:
        String key = "key1";
        String value = "value1";
        SystemHelper systemHelper = serviceLocator.getService(SystemHelper.class);
        systemHelper.writeConfigurationProperty(DataConfigProperty.DATA_REPOSITORY_TYPE, DataRepositoryType.REDIS);
        KeyValueProviderFactory redisKeyValueProviderFactory = serviceLocator.getService(KeyValueProviderFactory.class)
        KeyValueProvider keyValueProvider = redisKeyValueProviderFactory.getInstance();

        when:
        keyValueProvider.setString(key, value);
        String returnValue = keyValueProvider.getString(key);

        then:
        returnValue == value
    }
}
