package com.common.framework.ui.driver;

import com.common.framework.ui.browser.Browsers;
import com.common.framework.ui.platform.Platform;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.common.framework.ui.config.UIConfigLoader.CONFIG;

/**
 * Driver is the container of the {@link WebDriver} instance and the {@link Platform} information.
 */
public final class Driver {

    private Platform platform;

    private Browsers browsers;

    private WebDriver webDriver;

    private WebDriverWait webDriverWait;

    /**
     * Default constructor.
     *
     * @param platform  the {@link Platform}
     * @param browsers  the {@link Browsers}
     * @param webDriver the {@link WebDriver}
     */
    public Driver(Platform platform, Browsers browsers, WebDriver webDriver) {
        this.platform = platform;
        this.browsers = browsers;
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, CONFIG.get().getExplicitWait());
        webDriverWait
                //.pollingEvery(Duration.of(CONFIG.get().getPollingEvery(), SECONDS))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NotFoundException.class);
    }

    public Platform getPlatform() {
        return platform;
    }

    public Browsers getBrowsers() {
        return browsers;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }
}
