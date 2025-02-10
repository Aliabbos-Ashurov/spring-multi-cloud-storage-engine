package com.abbos.multicloudstorageengine.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Aliabbos Ashurov
 * @since 09/February/2025  20:21
 **/
@Configuration
public class StorageConfig {

    @Value("${storage.virtual-threads.enabled:false}") // Default: false (use fixed thread pool)
    private boolean virtualThreadsEnabled;

    @Value("${storage.processors:0}") // Default: 0 (use available processors if not set)
    private int processors;

    @Bean
    public ExecutorService storageExecutor() {
        if (virtualThreadsEnabled) {
            return Executors.newVirtualThreadPerTaskExecutor();
        } else {
            int poolSize = (processors > 0) ? processors : Runtime.getRuntime().availableProcessors();
            return Executors.newFixedThreadPool(poolSize);
        }
    }
}
