package com.common.framework.ui.page;

import com.common.framework.ui.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.openqa.selenium.support.PageFactory.initElements;

public abstract class WebPage extends WebOperations {

    private String ownUrl;

    protected WebPage() throws IOException {
        WebDriver driver = DriverManager.getDriver();
        initElements(driver, this);
        loadOwnUrl();
    }

    public void goToPageURL(String url) {
        DriverManager.getDriver().get(url);
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
