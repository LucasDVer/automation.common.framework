package com.common.framework.ui.config;


import com.common.framework.logger.Loggable;


public class UIConfiguration implements Loggable {

    private String baseURL;

    private String remoteServerURL;

    private Integer implicitWait;

    private Integer explicitWait;

    private Integer pollingEvery;

    private Integer pageLoadTimeout;

    private Integer scriptTimeout;

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getRemoteServerURL() {
        return remoteServerURL;
    }

    public void setRemoteServerURL(String remoteServerURL) {
        this.remoteServerURL = remoteServerURL;
    }

    public Integer getImplicitWait() {
        return implicitWait;
    }

    public void setImplicitWait(Integer implicitWait) {
        this.implicitWait = implicitWait;
    }

    public Integer getExplicitWait() {
        return explicitWait;
    }

    public void setExplicitWait(Integer explicitWait) {
        this.explicitWait = explicitWait;
    }

    public Integer getPollingEvery() {
        return pollingEvery;
    }

    public void setPollingEvery(Integer pollingEvery) {
        this.pollingEvery = pollingEvery;
    }

    public Integer getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public void setPageLoadTimeout(Integer pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public Integer getScriptTimeout() {
        return scriptTimeout;
    }

    public void setScriptTimeout(Integer scriptTimeout) {
        this.scriptTimeout = scriptTimeout;
    }

    public UIConfiguration(){

    }

    public UIConfiguration(String baseURL, String remoteServerURL, Integer implicitWait, Integer explicitWait, Integer pollingEvery, Integer pageLoadTimeout, Integer scriptTimeout) {
        this.baseURL = baseURL;
        this.remoteServerURL = remoteServerURL;
        this.implicitWait = implicitWait;
        this.explicitWait = explicitWait;
        this.pollingEvery = pollingEvery;
        this.pageLoadTimeout = pageLoadTimeout;
        this.scriptTimeout = scriptTimeout;
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
