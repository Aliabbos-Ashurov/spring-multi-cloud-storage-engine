package com.abbos.multicloudstorageengine.provider;

import com.abbos.multicloudstorageengine.context.ExecutionContext;
import reactor.core.publisher.Mono;

/**
 * Extends the {@link Provider} interface to define a reactive storage provider capable of performing
 * storage operations (upload, download, and delete) in a non-blocking, reactive manner.
 *
 * <p>Implementations of this interface return {@link Mono} objects from the Reactor library, enabling
 * reactive programming paradigms for handling storage operations in a multi-cloud environment.
 * The operations utilize an {@link ExecutionContext} for configuration.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025 08:57
 */
public interface ReactiveStorageProvider extends Provider {

    /**
     * Uploads data to the storage provider under the specified key in a reactive manner.
     *
     * @param key     the unique identifier for the data in the storage system
     * @param data    the byte array containing the data to upload
     * @param context the {@link ExecutionContext} defining operational constraints
     * @return a {@link Mono} representing the asynchronous completion of the upload operation
     * @throws IllegalArgumentException if key is null or empty, or if data is null
     */
    Mono<Void> upload(String key, byte[] data, ExecutionContext context);

    /**
     * Deletes data associated with the specified key from the storage provider in a reactive manner.
     *
     * @param key     the unique identifier of the data to delete
     * @param context the {@link ExecutionContext} defining operational constraints
     * @return a {@link Mono} representing the asynchronous completion of the delete operation
     * @throws IllegalArgumentException if key is null or empty
     */
    Mono<Void> delete(String key, ExecutionContext context);

    /**
     * Downloads data associated with the specified key from the storage provider in a reactive manner.
     *
     * @param key     the unique identifier of the data to download
     * @param context the {@link ExecutionContext} defining operational constraints
     * @return a {@link Mono} emitting the downloaded byte array upon completion
     * @throws IllegalArgumentException if key is null or empty
     */
    Mono<byte[]> download(String key, ExecutionContext context);
}
