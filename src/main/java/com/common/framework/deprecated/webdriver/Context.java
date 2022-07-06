package com.common.framework.deprecated.webdriver;

import com.common.framework.configuration.PropertiesProvider;
import com.common.framework.deprecated.configuration.EnvironmentProvider;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Objects;

public class Context {

    private static Context instance;
    private final String environment = Objects.requireNonNull(PropertiesProvider.getPropertyValue("environment")).replace(" ", "");

    private Context() {
    }

    public static synchronized Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public static String getEnvironment() {
        return getInstance().environment;
    }

    public static void initializePageInstance(WebDriver driver, String app)
            throws EncryptedDocumentException, IOException {
        driver.get(EnvironmentProvider.getUrl(app + "." + getEnvironment()));
    }
}
