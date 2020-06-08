package com.common.framework.configuration;

import com.common.framework.utils.FileUtils;

import java.io.IOException;
import java.util.Properties;

public class ExecutionPropertiesProvider {

    private static Properties properties;

    private ExecutionPropertiesProvider() {
    }

    private static Properties getInstance() throws IOException {
        if (properties == null) {
            properties = FileUtils.loadFromProperties(PropertiesLoader.getEnvironment());
        }
        return properties;
    }

    public static String getPropertyValue(String propertyName) throws IOException {
        if (System.getProperty(propertyName) != null && !System.getProperty(propertyName).isEmpty()) {
            return System.getProperty(propertyName);
        } else {
            return getInstance().getProperty(propertyName);
        }
    }

}
