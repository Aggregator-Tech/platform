package platform.config.resource

import platform.config.ConfigConstants
import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

class ConfigWebTest extends Specification{
    @Shared
    String baseUrl;

    def setup() {
        baseUrl = System.getProperty("baseUrl");
    }
    def "test Config Service"() {
        setup:
        String configId = "kafkaUrl";
        String configValue=  "kafkaHost:9092"
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        WebTarget target = clientBuilder.build().target(baseUrl).path(ConfigConstants.RESOURCE_PROPERTY_PATH)
                .path(configId)

        when:
        Response response = target.request().put(Entity.entity(configValue, MediaType.TEXT_PLAIN))

        then:
        response.status == 200;

        when:
        String retConfigValue = target.request().get(String.class)
        println "retConfigValue = $retConfigValue"

        then:
        assert retConfigValue == configValue
    }
}
