package platform.common

import spock.lang.Specification

class ConfigPropertyTest extends Specification {
    def "ToString"() {
        expect:
        ConfigProperty.SERVICE_DESCRIPTION.toString() == "serviceDescription";
    }
}
