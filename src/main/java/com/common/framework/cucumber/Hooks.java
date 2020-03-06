package com.common.framework.cucumber;

import com.common.framework.ui.driver.Drivers;
import com.common.framework.ui.platform.Platform;
import com.common.framework.ui.server.SeleniumStandaloneServer;
import com.common.framework.utils.ConfigUtils;
import io.cucumber.java.Before;

import java.net.MalformedURLException;

public class Hooks {

    private static boolean isFirstRun = true;

    @Before("@UI")
    public void before() throws MalformedURLException {
        if (isFirstRun) {
            Runtime.getRuntime().addShutdownHook(afterAllThread());
            if (Platform.WEB.equals(ConfigUtils.getPlatform())) {
                SeleniumStandaloneServer.SERVER.start();
            }
            isFirstRun = false;
        }
        Drivers.populateDriver(ConfigUtils.getPlatform(), ConfigUtils.getBrowser());

    }

    private Thread afterAllThread() {
        return new Thread(() -> {
            if (Platform.WEB.equals(ConfigUtils.getPlatform())) {
                SeleniumStandaloneServer.SERVER.stop();
            }
        });
    }
}
