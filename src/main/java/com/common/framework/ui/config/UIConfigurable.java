package com.common.framework.ui.config;

public interface UIConfigurable {

    String getBaseURL();

    String getRemoteServerURL();

    Integer getImplicitWait();

    Integer getExplicitWait();

    Integer getPollingEvery();

    Integer getPageLoadTimeout();

    Integer getScriptTimeout();
}
