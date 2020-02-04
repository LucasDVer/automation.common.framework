package com.travelocity.framework.logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Loggeable supports logging operations.
 */
public interface Loggable {

    /**
     * Logs a message at INFO level.
     *
     * @param message the message to be logged
     */
    default void info(String message) {
        getLogger(getClass()).info(message);
    }

    /**
     * Logs a message at DEBUG level.
     *
     * @param message the message to be logged
     */
    default void debug(String message) {
        getLogger(getClass()).debug(message);
    }

    /**
     * Logs a message at WARN level.
     *
     * @param message the message to be logged
     */
    default void warn(String message) {
        getLogger(getClass()).warn(message);
    }

    /**
     * Logs a message at ERROR level.
     *
     * @param message the message to be logged
     */
    default void error(String message) {
        getLogger(getClass()).error(message);
    }

    default void error(String message, Exception e) {
        getLogger(getClass()).error(e.getMessage());
    }
}
