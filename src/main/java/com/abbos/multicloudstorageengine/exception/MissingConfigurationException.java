package com.abbos.multicloudstorageengine.exception;

/**
 * Exception for missing configuration values.
 *
 * @author Aliabbos Ashurov
 * @since 08/February/2025 14:52
 */
public class MissingConfigurationException extends FileStorageException {
    public MissingConfigurationException(String configKey) {
        super("Missing required configuration: " + configKey);
    }
}
