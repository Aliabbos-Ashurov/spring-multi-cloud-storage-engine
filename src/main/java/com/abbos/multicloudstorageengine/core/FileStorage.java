package com.abbos.multicloudstorageengine.core;

import com.abbos.multicloudstorageengine.exception.FileStorageException;

import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * A high-level abstraction for interacting with file storage systems, providing asynchronous operations
 * for uploading, downloading, and deleting files. This interface is designed to be extensible and
 * supports multiple cloud providers such as AWS S3, Google Cloud Storage, and others.
 * </p>
 *
 * <p>
 * Implementations of this interface are expected to handle provider-specific logic while adhering to
 * the contract defined here. The asynchronous nature of the methods ensures scalability and performance,
 * making it suitable for modern, non-blocking applications.
 * </p>
 *
 * @author Aliabbos Ashurov
 * @since 08/February/2025 14:52
 */
public interface FileStorage {

    /**
     * <p>
     * Asynchronously uploads a file to the storage system. The method accepts a file path and an input stream
     * representing the file content. It returns a {@link CompletableFuture} that completes when the upload
     * operation finishes successfully or exceptionally.
     * </p>
     *
     * <p>
     * This method is ideal for handling large files or high-throughput scenarios where blocking operations
     * would degrade performance.
     * </p>
     *
     * @param path          the destination path or key where the file will be stored. Must not be null or empty.
     * @param inputStream   the {@link InputStream} containing the file content to upload. Must not be null.
     * @return              a {@link CompletableFuture} that completes when the upload operation finishes.
     * @throws FileStorageException if an error occurs during the upload process (e.g., invalid path, network issues).
     */
    CompletableFuture<Void> uploadAsync(String path, InputStream inputStream);

    /**
     * <p>
     * Asynchronously downloads a file from the storage system. The method retrieves the file content as an
     * {@link InputStream} wrapped in an {@link Optional}. If the file does not exist, the result will be an
     * empty {@link Optional}.
     * </p>
     *
     * <p>
     * This method is designed to handle scenarios where file retrieval needs to be non-blocking, ensuring
     * efficient resource utilization.
     * </p>
     *
     * @param path          the path or key of the file to download. Must not be null or empty.
     * @return              a {@link CompletableFuture} containing an {@link Optional} of the file's content
     *                      as an {@link InputStream}. Returns an empty {@link Optional} if the file does not exist.
     * @throws FileStorageException if an error occurs during the download process (e.g., invalid path, network issues).
     */
    CompletableFuture<Optional<InputStream>> downloadAsync(String path);

    /**
     * <p>
     * Asynchronously deletes a file from the storage system. The method accepts the file path or key and
     * removes the corresponding file if it exists. If the file does not exist, the operation is considered
     * successful and no exception is thrown.
     * </p>
     *
     * <p>
     * This method is useful for cleaning up unused files or managing storage quotas efficiently.
     * </p>
     *
     * @param path          the path or key of the file to delete. Must not be null or empty.
     * @return              a {@link CompletableFuture} that completes when the deletion operation finishes.
     * @throws FileStorageException if an error occurs during the deletion process (e.g., invalid path, network issues).
     */
    CompletableFuture<Void> deleteAsync(String path);
}
