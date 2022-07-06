package com.common.framework.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public abstract class BaseAPI {

    private String baseUrl;
    private String specificPath = "";
    private String query = "";

    protected BaseAPI() {
        loadBaseUrl();
        loadSpecificPath();
    }

    public Response doGet() {
        return RestAssured.get(baseUrl + specificPath + query);
    }

    protected abstract void loadBaseUrl();

    protected abstract void loadSpecificPath();

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String url) {
        baseUrl = url;
    }

    public String getSpecificPath() {
        return specificPath;
    }

    public void setSpecificPath(String specificPath) {
        this.specificPath = specificPath;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
