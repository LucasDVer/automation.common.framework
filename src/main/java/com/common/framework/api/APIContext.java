package com.common.framework.api;

import io.restassured.response.Response;


public class APIContext {

    private Response response;

    public Response getResponse(){
        return response;
    }

    public void setResponse(Response response){
        this.response = response;
    }
}
