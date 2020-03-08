package com.common.framework.ui.browser;

import org.openqa.selenium.Capabilities;

/**
 * GetCapabilities represents a contract for any new Browser to be supported.
 */
public interface GetCapabilities {

    /**
     * Gets capabilities.
     *
     * @return the {@link Capabilities}
     */
    Capabilities getCapabilities();

}
