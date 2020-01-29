package automation.common.framework.datamanagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ExecutionPropertiesProvider {

    private static Properties properties;

    private static Properties getInstance() throws Exception {
        if (properties == null) {
            properties = new Properties();
            InputStream input = ExecutionPropertiesProvider.class.getClassLoader()
                    .getResourceAsStream("automation.properties");
            try {
                properties.load(input);
            } catch (NullPointerException | FileNotFoundException e) {
                throw new Exception("Bad Dir of file", e);
            } catch (IOException e) {
                throw new Exception("Properties not found", e);
            }
        }
        return properties;
    }

    public static String getString(String propertyName) {
        if (System.getProperty(propertyName) != null && !System.getProperty(propertyName).isEmpty()) {
            return System.getProperty(propertyName);
        } else {
            try {
                return getInstance().getProperty(propertyName);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

}
