package com.common.framework.ui.js;


import com.common.framework.ui.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * Javascript.
 */
public final class Javascript {

    private Javascript() {
    }

    /**
     * Executes a click on the element by javascript.
     *
     * @param webElement the {@link WebElement}
     */
    public static void click(WebElement webElement) {
        getJavascriptExecutor().executeScript("arguments[0].click();", webElement);
    }

    /**
     * Creates an javascript executor.
     *
     * @return the {@link JavascriptExecutor}
     */
    private static JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) DriverManager.getDriver();
    }

    public void scrollIntoView(WebElement element) {
        getJavascriptExecutor().executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
