package com.abbos.multicloudstorageengine.provider;

/**
 * <p>
 * A marker interface used to identify classes that represent storage providers within the file storage framework.
 * This interface does not define any methods or behavior but serves as a tagging mechanism to group and classify
 * provider implementations (e.g., AWS S3, Google Cloud Storage).
 * </p>
 *
 * <p>
 * Implementations of this interface are expected to provide concrete functionality for interacting with specific
 * cloud storage systems. By implementing this marker interface, classes signal their role as storage providers,
 * enabling dynamic discovery and integration within the framework.
 * </p>
 *
 * <p>
 * Example Use Cases:
 * <ul>
 *   <li>Dynamic provider selection using reflection or dependency injection frameworks.</li>
 *   <li>Ensuring type safety when working with provider-specific configurations or factories.</li>
 * </ul>
 * </p>
 *
 * @author Aliabbos Ashurov
 * @since 08/February/2025 15:51
 */
public interface StorageProvider {
    // Marker interface; no methods defined.
}
