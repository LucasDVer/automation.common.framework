package com.common.framework.utils;

import java.util.Map;

public class ScenarioContextUtils {

    public static <T> T getCastedObjectFromContext(Map<String, Object> actualContext, String key, Class<T> classToCast){
        return (classToCast.cast(actualContext.get(key)));
    }

    private ScenarioContextUtils(){}
}
