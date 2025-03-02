# Spring Multi-Cloud Storage Engine

A flexible and extensible Spring Boot library for interacting with multiple cloud storage providers, such as AWS S3,
Google Cloud Storage, and Azure Blob Storage. This project provides a unified abstraction layer to simplify file
operations (upload, download, delete) across different cloud storage services while leveraging Spring's dependency
injection and configuration capabilities.

## Features

- **Multi-Cloud Support**: Seamlessly integrates with AWS S3, Google Cloud Storage, and Azure Blob Storage.
- **Unified API**: Perform common file operations (upload, download, delete) using a consistent interface.
- **Spring Boot Integration**: Leverages Spring's dependency injection and configuration properties for easy setup.
- **Extensible Design**: Add support for additional cloud providers by implementing the `StorageService` interface.
- **Lightweight**: Minimal dependencies with a focus on simplicity and performance.

## Prerequisites

Before using this library, ensure you have the following:

- **Java**: JDK 17 or higher
- **Maven**: For dependency management and building the project
- **Spring Boot**: Version 3.x
- **Cloud Provider Credentials**: Access keys or service account credentials for your chosen cloud storage provider(s).

## Setting Up Configuration

Specify the credentials and details for your cloud storage providers in your Spring Boot application.properties or
application.yml file. Here’s a sample configuration for multiple providers:

Sample application.yml

  ````yaml
  multi-cloud:
   storage:
     aws:
       enabled: true
       access-key: ${AWS_ACCESS_KEY:m}
       secret-key: ${AWS_SECRET_KEY:k}
       base-url: ${AWS_BASE_URL:https://${multi-cloud.storage.aws.bucket-name}.s3.amazonaws.com/${multi-cloud.storage.aws.region}
       packages: [ "public", "private" ]
       log-enabled: true  # or false
       bucket-name: ${AWS_BUCKET_NAME:bucket name}
       region: ${AWS_REGION:region}
     gcp:
       enabled: true
       project-id: ${GCP_PROJECT_ID:your-gcs-project-id}
       bucket-name: ${GCP_BUCKET_NAME:your-gcs-bucket-name}
       credentials: ${GCP_CREDENTIALS:/path/to/gcs-service-account-key.json}
       log-enabled: true  # or false
     azure:
       enabled: true
       account-name: ${AZURE_ACCOUNT_NAME:your-azure-account-name}
       account-key: ${AZURE_ACCOUNT_KEY:YOUR_AZURE_ACCOUNT_KEY}
       container: ${AZURE_CONTAINER:your-blob-container-name}
       log-enabled: true  # or false
   ````

## Utilizing Storage Services

This library offers a `StorageService` interface, implemented for each supported cloud provider (`AWSS3StorageService`,
`GCPStorageService`, `AzureBlobStorageService`). You can inject these services into your Spring components as needed.

### Sample: File Management Controller

```java
import com.aliabbosashurov.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService awsStorageService;
    private final StorageService gcpStorageService;
    private final StorageService azureStorageService;

    public StorageController(
            @Qualifier("AWSS3StorageService") StorageService awsStorageService,
            @Qualifier("GCPStorageService") StorageService gcpStorageService,
            @Qualifier("AzureBlobStorageService") StorageService azureStorageService) {
        this.awsStorageService = awsStorageService;
        this.gcpStorageService = gcpStorageService;
        this.azureStorageService = azureStorageService;
    }

    @PostMapping("/upload/aws")
    public String uploadToAWS(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        awsStorageService.uploadFile(fileName, file);
        return "File successfully uploaded to AWS S3: " + fileName;
    }

    @PostMapping("/upload/gcs")
    public String uploadToGCP(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        gcpStorageService.uploadFile(fileName, file);
        return "File successfully uploaded to Google Cloud Storage: " + fileName;
    }

    @PostMapping("/upload/azure")
    public String uploadToAzure(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        azureStorageService.uploadFile(fileName, file);
        return "File successfully uploaded to Azure Blob Storage: " + fileName;
    }

    @GetMapping("/download/aws/{fileName}")
    public byte[] downloadFromAWS(@PathVariable String fileName) {
        return awsStorageService.downloadFile(fileName);
    }

    @GetMapping("/download/azure/{fileName}")
    public byte[] downloadFromAzure(@PathVariable String fileName) {
        return azureStorageService.downloadFile(fileName);
    }

    @DeleteMapping("/delete/aws/{fileName}")
    public String deleteFromAWS(@PathVariable String fileName) {
        awsStorageService.deleteFile(fileName);
        return "File removed from AWS S3: " + fileName;
    }

    @DeleteMapping("/delete/azure/{fileName}")
    public String deleteFromAzure(@PathVariable String fileName) {
        azureStorageService.deleteFile(fileName);
        return "File removed from Azure Blob Storage: " + fileName;
    }
} 
````


### Changes Made
- Added `AzureBlobStorageService` to the list of injected services in the constructor.
- Included Azure-specific endpoints for uploading (`/upload/azure`), downloading (`/download/azure`), and deleting (`/delete/azure`) files, mirroring the structure of AWS and GCP.
- Updated the introductory text to explicitly list `AzureBlobStorageService` alongside the other implementations.
- Kept the formatting consistent with Markdown standards, using ```java for the code block.

This section is now ready to be pasted into your `Readme.md` file. Let me know if you need further adjustments!


## License

This project is released under the MIT License. See the [LICENSE](LICENSE) file for more information.
    