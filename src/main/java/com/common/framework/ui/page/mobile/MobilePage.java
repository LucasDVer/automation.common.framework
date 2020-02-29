package com.common.framework.ui.page.mobile;

import com.common.framework.ui.driver.Drivers;
import com.common.framework.ui.page.CommonOperations;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.PageFactory.initElements;

public abstract class MobilePage extends CommonOperations {

    protected MobilePage() {
        WebDriver webDriver = Drivers.getDriver().getWebDriver();
        initElements(new AppiumFieldDecorator(webDriver), this);
    }

}
