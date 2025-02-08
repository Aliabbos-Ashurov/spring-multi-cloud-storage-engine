package com.abbos.multicloudstorageengine.core;

import com.abbos.multicloudstorageengine.exception.FileStorageException;

import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Aliabbos Ashurov
 * @since 08/February/2025  15:33
 **/
public abstract class AbstractFileStorage implements FileStorage {

    protected abstract void upload(String path, InputStream inputStream);

    protected abstract Optional<InputStream> download(String path);

    protected abstract void delete(String path);

    @Override
    public CompletableFuture<Void> uploadAsync(String path, InputStream inputStream) {
        return CompletableFuture.runAsync(() -> {
            try {
                upload(path, inputStream);
            } catch (Exception e) {
                throw new FileStorageException("Failed to upload file: ", e);
            }
        });
    }

    @Override
    public CompletableFuture<Optional<InputStream>> downloadAsync(String path) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return download(path);
            } catch (Exception e) {
                throw new FileStorageException("Failed to download file: ", e);
            }
        });
    }

    @Override
    public CompletableFuture<Void> deleteAsync(String path) {
        return CompletableFuture.runAsync(() -> {
            try {
                delete(path);
            } catch (Exception e) {
                throw new FileStorageException("Failed to delete file: ", e);
            }
        });
    }
}
