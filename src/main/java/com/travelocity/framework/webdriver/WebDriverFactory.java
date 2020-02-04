package com.travelocity.framework.webdriver;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;

import static java.util.concurrent.TimeUnit.SECONDS;

public class WebDriverFactory {

    public static WebDriver generateDriver() {
        WebDriver driver;
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);
        String browser = config.browser().toUpperCase();
        switch (browser) {
            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver", config.geckoDriver());
                driver = new FirefoxDriver();
                break;
            case "IE":
                InternetExplorerOptions IEOptions = new InternetExplorerOptions();
                IEOptions.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
                IEOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                IEOptions.setCapability("requireWindowFocus", true);
                IEOptions.setCapability("ignoreZoomSetting", true);
                File file = new File("IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                driver = new InternetExplorerDriver(IEOptions);
                break;
            default:
                System.setProperty("webdriver.chrome.driver", config.chromeDriver());
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }

    public WebDriver get() {
        WebDriver driver = generateDriver();
        driver.manage().timeouts().implicitlyWait(1, SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

}