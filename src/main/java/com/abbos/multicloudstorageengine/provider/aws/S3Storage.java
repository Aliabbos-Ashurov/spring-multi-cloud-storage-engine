package com.abbos.multicloudstorageengine.provider.aws;

import com.abbos.multicloudstorageengine.config.S3Config;
import com.abbos.multicloudstorageengine.core.AbstractFileStorage;
import com.abbos.multicloudstorageengine.enums.FileType;
import com.abbos.multicloudstorageengine.provider.StorageProvider;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

/**
 * S3Storage is a concrete implementation of the StorageProvider interface for interacting with Amazon S3.
 * It provides methods for uploading, downloading, and deleting files in an S3 bucket.
 *
 * @author Aliabbos Ashurov
 * @since 08/February/2025 15:43
 */
@Service
public final class S3Storage extends AbstractFileStorage implements StorageProvider {

    private final S3Client s3Client;
    private final String bucketName;

    /**
     * Constructs an S3Storage instance.
     *
     * @param executor The executor service for managing asynchronous tasks.
     * @param s3Client The S3 client used to interact with Amazon S3.
     * @param s3Config The configuration containing the bucket name and other S3 settings.
     */
    public S3Storage(ExecutorService executor, S3Client s3Client, S3Config s3Config) {
        super(executor, s3Config.isLogEnabled());
        this.s3Client = s3Client;
        this.bucketName = s3Config.getBucketName();
    }

    /**
     * Uploads a file to the specified path in the S3 bucket.
     *
     * @param path        The key (path) where the file will be stored in the S3 bucket.
     * @param inputStream The input stream containing the file data to upload.
     */
    @Override
    protected void upload(String path, InputStream inputStream) {
        PutObjectRequest request = buildPutObjectRequest(path)
                .contentType(FileType.OCTET_STREAM.getContentType())
                .build();
        try {
            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
            printLog(Level.INFO, "File uploaded successfully: bucket=%s, path=%s", bucketName, path);
        } catch (IOException e) {
            printLog(Level.SEVERE, "Error uploading file: bucket=%s, path=%s, error=%s", bucketName, path, e.getMessage());
        }
    }

    /**
     * Uploads a file to the specified path in the S3 bucket with a specific content type.
     *
     * @param path        The key (path) where the file will be stored in the S3 bucket.
     * @param inputStream The input stream containing the file data to upload.
     * @param fileType    The content type (e.g., "image/jpeg", "application/pdf") of the file.
     */
    @Override
    protected void upload(String path, InputStream inputStream, FileType fileType) {
        PutObjectRequest request = buildPutObjectRequest(path)
                .contentType(fileType.toString())
                .build();
        try {
            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
            printLog(Level.INFO, "File with content type uploaded successfully: bucket=%s, path=%s, contentType=%s",
                    bucketName, path, fileType);
        } catch (Exception e) {
            printLog(Level.SEVERE, "Error uploading file with content type: bucket=%s, path=%s, error=%s",
                    bucketName, path, e.getMessage());
        }
    }

    /**
     * Downloads a file from the specified path in the S3 bucket.
     *
     * @param path The key (path) of the file to download from the S3 bucket.
     * @return An Optional containing the InputStream of the downloaded file, or empty if the file does not exist.
     */
    @Override
    protected Optional<InputStream> download(String path) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(path)
                .build();
        try {
            InputStream inputStream = s3Client.getObjectAsBytes(request).asInputStream();
            printLog(Level.INFO, "File downloaded successfully: bucket=%s, path=%s", bucketName, path);
            return Optional.of(inputStream);
        } catch (Exception e) {
            printLog(Level.SEVERE, "Error downloading file: bucket=%s, path=%s, error=%s", bucketName, path, e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Deletes a file from the specified path in the S3 bucket.
     *
     * @param path The key (path) of the file to delete from the S3 bucket.
     */
    @Override
    protected void delete(String path) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(path)
                .build();
        try {
            s3Client.deleteObject(request);
            printLog(Level.INFO, "File deleted successfully: bucket=%s, path=%s", bucketName, path);
        } catch (Exception e) {
            printLog(Level.SEVERE, "Error deleting file: bucket=%s, path=%s, error=%s", bucketName, path, e.getMessage());
        }
    }

    /**
     * Builds a basic PutObjectRequest for the given path.
     *
     * @param path The key (path) where the file will be stored in the S3 bucket.
     * @return A PutObjectRequest builder configured with the bucket name and key.
     */
    private PutObjectRequest.Builder buildPutObjectRequest(String path) {
        return PutObjectRequest.builder()
                .bucket(bucketName)
                .key(path);
    }
}
