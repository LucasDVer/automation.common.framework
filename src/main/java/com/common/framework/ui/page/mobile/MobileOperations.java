package com.common.framework.ui.page.mobile;

import com.common.framework.ui.driver.Drivers;
import com.common.framework.ui.page.CommonOperations;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.interactions.touch.TouchActions;

public abstract class MobileOperations extends CommonOperations {

    /**
     * Types on the element.
     *
     * @param mobileElement the {@link MobileElement}
     * @param text          the text
     */
    protected void type(MobileElement mobileElement, String text) {
        isElementClickable(mobileElement);
        mobileElement.setValue(text);
    }

    /**
     * Tap on element.
     *
     * @param mobileElement the {@link MobileElement}
     */
    protected void tap(MobileElement mobileElement) {
        isElementClickable(mobileElement);
        touchActions().singleTap(mobileElement);
    }

    private TouchActions touchActions() {
        return new TouchActions(Drivers.getDriver().getWebDriver());
    }

}
