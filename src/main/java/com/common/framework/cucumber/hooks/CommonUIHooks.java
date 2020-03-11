package com.common.framework.cucumber.hooks;

import com.common.framework.ui.driver.Drivers;
import com.common.framework.ui.platform.Platform;
import com.common.framework.ui.server.SeleniumStandaloneServer;
import com.common.framework.utils.ConfigUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.MalformedURLException;

public class CommonUIHooks {

    private static boolean isFirstRun = true;

    @Before
    public void tearUp() throws MalformedURLException {
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

    @After
    public void tearDown() {
        Runtime.getRuntime().addShutdownHook(afterAllThread());
        Drivers.dispose();
    }
}
