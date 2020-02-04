package com.travelocity.framework.ui.server;

import com.travelocity.framework.logger.Loggable;
import org.openqa.grid.internal.utils.configuration.StandaloneConfiguration;
import org.openqa.selenium.remote.server.SeleniumServer;

import static java.lang.Runtime.getRuntime;

/**
 * SeleniumStandaloneServer makes possible to start and stop a selenium standalone server instance.
 */
public enum SeleniumStandaloneServer implements Loggable {

    SERVER;

    private SeleniumServer seleniumServer;

    private boolean notStarted = true;

    /**
     * Starts the {@link SeleniumServer} using the standalone configuration.
     */
    public void start() {
        if (notStarted) {
            seleniumServer = new SeleniumServer(new StandaloneConfiguration());
            getRuntime().addShutdownHook(new Thread(this::stop));

            seleniumServer.boot();
            info("Initializing local Selenium Server Standalone ...");

            notStarted = false;
        }
        warn("Selenium server is already started.");
    }

    /**
     * Stops the {@link SeleniumServer}.
     */
    public void stop() {
        seleniumServer.stop();
        notStarted = true;
    }

}
