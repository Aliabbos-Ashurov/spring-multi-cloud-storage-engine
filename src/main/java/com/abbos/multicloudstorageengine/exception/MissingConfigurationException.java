package com.abbos.multicloudstorageengine.exception;

/**
 * <p>
 * A specialized exception indicating that a required configuration key or value is missing.
 * This exception is typically thrown during initialization when mandatory configuration properties
 * (e.g., bucket name, access key, secret key) are not provided or are invalid.
 * </p>
 *
 * <p>
 * Example: If the AWS S3 configuration is missing the "bucket-name" property, this exception will be thrown.
 * </p>
 *
 * @author Aliabbos Ashurov
 * @since 08/February/2025 14:52
 */
public class MissingConfigurationException extends FileStorageException {

    /**
     * Constructs a new {@link MissingConfigurationException} with the name of the missing configuration key.
     *
     * @param configKey the name of the missing or invalid configuration key.
     */
    public MissingConfigurationException(String configKey) {
        super("Missing required configuration: " + configKey);
    }
}
