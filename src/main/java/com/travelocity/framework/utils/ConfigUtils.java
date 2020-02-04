package com.travelocity.framework.utils;

import com.travelocity.framework.configuration.ConfigFile;
import com.travelocity.framework.logger.Loggable;
import com.travelocity.framework.ui.browser.Browsers;
import com.travelocity.framework.ui.platform.Platform;

import static java.lang.System.getProperty;
import static java.lang.System.getenv;

public class ConfigUtils implements Loggable {

    // Environment variable names
    private static final String ENVIRONMENT = "environment";
    private static final String DEFAULT_ENVIRONMENT = "automation";
    private static final String PLATFORM = "platform";
    private static final String BROWSER = "browser";
    private static final String CONFIG_FILE_SUFFIX = "config";

    private ConfigUtils() {
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
