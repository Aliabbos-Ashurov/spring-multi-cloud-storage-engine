package com.abbos.multicloudstorageengine.core;

import com.abbos.multicloudstorageengine.enums.FileType;
import com.abbos.multicloudstorageengine.exception.FileStorageException;

import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Asynchronous file storage interface supporting multiple cloud providers like AWS S3 and Google Cloud Storage.
 *
 * @author Aliabbos Ashurov
 * @since 08/February/2025 14:52
 */
public interface FileStorage {

    /**
     * Uploads a file asynchronously.
     *
     * @param path        Destination path or key.
     * @param inputStream File content stream.
     * @return CompletableFuture indicating success or failure.
     * @throws FileStorageException If upload fails.
     */
    CompletableFuture<Void> uploadAsync(String path, InputStream inputStream);

    /**
     * Uploads a file asynchronously with a specified file type.
     *
     * @param path        Destination path or key.
     * @param inputStream File content stream.
     * @param fileType    Type of the file (e.g., JPEG, PDF).
     * @return CompletableFuture indicating success or failure.
     * @throws FileStorageException If upload fails.
     */
    CompletableFuture<Void> uploadAsync(String path, InputStream inputStream, FileType fileType);

    /**
     * Downloads a file asynchronously.
     *
     * @param path File path or key.
     * @return CompletableFuture with an Optional InputStream. Empty if file not found.
     * @throws FileStorageException If download fails.
     */
    CompletableFuture<Optional<InputStream>> downloadAsync(String path);

    /**
     * Deletes a file asynchronously.
     *
     * @param path File path or key.
     * @return CompletableFuture indicating success or failure.
     * @throws FileStorageException If deletion fails.
     */
    CompletableFuture<Void> deleteAsync(String path);
}
