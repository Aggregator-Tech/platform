import platform.common.test.BaseSpecification
import platform.config.resource.Config
import platform.data.provider.KeyValueProvider

class ConfigTest extends BaseSpecification{
    def "Get Config"() {
        setup:
        String configId = "kafkaUrl";
        String mockConfigValue=  "kafkaHost:9092"
        KeyValueProvider mockKeyValueProvider = Mock()
        mockKeyValueProvider.getString(configId) >> mockConfigValue
        Config config = new Config(mockKeyValueProvider);

        when:
        String configValue = config.getConfig(configId);

        then:
        configValue == mockConfigValue;
    }

    def "Set Config"() {
        setup:
        String configId = "kafkaUrl";
        String configValue=  "kafkaHost:9092"
        KeyValueProvider mockKeyValueProvider = Mock()
        Config config = new Config(mockKeyValueProvider);

        when:
        config.setConfig(configId, configValue);

        then:
        1 * mockKeyValueProvider.setString(configId, configValue)
    }
}
