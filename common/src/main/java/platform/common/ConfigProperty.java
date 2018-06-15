package platform.common;

import com.google.common.base.CaseFormat;

public enum ConfigProperty {
  COMMON_ABOUT_DESCRIPTION;

  @Override
  public String toString() {
    return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, super.toString());
  }
}
