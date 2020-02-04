package com.travelocity.framework.webdriver;

import com.travelocity.framework.configuration.EnvironmentProvider;
import com.travelocity.framework.configuration.ExecutionPropertiesProvider;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Objects;

public class Context {

    private static Context instance;
    private String ENVIRONMENT = Objects.requireNonNull(ExecutionPropertiesProvider.getPropertyValue("environment")).replaceAll(" ", "");

    private Context() throws IOException {
    }

    public static synchronized Context getInstance() throws IOException {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public static String getEnvironment() throws IOException {
        return getInstance().ENVIRONMENT;
    }

    public static void initializePageInstance(WebDriver driver, String app)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        driver.get(EnvironmentProvider.getUrl(app + "." + getEnvironment()));
        System.out.println("---");
        System.out.println("ENVIRONMENT: " + EnvironmentProvider.getUrl(getEnvironment()));
    }
}
