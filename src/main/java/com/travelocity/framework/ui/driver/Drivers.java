package com.travelocity.framework.ui.driver;

import com.travelocity.framework.logger.Loggable;
import com.travelocity.framework.ui.browser.Browsers;
import com.travelocity.framework.ui.config.UIConfigLoader;
import com.travelocity.framework.ui.platform.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Drivers manages the creation of different {@link org.openqa.selenium.WebDriver} instances, supporting parallel execution
 * by using {@link ThreadLocal} to store the WebDriver instance per thread.
 */
public final class Drivers implements Loggable {

    private static final ThreadLocal<Driver> DRIVERS_CONSTANT = new ThreadLocal<>();

    private Drivers() {
    }

    /**
     * Creates an instance of {@link Driver}.
     * The {@link org.openqa.selenium.WebDriver} instance will be instantiated with the desired platform capabilities.
     *
     * @param platform the {@link Platform}
     * @param browsers the {@link Browsers}
     * @return the {@link Driver}
     * @throws MalformedURLException if the URL of the remote server is invalid
     */
    public static void populateDriver(Platform platform, Browsers browsers) throws MalformedURLException {
        if (DRIVERS_CONSTANT.get() == null) {
            WebDriver webdriver;
            switch (platform) {
                case MOBILE:
                    webdriver = new AppiumDriver(new URL(UIConfigLoader.CONFIG.getAppiumURL()), browsers.getCapabilities());
                    break;
                case WEB:
                    webdriver = setupWebDriverForWeb(browsers);
                    break;
                default:
                    webdriver = setupRemoteWebDriverForWeb(browsers);
            }
            DRIVERS_CONSTANT.set(new Driver(platform, browsers, webdriver));
        }
    }

    private static WebDriver setupWebDriverForWeb(Browsers browsers) throws MalformedURLException {
        WebDriver webdriver;
        webdriver = setupRemoteWebDriverForWeb(browsers);
        webdriver.manage().timeouts().pageLoadTimeout(UIConfigLoader.CONFIG.getPageLoadTimeout(), SECONDS)
                .setScriptTimeout(UIConfigLoader.CONFIG.getScriptTimeout(), SECONDS)
                .implicitlyWait(UIConfigLoader.CONFIG.getImplicitWait(), SECONDS);
        webdriver.manage().window().maximize();
        return webdriver;
    }

    private static WebDriver setupRemoteWebDriverForWeb(Browsers browsers) throws MalformedURLException {
        return new RemoteWebDriver(new URL(UIConfigLoader.CONFIG.getRemoteServerURL()), browsers.getCapabilities());
    }

    private static WebDriver setupWebDriverForWebByBrowser(Browsers browsers) {

        switch (browsers) {
            case FIREFOX:
                return new FirefoxDriver(new FirefoxOptions(browsers.getCapabilities()));
            case IE:
                return new InternetExplorerDriver(new InternetExplorerOptions(browsers.getCapabilities()));
            default:
                return new ChromeDriver((ChromeOptions) browsers.getCapabilities());
        }
    }


    /**
     * Gets the instance previously created and stored at thread-level of the getDriver.
     *
     * @return the {@link Driver}
     */
    public static Driver getDriver() {
        return DRIVERS_CONSTANT.get();
    }

    /**
     * Dispose the getDriver, releasing the session between the client and the server.
     * The platform will be closed.
     */
    public static void dispose() {
        DRIVERS_CONSTANT.get().getWebDriver().quit();
        DRIVERS_CONSTANT.remove();
    }
}
