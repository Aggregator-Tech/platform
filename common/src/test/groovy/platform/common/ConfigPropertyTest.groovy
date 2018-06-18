package platform.common

import spock.lang.Specification

class ConfigPropertyTest extends Specification {
    def "ToString"() {
        expect:
        CommonConfigProperty.SERVICE_DESCRIPTION.toString() == "serviceDescription";
    }
}
