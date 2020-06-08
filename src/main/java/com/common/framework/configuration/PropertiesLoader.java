package com.common.framework.configuration;

import com.common.framework.logger.Loggable;
import com.common.framework.ui.browser.Browsers;
import com.common.framework.ui.platform.Platform;
import com.common.framework.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;
import static java.lang.System.getenv;

public class PropertiesLoader implements Loggable {

    private static final String ENVIRONMENT = "environment";
    private static final String DEFAULT_ENVIRONMENT = "automation";
    private static final String PLATFORM = "platform";
    private static final String BROWSER = "browser";
    private static Properties properties;

    private PropertiesLoader() {
    }

    private static Properties getInstance() {
        if (properties == null) {
            try {
                return FileUtils.loadFromProperties(PropertiesLoader.getEnvironment());
            } catch (IOException e) {
                throw new RuntimeException("Property file was not found");
            }
        }
        return properties;
    }

    public static String getPropertyFromFile(String key) {
        String property = getPropertyValue(key);
        if (StringUtils.isBlank(getProperty(key))) {
            return getInstance().getProperty(key);
        }
        return property;
    }

    public static String getPropertyValue(String key) {
        String property = getProperty(key);
        if (property == null) {
            property = getenv(key);
        }
        return property;
    }

    public static String getEnvironment() {
        return getPropertyValue(ENVIRONMENT) == null ? DEFAULT_ENVIRONMENT : getPropertyValue(ENVIRONMENT);
    }

    public static Platform getPlatform() {
        return getPropertyValue(PLATFORM) == null ? Platform.WEB : Platform.valueOf(getPropertyValue(PLATFORM).toUpperCase());
    }

    public static Browsers getBrowser() {
        return getPropertyValue(BROWSER) == null ? Browsers.CHROME : Browsers.valueOf(getPropertyValue(BROWSER).toUpperCase());
    }
}
