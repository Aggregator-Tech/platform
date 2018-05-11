package common.io.file

import spock.lang.Specification

class FileSystemHelperSpec extends Specification {
    def 'Test getFile()'() {
        setup:
        FileSystemHelper fileSystemHelper = new FileSystemHelper();
    }
}
