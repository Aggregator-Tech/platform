import org.glassfish.hk2.api.ServiceLocator
import platform.common.io.log.Log
import platform.common.test.BaseSpecification
import platform.config.ConfigConstants
import platform.config.processor.ConfigProcessor
import platform.config.resource.Config
import platform.data.provider.KeyValueProvider

class ConfigTest extends BaseSpecification{
    def "Get Config"() {
        setup:
        String configId = ConfigConstants.PROPERTY_KAFKA_URL;
        String mockConfigValue=  "kafkaHost:9092"
        KeyValueProvider mockKeyValueProvider = Mock()
        ServiceLocator mockServiceLocator = Mock()
        mockKeyValueProvider.getString(configId) >> mockConfigValue
        Log mockLog = Mock()
        Config config = new Config(mockServiceLocator, mockKeyValueProvider, mockLog);

        when:
        String configValue = config.getConfig(configId);

        then:
        configValue == mockConfigValue;
    }

    def "Set Config"() {
        setup:
        String configId = ConfigConstants.PROPERTY_KAFKA_URL;
        String configValue=  "kafkaHost:9092"
        KeyValueProvider mockKeyValueProvider = Mock()
        ServiceLocator mockServiceLocator = Mock()
        ConfigProcessor mockConfigProcessor = Mock()
        mockServiceLocator.getService(ConfigProcessor.class, ConfigConstants.PROPERTY_KAFKA_URL) >> mockConfigProcessor
        Log mockLog = Mock()
        Config config = new Config(mockServiceLocator, mockKeyValueProvider, mockLog);

        when:
        config.setConfig(configId, configValue);

        then:
        1 * mockKeyValueProvider.setString(configId, configValue)
        1 * mockConfigProcessor.process(configId, configValue)
    }
}
