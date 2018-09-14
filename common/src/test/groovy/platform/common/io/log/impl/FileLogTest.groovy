package platform.common.io.log.impl

import org.junit.Ignore
import platform.common.io.log.Log
import platform.common.test.BaseSpecification

@Ignore("Enable after finding solution to ensure presence of log directory. may be using logback-test config file")
class FileLogTest extends BaseSpecification {
    def "Info"() {
        expect:
        Log log = serviceLocator.getService(Log.class);
        log.info("Test log message");
    }
}
