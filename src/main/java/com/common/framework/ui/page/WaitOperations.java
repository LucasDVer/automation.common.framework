package com.common.framework.ui.page;

import com.common.framework.logger.Loggable;
import com.common.framework.ui.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class WaitOperations implements Loggable {

    /**
     * Wait for a element to be visible
     *
     * @param webElement the {@link WebElement}
     * @return WebElement
     */
    public WebElement waitForElementToBeVisible(WebElement webElement) {
        return getWebDriverWait().until((ExpectedConditions.visibilityOf(webElement)));
    }

    /**
     * Wait for a element to be clickable
     *
     * @param webElement the {@link WebElement}
     * @return WebElement
     */
    public WebElement waitForElementToBeClickable(WebElement webElement) {
        return getWebDriverWait().until((ExpectedConditions.elementToBeClickable(webElement)));
    }

    /**
     * Verifies if an element is visible before interacting with it.
     *
     * @param webElement the {@link WebElement}
     * @return {@code TRUE} if the element is visible
     */
    protected boolean isElementVisible(WebElement webElement) {
        try {
            return waitForElementToBeVisible(webElement).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            error(e.getMessage());
            return false;
        }
    }

    /**
     * Verifies if an element is clickable before clicking on it.
     *
     * @param webElement the {@link WebElement}
     * @return {@code TRUE} if the element is clickable
     */
    protected boolean isElementClickable(WebElement webElement) {
        try {
            return waitForElementToBeClickable(webElement).isEnabled();
        } catch (TimeoutException | NoSuchElementException e) {
            error(e.getMessage());
            return false;
        }
    }


    /**
     * Verifies if all elements are visible before interacting with it.
     *
     * @param webElements a {@link List} of {@link WebElement}
     * @return {@code TRUE} if the elements are present
     */
    protected boolean areElementsVisible(List<WebElement> webElements) {
        try {
            return getWebDriverWait().until(visibilityOfAllElements(webElements)).stream().allMatch(WebElement::isDisplayed);
        } catch (TimeoutException | NoSuchElementException e) {
            error(e.getMessage());
            return false;
        }
    }

    /**
     * Verifies if the expected text is present in the element.
     *
     * @param webElement the {@link WebElement}
     * @param text       the expected text
     * @return {@code TRUE} if the text is present
     */
    protected boolean isTextPresent(WebElement webElement, String text) {
        try {
            return getWebDriverWait().until(or(textToBePresentInElement(webElement, text), textToBePresentInElementValue(webElement, text)));
        } catch (TimeoutException | NoSuchElementException e) {
            error(e.getMessage());
            return false;
        }
    }


    /**
     * Wait for a element located to be present
     *
     * @param by the {@link By}
     */
    public void waitForElementLocatedToBePresent(By by) {
        getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Wait for a element located to be visible
     *
     * @param by the {@link By}
     */
    public WebElement waitForElementLocatedToBeVisible(By by) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    /**
     * Verifies if an element located is present before.
     *
     * @param by the {@link By}
     * @return {@code TRUE} if the element located is present
     */
    protected boolean isElementLocatedPresent(By by) {
        try {
            waitForElementLocatedToBePresent(by);
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            error(e.getMessage());
        }
        return false;
    }

    /**
     * Verifies if an element located is visible before interacting with it.
     *
     * @param by the {@link By}
     * @return the {@link WebElement}
     */
    protected boolean isElementLocatedVisible(By by) {
        try {
            return waitForElementLocatedToBeVisible(by).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            error(e.getMessage());
            return false;
        }
    }

    private WebDriverWait getWebDriverWait() {
        return DriverManager.getDriver().getWebDriverWait();
    }

    public boolean waitForUrlToContain(String url) {
        return getWebDriverWait().until(ExpectedConditions.urlContains(url));
    }

    public void waitForElementLocatedToBeInvisible(By by) {
        getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(by));
    }


    public <T> T waitFor(ExpectedCondition<T> condition) {
        try {
            return getWebDriverWait().until(condition);
        } catch (TimeoutException toe) {
            error(toe.getMessage());
            throw toe;
        }
    }
}
