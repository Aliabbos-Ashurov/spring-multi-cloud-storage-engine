package com.abbos.multicloudstorageengine.config;

import com.abbos.multicloudstorageengine.exception.MissingConfigurationException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 10/February/2025  15:03
 **/
@Component
@ConfigurationProperties(prefix = "multi-cloud.storage.aws")
public class S3Config implements CloudStorageConfig {

    private boolean enabled;
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucketName;
    private List<String> packages;
    private String baseUrl;
    private boolean logEnabled = false;

    @PostConstruct
    public void validate() {
        if (bucketName == null || bucketName.isBlank()) {
            throw new MissingConfigurationException("AWS Bucket name is required but not provided.");
        }
        if (accessKey == null || accessKey.isBlank()) {
            throw new MissingConfigurationException("AWS Access Key is required but not provided.");
        }
        if (secretKey == null || secretKey.isBlank()) {
            throw new MissingConfigurationException("AWS Secret Key is required but not provided.");
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setLogEnabled(boolean logEnabled) {
        this.logEnabled = logEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
