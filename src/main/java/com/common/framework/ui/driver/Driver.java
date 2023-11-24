package com.common.framework.ui.driver;

import com.common.framework.ui.browser.Browser;
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

    private Browser browser;

    private WebDriver webDriver;

    private WebDriverWait webDriverWait;

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
        //this.webDriverWait = new WebDriverWait(webDriver, CONFIG.getConfig().getExplicitWait());
        webDriverWait
                //.pollingEvery(Duration.of(CONFIG.get().getPollingEvery(), SECONDS))
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
