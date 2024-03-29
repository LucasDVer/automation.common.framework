package com.common.framework.ui.driver;

import com.common.framework.ui.browser.Browser;
import com.common.framework.ui.config.UIConfigLoader;
import com.common.framework.ui.platform.Platform;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Driver is the container of the {@link WebDriver} instance and the {@link Platform} information.
 */
public final class Driver {

    private final Platform platform;

    private final Browser browser;

    private final WebDriver webDriver;

    private final WebDriverWait webDriverWait;

    /**
     * Default constructor.
     *
     * @param platform  the {@link Platform}
     * @param browser   the {@link Browser}
     * @param webDriver the {@link WebDriver}
     */
    public Driver(Platform platform, Browser browser, WebDriver webDriver) {
        this.platform = platform;
        this.browser = browser;
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(UIConfigLoader.getConfig().getExplicitWait()));
        webDriverWait
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NotFoundException.class);
    }

    public Platform getPlatform() {
        return platform;
    }

    public Browser getBrowser() {
        return browser;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }
}
