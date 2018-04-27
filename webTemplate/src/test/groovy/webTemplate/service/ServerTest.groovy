package webTemplate.service

import spock.lang.Specification

class ServerTest extends Specification {
    def "GetBaseUri"() {
        when:
        Server server = new Server();
        then:
        assert server.getBaseUri() == "http://0.0.0.0:9501/webTemplate/"
    }

    def "GetBaseUri with configured port"() {
        when:
        Server server = new Server();
        System.setProperty("server.port", "9500");
        then:
        assert server.getBaseUri() == "http://0.0.0.0:9500/webTemplat/"
    }

}
