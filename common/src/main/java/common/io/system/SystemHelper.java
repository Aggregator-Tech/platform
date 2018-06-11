package common.io.system;

public class SystemHelper {

    public String readConfigurationProperty(String propertyName) {
        String propertyValue = System.getenv(propertyName);
        if (propertyValue == null || propertyValue.isEmpty()) {
            propertyValue = System.getProperty(propertyName);
        }
        return propertyValue;
    }
}
