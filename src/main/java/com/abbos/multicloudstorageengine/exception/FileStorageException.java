package com.abbos.multicloudstorageengine.exception;

/**
 * <p>
 * A custom runtime exception representing errors that occur during file storage operations.
 * This serves as the base exception class for all file storage-related errors in the framework.
 * </p>
 *
 * <p>
 * Subclasses of this exception provide more specific error handling for scenarios such as invalid
 * providers or missing configurations.
 * </p>
 *
 * @author Aliabbos Ashurov
 * @since 08/February/2025 14:52
 */
public class FileStorageException extends RuntimeException {

    /**
     * Constructs a new {@link FileStorageException} with the specified detail message.
     *
     * @param message the detail message describing the cause of the exception.
     */
    public FileStorageException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link FileStorageException} with the specified detail message and cause.
     *
     * @param message the detail message describing the cause of the exception.
     * @param cause   the underlying cause of the exception.
     */
    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
