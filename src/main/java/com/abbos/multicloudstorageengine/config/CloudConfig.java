package com.abbos.multicloudstorageengine.config;

import com.abbos.multicloudstorageengine.exception.MissingConfigurationException;

/**
 * Marker interface for cloud storage configuration classes. Implementations define settings
 * specific to cloud providers (e.g., AWS S3, Google Cloud Storage). Provides a default method
 * for validating required configuration fields.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025
 */
public interface CloudConfig {

    /**
     * Validates that a configuration field is neither null nor blank.
     *
     * @param value     the field value to check
     * @param fieldName the name of the field for error messaging
     * @throws MissingConfigurationException if the value is null or blank
     */
    default void validateField(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new MissingConfigurationException(fieldName + " is required but not provided.");
        }
    }
}
