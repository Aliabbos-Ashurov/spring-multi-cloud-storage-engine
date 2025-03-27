package com.abbos.multicloudstorageengine.provider;

import com.abbos.multicloudstorageengine.context.ExecutionContext;

import java.util.concurrent.CompletableFuture;

/**
 * Extends the {@link Provider} interface to define an asynchronous storage provider capable of performing
 * storage operations (upload, download, and delete) using Java's {@link CompletableFuture} for non-blocking
 * execution.
 *
 * <p>Implementations of this interface handle storage operations asynchronously in a multi-cloud environment,
 * leveraging an {@link ExecutionContext} to configure operational parameters such as timeout and retries.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025 08:56
 */
public interface AsyncStorageProvider extends Provider {

    /**
     * Uploads data to the storage provider under the specified key asynchronously.
     *
     * @param key     the unique identifier for the data in the storage system
     * @param data    the byte array containing the data to upload
     * @param context the {@link ExecutionContext} defining operational constraints
     * @return a {@link CompletableFuture} representing the asynchronous completion of the upload operation
     * @throws IllegalArgumentException if key is null or empty, or if data is null
     */
    CompletableFuture<Void> upload(String key, byte[] data, ExecutionContext context);

    /**
     * Deletes data associated with the specified key from the storage provider asynchronously.
     *
     * @param key     the unique identifier of the data to delete
     * @param context the {@link ExecutionContext} defining operational constraints
     * @return a {@link CompletableFuture} representing the asynchronous completion of the delete operation
     * @throws IllegalArgumentException if key is null or empty
     */
    CompletableFuture<Void> delete(String key, ExecutionContext context);

    /**
     * Downloads data associated with the specified key from the storage provider asynchronously.
     *
     * @param key     the unique identifier of the data to download
     * @param context the {@link ExecutionContext} defining operational constraints
     * @return a {@link CompletableFuture} resolving to the downloaded byte array upon completion
     * @throws IllegalArgumentException if key is null or empty
     */
    CompletableFuture<byte[]> download(String key, ExecutionContext context);
}
