package com.travelocity.framework.ui.page.mobile;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import static org.openqa.selenium.support.PageFactory.initElements;

public abstract class MobileComponent extends MobileOperations {

    private MobileElement container;

    public MobileComponent(MobileElement container) {
        initElements(new AppiumFieldDecorator(container), this);
        this.container = container;
    }

    protected MobileElement getContainer() {
        return container;
    }

}
