package com.abbos.multicloudstorageengine.provider;

/**
 * A marker interface to identify storage provider classes in the file storage framework.
 * It serves as a tagging mechanism for grouping provider implementations (e.g., AWS S3, Google Cloud Storage).
 * Implementations provide functionality for interacting with specific cloud storage systems.
 *
 * Example Use Cases:
 * - Dynamic provider selection via reflection or dependency injection.
 * - Ensuring type safety for provider-specific configurations or factories.
 *
 * @author Aliabbos Ashurov
 * @since 10/February/2025 18:23
 */
public interface StorageProvider extends Provider {
}
