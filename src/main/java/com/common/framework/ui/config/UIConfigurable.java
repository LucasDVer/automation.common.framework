package com.common.framework.ui.config;

import com.common.framework.configuration.Configurable;

public interface UIConfigurable extends Configurable {

    String getRemoteServerURL();

    String getAppiumURL();

    Integer getImplicitWait();

    Integer getExplicitWait();

    Integer getPollingEvery();

    Integer getPageLoadTimeout();

    Integer getScriptTimeout();
}
