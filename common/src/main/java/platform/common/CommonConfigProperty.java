package platform.common;

import com.google.common.base.CaseFormat;

public enum CommonConfigProperty implements ConfigProperty{
  SERVICE_DESCRIPTION,
  SERVICE_PORT;

  @Override
  public String toString() {
    return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, super.toString());
  }
}
