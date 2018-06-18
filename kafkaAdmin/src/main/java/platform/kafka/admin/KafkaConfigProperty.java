package platform.kafka.admin;

import com.google.common.base.CaseFormat;
import platform.common.ConfigProperty;

public enum KafkaConfigProperty implements ConfigProperty {
  KAFKA_BOOTSTRAP_SERVER;

  @Override
  public String toString() {
    return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, super.toString());
  }
}