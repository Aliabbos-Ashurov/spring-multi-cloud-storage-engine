package com.abbos.multicloudstorageengine.provider;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An interface providing default logging behavior using java.util.logging with an enable/disable toggle.
 *
 * @author Aliabbos Ashurov
 * @since 17/March/2025  21:14
 **/
public interface Loggable {

    boolean isLogEnabled();
    
    Logger getLogger();

    /**
     * Logs a message at the FINE level (equivalent to debug) if logging is enabled.
     *
     * @param message the message to log
     * @param args    optional arguments for message formatting
     */
    default void logDebug(String message, Object... args) {
        if (isLogEnabled() && getLogger().isLoggable(Level.FINE)) {
            getLogger().log(Level.FINE, String.format(message, args));
        }
    }

    /**
     * Logs a message at the INFO level if logging is enabled.
     *
     * @param message the message to log
     * @param args    optional arguments for message formatting
     */
    default void logInfo(String message, Object... args) {
        if (isLogEnabled() && getLogger().isLoggable(Level.INFO)) {
            getLogger().log(Level.INFO, String.format(message, args));
        }
    }

    /**
     * Logs a message at the WARNING level if logging is enabled.
     *
     * @param message the message to log
     * @param args    optional arguments for message formatting
     */
    default void logWarn(String message, Object... args) {
        if (isLogEnabled() && getLogger().isLoggable(Level.WARNING)) {
            getLogger().log(Level.WARNING, String.format(message, args));
        }
    }

    /**
     * Logs a message at the SEVERE level (equivalent to error) with a throwable if logging is enabled.
     *
     * @param message the message to log
     * @param t       the throwable to log
     * @param args    optional arguments for message formatting
     */
    default void logError(String message, Throwable t, Object... args) {
        if (isLogEnabled() && getLogger().isLoggable(Level.SEVERE)) {
            getLogger().log(Level.SEVERE, String.format(message, args), t);
        }
    }
}
