package com.abbos.multicloudstorageengine.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Aliabbos Ashurov
 * @since 10/February/2025  16:25
 **/
@Component
@ConfigurationProperties(prefix = "multi-cloud.storage.gcs")
public class GCSConfig implements CloudStorageConfig {

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
