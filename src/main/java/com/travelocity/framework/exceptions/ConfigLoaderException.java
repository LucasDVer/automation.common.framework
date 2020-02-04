package com.travelocity.framework.exceptions;

import static java.lang.String.format;

public class ConfigLoaderException extends RuntimeException {

    private static final String MESSAGE = "Unable to load configuration file. Details: \n '%s'.";

    public ConfigLoaderException(String details) {
        super(format(MESSAGE, details));
    }
}
