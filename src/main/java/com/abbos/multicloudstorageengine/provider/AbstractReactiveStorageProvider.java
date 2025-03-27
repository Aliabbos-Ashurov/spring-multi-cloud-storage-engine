package com.abbos.multicloudstorageengine.provider;

import com.abbos.multicloudstorageengine.model.Capabilities;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.concurrent.Callable;

/**
 * An abstract base class for reactive cloud storage providers.
 * <p>
 * This class provides a standard implementation for storage providers that support
 * reactive programming using Project Reactor. It ensures that all storage operations
 * are executed on a specified {@link Scheduler}.
 * </p>
 *
 * <ul>
 *     <li>Defines a common provider name.</li>
 *     <li>Enforces a reactive execution model.</li>
 *     <li>Provides a default implementation for {@code getCapabilities()}, indicating reactive support.</li>
 * </ul>
 * <p>
 * Subclasses should extend this class to implement specific cloud storage functionality.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025 08:54
 */
public abstract class AbstractReactiveStorageProvider implements ReactiveStorageProvider {

    /**
     * The name of the cloud storage provider.
     */
    protected final String providerName;

    /**
     * Constructs an {@code AbstractReactiveStorageProvider} with the specified provider name and scheduler.
     *
     * @param providerName the name of the storage provider (e.g., "AWS S3", "Google Cloud Storage", "Azure Blob Storage")
     */
    protected AbstractReactiveStorageProvider(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public String getProviderName() {
        return providerName;
    }

    /**
     * @return {@link Capabilities}
     * @see Capabilities#onReactive()
     */
    @Override
    public Capabilities getCapabilities() {
        return Capabilities.onReactive();
    }

    /**
     * Executes a callable operation within a reactive pipeline, ensuring execution on the configured scheduler.
     *
     * @param action the callable action to execute
     * @param <T>    the type of the result
     * @return a {@link Mono} emitting the result of the action
     */
    protected <T> Mono<T> executeReactive(Callable<T> action) {
        return Mono.fromCallable(action);
    }
}
