package com.abbos.multicloudstorageengine.exception;

/**
 * Exception for invalid or unsupported storage providers.
 *
 * @author Aliabbos Ashurov
 * @since 08/February/2025 14:52
 */
public class InvalidProviderException extends FileStorageException {
    public InvalidProviderException(String provider) {
        super("Invalid file storage provider: " + provider);
    }
}