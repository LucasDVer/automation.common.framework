package com.common.framework.configuration;

import com.common.framework.logger.Loggable;
import com.common.framework.ui.browser.Browser;
import com.common.framework.ui.platform.Platform;

import static java.lang.System.getProperty;
import static java.lang.System.getenv;

public class SystemVariablesProvider implements Loggable {

    private static final String ENVIRONMENT_KEY = "environment";
    private static final String DEFAULT_ENVIRONMENT_VALUE = "automation";
    private static final String PLATFORM_KEY = "platform";
    private static final String BROWSER_KEY = "browser";

    private SystemVariablesProvider() {
    }

    public static String getPropertyValue(String key) {
        String property = getProperty(key);
        if (property == null) {
            property = getenv(key);
        }
        return property;
    }

    public static String getEnvironmentValue() {
        String environmentValue = getPropertyValue(ENVIRONMENT_KEY);
        return environmentValue == null ? DEFAULT_ENVIRONMENT_VALUE : environmentValue;
    }

    public static Platform getPlatformValue() {
        String platformValue = getPropertyValue(PLATFORM_KEY);
        return platformValue == null ? Platform.WEB : Platform.valueOf(platformValue.toUpperCase());
    }

    public static Browser getBrowserValue() {
        String browserValue = getPropertyValue(BROWSER_KEY);
        return browserValue == null ? Browser.CHROME : Browser.valueOf(browserValue.toUpperCase());
    }
}
