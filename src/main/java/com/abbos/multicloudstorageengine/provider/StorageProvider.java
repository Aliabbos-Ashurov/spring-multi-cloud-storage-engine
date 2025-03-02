package com.abbos.multicloudstorageengine.provider;

import com.abbos.multicloudstorageengine.context.ExecutionContext;

/**
 * Extends the {@link Provider} interface to define a synchronous storage provider capable of performing
 * basic storage operations such as upload, download, and delete in a multi-cloud environment.
 *
 * <p>Implementations of this interface handle storage operations synchronously, utilizing an
 * {@link ExecutionContext} to configure operational parameters like timeout, retries, and metadata.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025 08:51
 */
public interface StorageProvider extends Provider {

    /**
     * Uploads data to the storage provider under the specified key.
     *
     * @param key     the unique identifier for the data in the storage system
     * @param data    the byte array containing the data to upload
     * @param context the {@link ExecutionContext} defining operational constraints
     * @throws IllegalArgumentException if key is null or empty, or if data is null
     * @throws RuntimeException         if the upload operation fails due to provider-specific issues
     */
    void upload(String key, byte[] data, ExecutionContext context);

    /**
     * Deletes data associated with the specified key from the storage provider.
     *
     * @param key     the unique identifier of the data to delete
     * @param context the {@link ExecutionContext} defining operational constraints
     * @throws IllegalArgumentException if key is null or empty
     * @throws RuntimeException         if the delete operation fails due to provider-specific issues
     */
    void delete(String key, ExecutionContext context);

    /**
     * Downloads data associated with the specified key from the storage provider.
     *
     * @param key     the unique identifier of the data to download
     * @param context the {@link ExecutionContext} defining operational constraints
     * @return a byte array containing the downloaded data
     * @throws IllegalArgumentException if key is null or empty
     * @throws RuntimeException         if the download operation fails due to provider-specific issues
     */
    byte[] download(String key, ExecutionContext context);
}
