package com.common.framework.bdd;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static Map<String, Object> context = new HashMap<>();

    public static Map<String, Object> getContext(){
        return context;
    }

    public Object getFromContext(String key){
        return context.get(key);
    }

    public static void addToContext(String key, Object value){
        context.put(key, value);
    }
}
