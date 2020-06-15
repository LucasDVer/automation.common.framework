package com.common.framework.configuration;

import com.common.framework.logger.Loggable;
import com.common.framework.ui.browser.Browsers;
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
        return getPropertyValue(ENVIRONMENT_KEY) == null ? DEFAULT_ENVIRONMENT_VALUE : getPropertyValue(ENVIRONMENT_KEY);
    }

    public static Platform getPlatformValue() {
        return getPropertyValue(PLATFORM_KEY) == null ? Platform.WEB : Platform.valueOf(getPropertyValue(PLATFORM_KEY).toUpperCase());
    }

    public static Browsers getBrowserValue() {
        return getPropertyValue(BROWSER_KEY) == null ? Browsers.CHROME : Browsers.valueOf(getPropertyValue(BROWSER_KEY).toUpperCase());
    }
}
