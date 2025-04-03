package com.abbos.multicloudstorageengine.provider;

import com.abbos.multicloudstorageengine.exception.InvalidProviderException;
import com.abbos.multicloudstorageengine.model.Capabilities;

import java.util.logging.Logger;

/**
 * An abstract base class for cloud storage providers, implementing the {@link StorageProvider} and {@link Loggable} interfaces.
 * <p>
 * This class serves as a foundation for all storage provider implementations, providing a common structure that includes:
 * <ul>
 *     <li>A unique provider name to identify the storage service.</li>
 *     <li>Default logging capabilities via {@link Loggable}, configurable through a logging toggle.</li>
 *     <li>A default implementation of {@link #getCapabilities()}, indicating no advanced features unless overridden.</li>
 * </ul>
 * Subclasses must extend this class to implement specific storage operations such as upload, download, and delete,
 * while inheriting basic provider identification and logging functionality.
 * </p>
 *
 * @author Aliabbos Ashurov
 * @since 02 March 2025 08:52
 */
public abstract class AbstractStorageProvider implements StorageProvider{
    private final Logger logger;
    protected final String providerName;
    protected final boolean logEnabled;

    protected AbstractStorageProvider(String providerName, boolean logEnabled) {
        if (providerName == null) {
            throw new InvalidProviderException("Provider name cannot be null");
        }
        this.providerName = providerName;
        this.logger = Logger.getLogger(this.getClass().getName());
        this.logEnabled = logEnabled;
    }

    /**
     * Returns the name of this storage provider.
     *
     * @return the provider name as a {@code String}
     */
    @Override
    public String getProviderName() {
        return providerName;
    }

    /**
     * Returns the capabilities of this storage provider.
     * <p>
     * By default, this implementation returns {@link Capabilities#none()}, indicating no support
     * for advanced features such as asynchronous or reactive operations. Subclasses may override
     * this method to specify their supported capabilities.
     * </p>
     *
     * @return a {@link Capabilities} object representing the provider's features
     * @see Capabilities#none()
     */
    @Override
    public Capabilities getCapabilities() {
        return Capabilities.none();
    }

    /**
     * Returns the logger instance for this provider.
     * <p>
     * The logger is initialized with the fully qualified name of the implementing class,
     * allowing for class-specific logging.
     * </p>
     *
     * @return the {@link Logger} instance used for logging
     */
    @Override
    public Logger getLogger() {
        return logger;
    }

    /**
     * Indicates whether logging is enabled for this provider.
     *
     * @return {@code true} if logging is enabled, {@code false} otherwise
     */
    @Override
    public boolean isLogEnabled() {
        return logEnabled;
    }
}