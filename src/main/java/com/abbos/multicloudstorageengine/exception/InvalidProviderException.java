package com.abbos.multicloudstorageengine.exception;

/**
 * Exception for invalid or unsupported storage providers.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025  11:27
 **/
public class InvalidProviderException extends FileStorageException {
    public InvalidProviderException(String provider) {
        super("Invalid file storage provider: " + provider);
    }
}
