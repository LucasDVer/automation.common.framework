package com.common.framework.ui.page;

import com.common.framework.ui.driver.DriverManager;
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
        this.webElementContainer = DriverManager.getDriver().getWebDriver().findElement(container);
    }

    public WebElement getWebElementContainer() {
        return webElementContainer;
    }

}
