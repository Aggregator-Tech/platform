package platform.common.io.system

import platform.common.Constants
import spock.lang.Specification

class SystemHelperTest extends Specification {
    def "ReadConfigurationProperty"() {


        when:
        Optional<String> property = SystemHelper.getProperty(Constants.COMMON_ABOUT_DESCRIPTION)

        then:
        !property.isPresent()
    }
}
