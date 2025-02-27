package com.abbos.multicloudstorageengine.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "multi-cloud.storage.threads")
public class ThreadsConfig implements CloudStorageConfig {

    private boolean virtualThreadsEnabled = false;
    private int processors = 0;

    public boolean isVirtualThreadsEnabled() {
        return virtualThreadsEnabled;
    }

    public void setVirtualThreadsEnabled(boolean virtualThreadsEnabled) {
        this.virtualThreadsEnabled = virtualThreadsEnabled;
    }

    public int getProcessors() {
        return processors;
    }

    public void setProcessors(int processors) {
        this.processors = processors;
    }
}
