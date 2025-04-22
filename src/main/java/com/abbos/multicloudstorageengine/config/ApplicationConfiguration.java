package com.abbos.multicloudstorageengine.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Configuration class for multi-cloud storage integration.
 * - Enables asynchronous execution for storage-related tasks.
 * - Configures storage clients for AWS S3, Google Cloud Storage, and Azure Blob Storage.
 * - Uses virtual threads for optimized I/O-bound operations.
 * <p>
 * Beans are conditionally created based on the `enabled` property in `application.yml`.
 *
 * @author Aliabbos Ashurov
 * @since 01/March/2025
 */

@Configuration
@EnableAsync
public class ApplicationConfiguration {

    /**
     * Creates an Executor for storage operations using virtual threads. This bean, named
     * "storageExecutor", is optimized for I/O-bound tasks like cloud storage interactions.
     *
     * @return an Executor using virtual threads
     */
    @Bean(name = "storageExecutor")
    public Executor executor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    @ConditionalOnBean(S3Config.class)
    public S3Client s3Client(S3Config s3Config) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(s3Config.getAccessKey(), s3Config.getSecretKey());
        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.of(s3Config.getRegion()))
                .build();
    }

    @Bean
    @ConditionalOnBean(GCSConfig.class)
    public Storage gcsClient(GCSConfig gcsConfig) throws IOException {
        return StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(gcsConfig.getCredentialsPath())))
                .setProjectId(gcsConfig.getProjectId())
                .build()
                .getService();
    }

    @Bean
    @ConditionalOnBean(AzureBlobConfig.class)
    public BlobServiceClient azureBlobClient(AzureBlobConfig azureConfig) {
        String connectionString = String.format(
                "DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net",
                azureConfig.getAccountName(),
                azureConfig.getAccountKey()
        );

        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }
}