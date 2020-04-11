package com.common.framework.ui.page.web;

import com.common.framework.ui.driver.Drivers;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
@Setter
public abstract class WebComponent extends WebOperations {

    private WebElement webElementContainer;
    private By byContainer;

    protected WebComponent(WebElement container) {
        this.webElementContainer = container;
    }

    protected WebComponent(By container) {
        this.byContainer = container;
        this.webElementContainer = Drivers.getDriver().getWebDriver().findElement(container);
    }

    public WebElement getWebElementContainer() {
        return webElementContainer;
    }

}
