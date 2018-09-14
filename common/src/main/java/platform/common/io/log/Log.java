package platform.common.io.log;


import org.jvnet.hk2.annotations.Contract;

@Contract
public interface Log {
  public void info(String message);
}
