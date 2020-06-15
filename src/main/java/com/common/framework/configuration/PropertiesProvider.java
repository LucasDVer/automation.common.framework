package com.common.framework.configuration;

import com.common.framework.utils.FileUtils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesProvider {

    private static Properties properties;

    private PropertiesProvider() {
    }

    private static Properties getInstance() throws IOException {
        if (properties == null) {
            properties = FileUtils.loadFromProperties(SystemVariablesProvider.getEnvironmentValue());
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
