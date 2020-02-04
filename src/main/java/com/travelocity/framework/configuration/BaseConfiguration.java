package com.travelocity.framework.configuration;

public class BaseConfiguration implements Configurable {

    private String baseURL;

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }
}
