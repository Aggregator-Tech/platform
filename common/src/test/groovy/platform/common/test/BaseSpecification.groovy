package platform.common.test

import org.glassfish.hk2.api.ServiceLocator
import platform.common.ServiceLocatorHelper
import spock.lang.Shared
import spock.lang.Specification

class BaseSpecification extends Specification{
    @Shared ServiceLocator serviceLocator;

    def setupSpec() {
        println "setup BaseSpecification";
        serviceLocator = ServiceLocatorHelper.serviceLocator;
    }
}
