package com.abbos.multicloudstorageengine.provider.aws;

import com.abbos.multicloudstorageengine.config.S3Config;
import com.abbos.multicloudstorageengine.context.ExecutionContext;
import com.abbos.multicloudstorageengine.enums.FileType;
import com.abbos.multicloudstorageengine.enums.MetadataKey;
import com.abbos.multicloudstorageengine.exception.FileStorageException;
import com.abbos.multicloudstorageengine.provider.AbstractStorageProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Component
@ConditionalOnBean(S3Client.class)
public final class S3StorageProvider extends AbstractStorageProvider {

    private static final String PROVIDER_NAME = "Amazon Simple Storage Service :: (S3)";
    private final S3Client s3Client;
    private final String bucketName;

    public S3StorageProvider(S3Client s3Client, S3Config s3Config) {
        super(PROVIDER_NAME, s3Config.isLogEnabled());
        this.s3Client = s3Client;
        this.bucketName = s3Config.getBucketName();
    }

    @Override
    public void upload(String key, byte[] data, ExecutionContext context) {
        PutObjectRequest request = PutObjectRequest.builder()
                .key(key)
                .bucket(bucketName)
                .contentLength(getMetadataValue(context, MetadataKey.CONTENT_LENGTH, Long.class)
                        .orElse((long) data.length))
                .contentType(getMetadataValue(context, MetadataKey.CONTENT_TYPE, String.class)
                        .orElse(FileType.OCTET_STREAM.getContentType()))
                .build();
        try {
            s3Client.putObject(request, RequestBody.fromBytes(data));
            logDebug("Uploaded object with key '%s' to bucket '%s', size: %d bytes", key, bucketName, data.length);
        } catch (Exception e) {
            logError("Failed to upload object with key '%s' to bucket '%s'", e, key, bucketName);
            throw new FileStorageException("Failed to upload key: %s :::: %s".formatted(key, e), e);
        }
    }

    @Override
    public void delete(String key, ExecutionContext context) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .key(key)
                .bucket(bucketName)
                .build();
        try {
            s3Client.deleteObject(request);
            logDebug("Deleted object with key '%s' from bucket '%s'", key, bucketName);
        } catch (Exception e) {
            logError("Failed to delete object with key '%s' from bucket '%s'", e, key, bucketName);
            throw new FileStorageException("Failed to delete key: %s :::: %s".formatted(key, e), e);
        }
    }

    @Override
    public byte[] download(String key, ExecutionContext context) {
        GetObjectRequest request = GetObjectRequest.builder()
                .key(key)
                .bucket(bucketName)
                .build();
        try {
            byte[] data = s3Client.getObject(request).readAllBytes();
            logDebug("Downloaded object with key '%s' from bucket '%s', size: %d bytes", key, bucketName, data.length);
            return data;
        } catch (IOException e) {
            logError("Failed to download object with key '%s' from bucket '%s'", e, key, bucketName);
            throw new FileStorageException("Failed to download key: %s :::: %s".formatted(key, e), e);
        }
    }
}