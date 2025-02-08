package com.abbos.multicloudstorageengine.exception;

/**
 * <p>
 * A specialized exception indicating that an invalid or unsupported file storage provider was specified.
 * This exception is typically thrown when the requested provider does not match any available implementations
 * (e.g., "s3", "gcp").
 * </p>
 *
 * <p>
 * Example: If the user specifies "azure" as the provider but no Azure implementation exists, this exception
 * will be thrown.
 * </p>
 *
 * @author Aliabbos Ashurov
 * @since 08/February/2025 14:52
 */
public class InvalidProviderException extends FileStorageException {

    /**
     * Constructs a new {@link InvalidProviderException} with the name of the invalid provider.
     *
     * @param provider the name of the invalid or unsupported provider.
     */
    public InvalidProviderException(String provider) {
        super("Invalid file storage provider: " + provider);
    }
}