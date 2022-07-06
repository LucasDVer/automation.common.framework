package com.common.framework.ui.driver;

import com.common.framework.logger.Loggable;
import com.common.framework.ui.browser.Browser;
import com.common.framework.ui.config.UIConfigLoader;
import com.common.framework.ui.platform.Platform;
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
import java.time.Duration;

/**
 * This class manages the creation of different {@link org.openqa.selenium.WebDriver} instances, supporting parallel execution
 * by using {@link ThreadLocal} to store the WebDriver instance per thread.
 */
public final class DriverManager implements Loggable {

    private static final ThreadLocal<Driver> DRIVERS_CONSTANT = new ThreadLocal<>();

    private DriverManager() {
    }

    /**
     * Creates an instance of {@link Driver}.
     * The {@link org.openqa.selenium.WebDriver} instance will be instantiated with the desired platform capabilities.
     *
     * @param platform the {@link Platform}
     * @param browser  the {@link Browser}
     * @throws MalformedURLException if the URL of the remote server is invalid
     */
    public static void populateDriver(Platform platform, Browser browser) throws MalformedURLException {
        if (DRIVERS_CONSTANT.get() == null) {
            WebDriver webdriver;
            if (Platform.REMOTE_WEB.equals(platform)) {
                webdriver = setupRemoteWebDriver(browser);
            } else {
                webdriver = setupWebDriver(browser);
            }
            DRIVERS_CONSTANT.set(new Driver(platform, browser, webdriver));
        }
    }

    private static WebDriver setupWebDriver(Browser browser) {
        WebDriver webdriver = setupWebDriverByBrowser(browser);
        webdriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(UIConfigLoader.CONFIG.getConfig().getPageLoadTimeout()))
                .implicitlyWait(Duration.ofSeconds(UIConfigLoader.CONFIG.getConfig().getImplicitWait()));
        webdriver.manage().window().maximize();
        return webdriver;
    }

    private static WebDriver setupRemoteWebDriver(Browser browser) throws MalformedURLException {
        return new RemoteWebDriver(new URL(UIConfigLoader.CONFIG.getConfig().getRemoteServerURL()), browser.getCapabilities());
    }

    private static WebDriver setupWebDriverByBrowser(Browser browser) {

        return switch (browser) {
            case FIREFOX -> new FirefoxDriver(new FirefoxOptions(browser.getCapabilities()));
            case IE -> new InternetExplorerDriver(new InternetExplorerOptions(browser.getCapabilities()));
            default -> new ChromeDriver((ChromeOptions) browser.getCapabilities());
        };
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
