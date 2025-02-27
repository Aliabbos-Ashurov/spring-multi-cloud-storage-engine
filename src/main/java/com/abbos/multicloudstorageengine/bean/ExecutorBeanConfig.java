package com.abbos.multicloudstorageengine.bean;

import com.abbos.multicloudstorageengine.config.ThreadsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Aliabbos Ashurov
 * @since 10/February/2025  16:19
 **/
@Configuration
public class ExecutorBeanConfig {

    @Bean
    public ExecutorService executorService(ThreadsConfig threadsConfig) {
        if (threadsConfig.isVirtualThreadsEnabled()) {
            return Executors.newVirtualThreadPerTaskExecutor();
        }
        int processors = threadsConfig.getProcessors() > 0
                ? threadsConfig.getProcessors()
                : Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(processors);
    }
}
