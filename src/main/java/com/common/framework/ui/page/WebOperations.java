package com.common.framework.ui.page;

import com.common.framework.ui.driver.DriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static java.util.stream.Stream.of;

public abstract class WebOperations extends WaitOperations {

    /**
     * Navigates to the URL.
     *
     * @param url the URL
     */
    protected void goTo(String url) {
        DriverManager.getDriver().get(url);
    }

    /**
     * Refresh the page.
     */
    protected void refresh() {
        DriverManager.getDriver().navigate().refresh();
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
            Actions builder = new Actions(DriverManager.getDriver());
            builder.moveToElement(element).click(element);
            builder.perform();
            return true;
        } catch (TimeoutException toe) {
            return false;
        }
    }


    /**
     * Navigates back.
     */
    protected void back() {
        DriverManager.getDriver().navigate().back();
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
