package com.common.framework.api;

import com.common.framework.configuration.PropertiesProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public abstract class BaseAPI {

    private static String baseUrl;
    private static String specificPath = "";

    protected BaseAPI() {
        loadBaseUrl();
        loadSpecificPath();
    }

    protected  Response doGet(String path) { return RestAssured.get(baseUrl + specificPath + path); }

    protected void loadBaseUrl(){
        setBaseUrl(PropertiesProvider.getPropertyValue("hostUrl"));
    }

    protected abstract void loadSpecificPath();

    public void setBaseUrl(String url) {
        baseUrl = url;
    }

    public void setSpecificPath(String specificPath) {
        this.specificPath = specificPath;
    }
}
