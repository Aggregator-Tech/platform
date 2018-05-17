package webTemplate.resource

import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget

class AboutTest extends Specification{
    @Shared
    String baseUrl;

    def setup() {
        baseUrl = System.getProperty("baseUrl");
    }
    def testGetAbout() {
        setup:
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        println "baseUrl= $basurl"
        
        WebTarget target = clientBuilder.build().target(baseUrl).path("/v1/about")
        String path =  target.getUri().getPath()
        println "Path is $path "
        when:
        String response = target.request().get(String.class)
        println "response = $response"

        then:
        assert response == "webTemplate"
    }
}
