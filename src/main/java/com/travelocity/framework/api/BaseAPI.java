package com.travelocity.framework.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public abstract class BaseAPI {

    private String baseUrl;
    private String specificPath = "";
    private String query = "";

    public BaseAPI() {
        loadBaseUrl();
    }

    public Response doGet() {
        return RestAssured.get(baseUrl + specificPath + query);
    }

    protected abstract void loadBaseUrl();

    protected void loadSpecificPath() {
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSpecificPath() {
        return specificPath;
    }

    public void setBaseUrl(String url) {
        baseUrl = url;
    }

    public void setSpecificPath(String specificPath) {
        this.specificPath = specificPath;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
