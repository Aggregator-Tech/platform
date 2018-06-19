package platform.web.service

import platform.common.CommonConfigProperty
import platform.common.ServiceLocatorHelper
import platform.common.io.system.SystemHelper
import spock.lang.Specification

class ServerTest extends Specification {
    def "GetBaseUri"() {
        when:
        Server server = new Server();
        then:
        assert server.getBaseUrl() == "http://0.0.0.0:9501/"
    }

    def "GetBaseUri with configured port"() {
        when:
        Server server = new Server();
        ServiceLocatorHelper.serviceLocator.getService(SystemHelper.class)
                .writeConfigurationProperty(CommonConfigProperty.SERVICE_PORT, "9500")
        then:
        assert server.getBaseUrl() == "http://0.0.0.0:9500/"
    }

}
