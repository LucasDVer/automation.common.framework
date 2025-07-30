package com.common.framework.ui.driver;

import com.common.framework.ui.platform.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static java.lang.System.getProperty;
import static java.lang.System.getenv;

/**
 * Driver is the container of the {@link WebDriver} instance and the {@link Platform} information.
 */
public final class Driver {

    private static final String BROWSER_KEY = "browser";
    private static final String CHROME_BROWSER = "chrome";
    private static final String FIREFOX_BROWSER = "firefox";
    private static final String EDGE_BROWSER = "edge";

    private Driver(){

    }

    private static String getPropertyValue(String key) {
        String property = getProperty(key);
        if (property == null ) {
            property = getenv(key);
        }
        return property;
    }

    private static String getBrowserValue() {
        String browser = getPropertyValue(BROWSER_KEY);
        return browser == null ? CHROME_BROWSER : browser;
    }

    public static WebDriver getDriverByBrowser() {
        String browser = getBrowserValue();
        switch (browser) {
            case (FIREFOX_BROWSER) :
                return new FirefoxDriver();
            case (EDGE_BROWSER) :
                return new EdgeDriver();
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                return new ChromeDriver();
        }
    }

}
