import ch.qos.logback.classic.AsyncAppender

def logPath="/log"
def logArchive="${logPath}/archive"

appender("Console-Appender", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%msg%n"
    }
}

appender("RollingFile-Appender", RollingFileAppender) {
    file = "${logPath}/logfile.log"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${logArchive}/rollingfile.log%d{yyyy-MM-dd}.log"
        maxHistory = 30
        totalSizeCap = "1GB"
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
