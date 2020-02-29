package com.common.framework.ui.config;


import com.common.framework.configuration.BaseConfiguration;
import com.common.framework.logger.Loggable;


public class UIConfiguration extends BaseConfiguration implements UIConfigurable, Loggable {

    private String remoteServerURL;

    private String appiumURL;

    private Integer implicitWait;

    private Integer explicitWait;

    private Integer pollingEvery;

    private Integer pageLoadTimeout;

    private Integer scriptTimeout;

    public String getRemoteServerURL() {
        return remoteServerURL;
    }

    public String getAppiumURL() {
        return appiumURL;
    }

    public Integer getImplicitWait() {
        return implicitWait;
    }

    public Integer getExplicitWait() {
        return explicitWait;
    }

    public Integer getPollingEvery() {
        return pollingEvery;
    }

    public Integer getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public Integer getScriptTimeout() {
        return scriptTimeout;
    }

    @Override
    public String toString() {
        return "Config{" +
                "baseURL='" + getBaseURL() + '\'' +
                ", remoteServerURL='" + remoteServerURL + '\'' +
                ", appiumURL='" + appiumURL + '\'' +
                ", implicitWait=" + implicitWait +
                ", explicitWait=" + explicitWait +
                ", pollingEvery=" + pollingEvery +
                ", pageLoadTimeout=" + pageLoadTimeout +
                ", scriptTimeout=" + scriptTimeout +
                '}';

    }
}
