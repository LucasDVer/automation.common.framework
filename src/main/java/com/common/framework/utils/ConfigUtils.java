package com.common.framework.utils;

import com.common.framework.logger.Loggable;
import com.common.framework.configuration.ConfigFile;
import com.common.framework.ui.browser.Browsers;
import com.common.framework.ui.platform.Platform;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;
import static java.lang.System.getenv;

public class ConfigUtils implements Loggable {

    private static final String ENVIRONMENT = "environment";
    private static final String DEFAULT_ENVIRONMENT = "automation";
    private static final String PLATFORM = "platform";
    private static final String BROWSER = "browser";
    private static final String CONFIG_FILE_SUFFIX = "config";
    private static Properties properties;

    private ConfigUtils() {
    }

    private static Properties getInstance() {
        if (properties == null) {
            try {
                return FileUtils.loadFromProperties(ConfigUtils.getEnvironment());
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

    public static String getConfigFileNameByType(ConfigFile fileType) {
        return String.format("%s-%s-%s", fileType.getValue(), getEnvironment(), CONFIG_FILE_SUFFIX);
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
