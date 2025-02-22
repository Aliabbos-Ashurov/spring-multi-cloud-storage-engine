package com.abbos.multicloudstorageengine.core;

import com.abbos.multicloudstorageengine.enums.FileType;
import com.abbos.multicloudstorageengine.exception.FileStorageException;

import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Aliabbos Ashurov
 * @since 08/February/2025  15:33
 **/
public abstract class AbstractFileStorage implements FileStorage {

    private final ExecutorService EXECUTOR;
    private final boolean logEnabled;
    protected final Logger LOG = Logger.getLogger(this.getClass().getName());

    public AbstractFileStorage(ExecutorService executor, boolean logEnabled) {
        this.EXECUTOR = executor;
        this.logEnabled = logEnabled;
    }

    @Override
    public CompletableFuture<Void> uploadAsync(String path, InputStream inputStream) {
        return CompletableFuture.runAsync(() -> upload(path, inputStream, FileType.OCTET_STREAM), EXECUTOR)
                .exceptionally(ex -> {
                    throw new FileStorageException("Error while uploading file", ex);
                });
    }

    @Override
    public CompletableFuture<Void> uploadAsync(String path, InputStream inputStream, FileType fileType) {
        return CompletableFuture.runAsync(() -> upload(path, inputStream, fileType), EXECUTOR)
                .exceptionally(ex -> {
                    throw new FileStorageException("Error while uploading file", ex);
                });
    }

    @Override
    public CompletableFuture<Optional<InputStream>> downloadAsync(String path) {
        return CompletableFuture.supplyAsync(() -> download(path), EXECUTOR)
                .exceptionally(ex -> {
                    throw new FileStorageException("Error while downloading file", ex);
                });
    }

    @Override
    public CompletableFuture<Void> deleteAsync(String path) {
        return CompletableFuture.runAsync(() -> delete(path), EXECUTOR)
                .exceptionally(ex -> {
                    throw new FileStorageException("Error while deleting file", ex);
                });
    }

    protected void log(Level level, String message, Object... params) {
        if (logEnabled) {
            LOG.log(level, String.format(message, params));
        }
    }

    protected abstract void upload(String path, InputStream inputStream);

    protected abstract void upload(String path, InputStream inputStream, FileType fileType);

    protected abstract Optional<InputStream> download(String path);

    protected abstract void delete(String path);
}
