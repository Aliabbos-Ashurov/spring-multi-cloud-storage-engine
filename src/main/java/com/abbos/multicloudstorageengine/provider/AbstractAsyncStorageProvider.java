package com.abbos.multicloudstorageengine.provider;

import com.abbos.multicloudstorageengine.model.Capabilities;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/**
 * An abstract base class for asynchronous cloud storage providers.
 * <p>
 * This class provides a standard implementation for storage providers that execute
 * operations asynchronously using {@link CompletableFuture}. It ensures that all storage
 * tasks are executed within a specified {@link Executor}.
 * </p>
 *
 * <ul>
 *     <li>Defines a common provider name.</li>
 *     <li>Enforces an asynchronous execution model.</li>
 *     <li>Provides a default implementation for {@code getCapabilities()}, indicating asynchronous support.</li>
 * </ul>
 * <p>
 * Subclasses should extend this class to implement specific cloud storage functionality.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025 08:54
 */
public abstract class AbstractAsyncStorageProvider implements AsyncStorageProvider {

    /**
     * The name of the cloud storage provider.
     */
    protected final String providerName;

    /**
     * The executor used for running asynchronous storage operations.
     */
    private final Executor executor;

    /**
     * Constructs an {@code AbstractAsyncStorageProvider} with the specified provider name and executor.
     *
     * @param providerName the name of the storage provider (e.g., "AWS S3", "Google Cloud Storage", "Azure Blob Storage")
     * @param executor     the {@link Executor} used to run asynchronous operations
     */
    protected AbstractAsyncStorageProvider(String providerName, Executor executor) {
        this.providerName = providerName;
        this.executor = executor;
    }

    @Override
    public String getProviderName() {
        return providerName;
    }

    /**
     * @return {@link Capabilities}
     * @see Capabilities#onAsync()
     */
    @Override
    public Capabilities getCapabilities() {
        return Capabilities.onAsync();
    }

    /**
     * Executes a supplier function asynchronously using the configured executor.
     *
     * @param action the supplier function to execute
     * @param <T>    the type of the result
     * @return a {@link CompletableFuture} emitting the result of the action, executed asynchronously
     */
    protected <T> CompletableFuture<T> executeAsync(Supplier<T> action) {
        return CompletableFuture.supplyAsync(action, executor);
    }
}

