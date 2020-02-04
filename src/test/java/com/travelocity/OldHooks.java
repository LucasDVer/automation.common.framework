package com.travelocity;

import com.travelocity.framework.webdriver.WebDriverFactory;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class OldHooks {

    private static WebDriver driver;

    static WebDriver getWebDriver() {
        return driver;
    }

    @Before
    public void tearUp(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@NoBrowser")) {
            System.out.println("Initializing service testing");
        } else {
            driver = WebDriverFactory.generateDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@NoBrowser")) {
            System.out.println("Finalizing service testing");
        } else {
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
                driver = null;
            }
        }
    }
}
