package com.common.framework.api;

import com.common.framework.bdd.ScenarioContext;
import io.restassured.response.Response;


public class APIContext extends ScenarioContext {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
