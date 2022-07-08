package com.common.framework.deprecated.webdriver;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    public static WebDriver generateDriver() {
        WebDriver driver;
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);
        String browser = config.browser().toUpperCase();
        switch (browser) {
            case "FIREFOX" -> {
                System.setProperty("webdriver.gecko.driver", config.geckoDriver());
                driver = new FirefoxDriver();
            }
            case "IE" -> {
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
                ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                ieOptions.setCapability("requireWindowFocus", true);
                ieOptions.setCapability("ignoreZoomSetting", true);
                File file = new File("IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                driver = new InternetExplorerDriver(ieOptions);
            }
            default -> {
                System.setProperty("webdriver.chrome.driver", config.chromeDriver());
                driver = new ChromeDriver();
            }
        }
        return driver;
    }

    public WebDriver get() {
        WebDriver driver = generateDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

}