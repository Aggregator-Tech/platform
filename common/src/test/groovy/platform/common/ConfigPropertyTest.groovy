package platform.common

import spock.lang.Specification

class ConfigPropertyTest extends Specification {
    def "ToString"() {
        expect:
        ConfigProperty.COMMON_ABOUT_DESCRIPTION.toString() == "commonAboutDescription";
    }
}
