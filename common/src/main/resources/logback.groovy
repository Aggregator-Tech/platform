import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.core.util.FileSize

def logPath="/log"
def logArchive="${logPath}/archive"

appender("Console-Appender", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
}

appender("RollingFile-Appender", RollingFileAppender) {
    file = "${logPath}/platform.log"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${logArchive}/platform.log%d{yyyy-MM-dd}.log"
        maxHistory = 30
        totalSizeCap = FileSize.valueOf("1 GB")

    }
    encoder(PatternLayoutEncoder) {
        pattern = "%msg%n"
        outputPatternAsHeader = true
    }
}

appender("Async-Appender",AsyncAppender){
    appenderRef("RollingFile-Appender")
}

root(INFO,["Console-Appender", "Async-Appender"])
