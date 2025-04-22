package com.abbos.multicloudstorageengine.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Configuration properties for Google Cloud Storage (GCS), bound to 'multi-cloud.storage.gcs' prefix.
 * Validates essential fields after initialization.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025  11:35
 **/
@Component
@ConfigurationProperties(prefix = "multi-cloud.storage.gcs")
@ConditionalOnProperty(prefix = "multi-cloud.storage.gcs", name = "enabled", havingValue = "true", matchIfMissing = false)
public class GCSConfig implements CloudConfig {
    private boolean enabled;
    private String projectId;
    private String credentialsPath;
    private String bucketName;
    private List<String> packages = Collections.emptyList();
    private String baseUrl;
    private boolean logEnabled;

    @PostConstruct
    public void validate() {
        validateField(projectId, "GCS Project ID");
        validateField(credentialsPath, "GCS Credentials Path");
        validateField(bucketName, "GCS Bucket name");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public List<String> getPackages() {
        return Collections.unmodifiableList(packages);
    }

    public void setPackages(List<String> packages) {
        this.packages = packages != null ? packages : Collections.emptyList();
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCredentialsPath() {
        return credentialsPath;
    }

    public void setCredentialsPath(String credentialsPath) {
        this.credentialsPath = credentialsPath;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}