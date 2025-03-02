package com.abbos.multicloudstorageengine.exception;

/**
 * Exception for missing configuration values.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025  11:28
 **/
public class MissingConfigurationException extends FileStorageException {
    public MissingConfigurationException(String configKey) {
        super(STR."Missing required configuration: \{configKey}");
    }
}
