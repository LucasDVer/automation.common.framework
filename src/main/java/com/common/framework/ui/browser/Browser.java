package com.common.framework.ui.browser;

import com.common.framework.configuration.SystemVariablesProvider;
import com.common.framework.ui.driver.capabilities.CapabilitiesLoader;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.github.bonigarcia.wdm.WebDriverManager.*;
import static java.util.Collections.synchronizedList;

/**
 * Browsers supports the process of getting the capabilities per platform and browsers.
 * <p>
 * It uses {@link io.github.bonigarcia.wdm.WebDriverManager} to download the required version of driver binary.
 */
public enum Browser implements GetCapabilities {


    CHROME {
        public ChromeOptions getCapabilities() {
            if (!BINARY_DOWNLOADED.contains(CHROME)) {
                chromedriver().setup();
                BINARY_DOWNLOADED.add(CHROME);
            }

            DesiredCapabilities capabilities = new DesiredCapabilities("chrome", "103.0.5060.66", Platform.WINDOWS);

            String[] arguments = new String[0];
            if (IS_HEADLESS) {
                Map<String, String> extraCapabilities = CapabilitiesLoader.CAPABILITIES.readCapabilities("headless");
                arguments = String.valueOf(extraCapabilities.get(ARGUMENTS)).split(",");
                extraCapabilities.remove(ARGUMENTS);
                extraCapabilities.forEach(capabilities::setCapability);
            }

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(capabilities);
            for (String argument : arguments) {
                chromeOptions.addArguments("--" + argument);
            }

            return chromeOptions;
        }
    },
    FIREFOX {
        @Override
        public FirefoxOptions getCapabilities() {
            if (!BINARY_DOWNLOADED.contains(FIREFOX)) {
                firefoxdriver().setup();
                BINARY_DOWNLOADED.add(FIREFOX);
            }

            return new FirefoxOptions();
        }
    },
    IE {
        @Override
        public InternetExplorerOptions getCapabilities() {
            if (!BINARY_DOWNLOADED.contains(IE)) {
                iedriver().setup();
                BINARY_DOWNLOADED.add(IE);
            }

            return new InternetExplorerOptions();
        }
    };

    private static final String ARGUMENTS = "arguments";
    private static final boolean IS_HEADLESS = Boolean.parseBoolean(SystemVariablesProvider.getPropertyValue("headless"));

    private static final List<Browser> BINARY_DOWNLOADED;

    static {
        BINARY_DOWNLOADED = synchronizedList(new ArrayList<>());
    }

}
