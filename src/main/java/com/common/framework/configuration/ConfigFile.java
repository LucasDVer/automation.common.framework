package com.common.framework.configuration;

public enum ConfigFile {

    SERVICES("services"),
    UI("ui");

    private String value;

    ConfigFile(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
