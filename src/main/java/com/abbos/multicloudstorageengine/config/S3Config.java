package com.abbos.multicloudstorageengine.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Configuration properties for AWS S3 storage, bound to 'multi-cloud.storage.aws' prefix.
 * Validates essential fields after initialization.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025  11:25
 **/
@Component
@ConfigurationProperties(prefix = "multi-cloud.storage.aws")
public class S3Config implements CloudConfig {

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
        validateField(accessKey, "AWS Access Key");
        validateField(secretKey, "AWS Secret Key");
        validateField(region, "AWS Region");
        validateField(bucketName, "AWS Bucket name");
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