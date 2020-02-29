package com.common.framework.ui.page.web;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import static org.openqa.selenium.support.PageFactory.initElements;

@Getter
@Setter
public abstract class WebComponent extends WebOperations {

    private WebElement container;

    protected WebComponent(WebElement container) {
        this.container = container;
        initElements(new DefaultElementLocatorFactory(container), this);
    }

}
