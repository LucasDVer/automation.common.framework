package com.common.framework.utils;

import com.common.framework.bdd.ScenarioContext;

public class ScenarioContextUtils {

    public static <T> T getCastedObjectFromContext(ScenarioContext actualContext, String key, Class<T> classToCast){
        return (classToCast.cast(actualContext.getFromContext(key)));
    }

    private ScenarioContextUtils(){}
}
