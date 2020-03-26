package com.common.framework.bdd;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> context;

    public ScenarioContext(){
        this.context = new HashMap<>();
    }

    public Object getContext(){
        return context;
    }

    public Object getFromContext(String key){
        return context.get(key);
    }

    public void addToContext(String key, Object value){
        context.put(key, value);
    }
}
