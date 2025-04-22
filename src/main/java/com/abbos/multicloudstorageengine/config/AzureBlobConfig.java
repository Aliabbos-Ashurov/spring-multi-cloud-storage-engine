package com.abbos.multicloudstorageengine.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Configuration properties for Azure Blob Storage, bound to 'multi-cloud.storage.azure' prefix.
 * Validates essential fields after initialization.
 *
 * @author Aliabbos Ashurov
 * @since 02/March/2025  11:45
 **/
@Component
@ConfigurationProperties(prefix = "multi-cloud.storage.azure-blob")
@ConditionalOnProperty(prefix = "multi-cloud.storage.azure-blob", name = "enabled", havingValue = "true", matchIfMissing = false)
public class AzureBlobConfig implements CloudConfig {

    private boolean enabled;
    private String accountName;
    private String accountKey;
    private String containerName;
    private String baseUrl;
    private List<String> packages = Collections.emptyList();
    private boolean logEnabled = false;

    @PostConstruct
    public void validate() {
        validateField(accountName, "Azure Account Name");
        validateField(accountKey, "Azure Account Key");
        validateField(containerName, "Azure Container Name");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<String> getPackages() {
        return Collections.unmodifiableList(packages);
    }

    public void setPackages(List<String> packages) {
        this.packages = packages != null ? packages : Collections.emptyList();
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public void setLogEnabled(boolean logEnabled) {
        this.logEnabled = logEnabled;
    }
}
