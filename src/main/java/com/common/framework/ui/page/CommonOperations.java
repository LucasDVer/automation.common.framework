package com.common.framework.ui.page;

import com.common.framework.ui.driver.Drivers;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static java.util.stream.Stream.of;

public abstract class CommonOperations extends WaitOperations {

    /**
     * Navigates back.
     */
    protected void back() {
        Drivers.getDriver().getWebDriver().navigate().back();
    }

    /**
     * Clicks on the element.
     *
     * @param webElement the {@link WebElement}
     */
    protected void click(WebElement webElement) {
        if (isElementClickable(webElement)) {
            webElement.click();
        } else {
            throw new NoSuchElementException("Unable to find element to perform click");
        }

    }

    /**
     * Gets the text from the web element.
     *
     * @param webElement the {@link WebElement}
     * @return the text
     */
    protected String getText(WebElement webElement) {
        if (isElementVisible(webElement)) {
            return webElement.getText();
        } else {
            throw new NoSuchElementException("Unable to find element");
        }
    }

    /**
     * Verifies if the WebElement's text matches all the criteria.
     *
     * @param webElement the {@link WebElement}
     * @param texts      the expected texts
     * @return {@code TRUE} if WebElement's text matches the criteria
     */
    protected boolean match(WebElement webElement, String... texts) {
        return of(texts).allMatch(text -> getText(webElement).toLowerCase().contains(text.toLowerCase()));
    }

}
