package com.common.framework.exceptions;

import static java.lang.String.format;

public class FailedOrInterruptedIOOperations extends RuntimeException {

    private static final String MESSAGE = "The I/O operation was interrupted or failed. Details: \n '%s'. ";

    public FailedOrInterruptedIOOperations(String details) {
        super(format(MESSAGE, details));
    }

}
