package com.common.framework.ui.page.web;

import com.common.framework.ui.driver.Driver;
import com.common.framework.ui.driver.Drivers;
import org.openqa.selenium.By;

import java.io.IOException;

import static org.openqa.selenium.support.PageFactory.initElements;

public abstract class WebPage extends WebOperations {

    private String ownUrl;

    protected WebPage() throws IOException {
        Driver driver = Drivers.getDriver();
        initElements(driver.getWebDriver(), this);
        loadOwnUrl();
    }

    public void goToPageURL(String url) {
        Drivers.getDriver().getWebDriver().get(url);
        waitForUrlToContain(url);
    }

    public String getOwnUrl() {
        return this.ownUrl;
    }

    protected void setOwnUrl(String url) {
        this.ownUrl = url;
    }

    protected abstract void loadOwnUrl() throws IOException;

    protected abstract By getPageLocator();

    public boolean isLoaded() {
        return isElementLocatedVisible(getPageLocator());
    }

}
