package com.common.framework.deprecated.webdriver;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("file:src/test/resources/webdriver.config")
public interface WebDriverConfig extends Config {

    @Key("webdriver.browser")
    @DefaultValue("firefox")
    String browser();

    @Key("webdriver.geckodriver")
    String geckoDriver();

    @Key("webdriver.chromedriver")
    String chromeDriver();

    @Key("webdriver.internetexplorerdriver")
    String internetExplorerDriver();

    @Key("reportConfigPath")
    String reportConfigPath();

}
