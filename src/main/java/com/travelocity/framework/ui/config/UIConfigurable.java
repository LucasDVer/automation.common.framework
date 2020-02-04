package com.travelocity.framework.ui.config;

import com.travelocity.framework.configuration.Configurable;

public interface UIConfigurable extends Configurable {

    String getRemoteServerURL();

    String getAppiumURL();

    Integer getImplicitWait();

    Integer getExplicitWait();

    Integer getPollingEvery();

    Integer getPageLoadTimeout();

    Integer getScriptTimeout();
}
