package com.common.framework.ui.driver;

import com.common.framework.logger.Loggable;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

/**
 * This class manages the creation of different {@link org.openqa.selenium.WebDriver} instances, supporting parallel execution
 * by using {@link ThreadLocal} to store the WebDriver instance per thread.
 */
public final class DriverManager implements Loggable {

    private static final ThreadLocal<WebDriver> DRIVERS_CONSTANT = new ThreadLocal<>();

    private DriverManager() {
    }

    /**
     * Creates an instance of {@link Driver}.
     * The {@link org.openqa.selenium.WebDriver} instance will be instantiated with the desired platform capabilities.
     *
     * @throws MalformedURLException if the URL of the remote server is invalid
     */
    public static void populateDriver() throws MalformedURLException {
        if (DRIVERS_CONSTANT.get() == null) {
            DRIVERS_CONSTANT.set(Driver.getDriverByBrowser());
        }
    }


    /**
     * Gets the instance previously created and stored at thread-level of the getDriver.
     *
     * @return the {@link Driver}
     */
    public static WebDriver getDriver() {
        return DRIVERS_CONSTANT.get();
    }

    /**
     * Dispose the getDriver, releasing the session between the client and the server.
     * The platform will be closed.
     */
    public static void dispose() {
        DRIVERS_CONSTANT.get().quit();
        DRIVERS_CONSTANT.remove();
    }
}
