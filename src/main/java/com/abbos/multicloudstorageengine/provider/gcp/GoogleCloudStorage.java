package com.abbos.multicloudstorageengine.provider.gcp;

import com.abbos.multicloudstorageengine.core.AbstractFileStorage;
import com.abbos.multicloudstorageengine.provider.StorageProvider;

import java.io.InputStream;
import java.util.Optional;

/**
 * @author Aliabbos Ashurov
 * @since 08/February/2025  15:42
 **/
public final class GoogleCloudStorage extends AbstractFileStorage implements StorageProvider {

    @Override
    protected void upload(String path, InputStream inputStream) {

    }

    @Override
    protected Optional<InputStream> download(String path) {
        return Optional.empty();
    }

    @Override
    protected void delete(String path) {

    }
}
