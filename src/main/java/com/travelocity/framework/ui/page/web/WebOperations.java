package com.travelocity.framework.ui.page.web;

import com.travelocity.framework.ui.driver.Drivers;
import com.travelocity.framework.ui.page.CommonOperations;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public abstract class WebOperations extends CommonOperations {

    /**
     * Navigates to the URL.
     *
     * @param url the URL
     */
    protected void goTo(String url) {
        Drivers.getDriver().getWebDriver().get(url);
    }

    /**
     * Refresh the page.
     */
    protected void refresh() {
        Drivers.getDriver().getWebDriver().navigate().refresh();
    }

    /**
     * Types on the element.
     *
     * @param webElement the {@link WebElement}
     * @param text       the text
     */
    protected boolean type(WebElement webElement, String text) {
        isElementVisible(webElement);
        webElement.sendKeys(text);
        return isTextPresent(webElement, text);
    }

    /**
     * Selects an option by text from an HTML Select.
     *
     * @param webElement the {@link WebElement}
     * @param text       the text to select
     */
    protected void selectByText(WebElement webElement, String text) {
        getSelect(webElement).selectByVisibleText(text);
    }

    private Select getSelect(WebElement webElement) {
        return new Select(webElement);
    }

    public boolean clickWithActionsBuilder(WebElement element) {
        try {
            Actions builder = new Actions(Drivers.getDriver().getWebDriver());
            builder.moveToElement(element).click(element);
            builder.perform();
            return true;
        } catch (TimeoutException toe) {
            return false;
        }
    }


}
