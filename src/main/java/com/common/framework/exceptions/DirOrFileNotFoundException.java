package com.common.framework.exceptions;

import static java.lang.String.format;

public class DirOrFileNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Unable to find directory or file. Details: \n '%s'.";

    public DirOrFileNotFoundException(String details) {
        super(format(MESSAGE, details));
    }
}
