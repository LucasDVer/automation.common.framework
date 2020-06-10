package com.common.framework.ui.config;


import com.common.framework.logger.Loggable;


public class UIConfiguration implements UIConfigurable, Loggable {

    private String remoteServerURL;

    private Integer implicitWait;

    private Integer explicitWait;

    private Integer pollingEvery;

    private Integer pageLoadTimeout;

    private Integer scriptTimeout;

    private String baseURL;

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getRemoteServerURL() {
        return remoteServerURL;
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
                ", implicitWait=" + implicitWait +
                ", explicitWait=" + explicitWait +
                ", pollingEvery=" + pollingEvery +
                ", pageLoadTimeout=" + pageLoadTimeout +
                ", scriptTimeout=" + scriptTimeout +
                '}';

    }
}
