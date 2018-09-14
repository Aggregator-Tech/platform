package platform.common.io.log.impl;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.common.io.log.Log;

@Service
public class FileLog implements Log {
  private final Logger logger;

  public FileLog() {
    logger = LoggerFactory.getLogger(FileLog.class);
    System.out.println("logger.isInfoEnabled() = " + logger.isInfoEnabled());
  }

  @Override
  public void info(String message) {
    logger.info(message);
  }
}
