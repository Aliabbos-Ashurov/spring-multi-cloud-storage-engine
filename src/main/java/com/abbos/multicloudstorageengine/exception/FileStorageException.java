package com.abbos.multicloudstorageengine.exception;

/**
 * Custom runtime exception for file storage errors.
 *
 * @author Aliabbos Ashurov
 * * @since 08/February/2025 14:52
 */
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
