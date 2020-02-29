package com.common.framework.ui.platform;

public class Environment {

    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String WINDOWS_OS_PROPERTY = "win";

    private Environment() {

    }

    public static boolean isWindows() {
        return OS.contains(WINDOWS_OS_PROPERTY);
    }
}
