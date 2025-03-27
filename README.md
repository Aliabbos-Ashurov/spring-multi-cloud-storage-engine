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
      enabled: ${AWS_ENABLED:false}
      access-key: ${AWS_ACCESS_KEY:m}
      secret-key: ${AWS_SECRET_KEY:k}
      bucket-name: ${AWS_BUCKET_NAME:bucket-name}
      region: ${AWS_REGION:region}
    gcs:
      enabled: ${GCS_ENABLED:false}
      credentials-path: ${GCS_CREDENTIALS_PATH:/path/to/gcs/credentials.json}
      project-id: ${GCS_PROJECT_ID:your-gcs-project-id}
      bucket-name: ${GCS_BUCKET_NAME:your-gcs-bucket}
    azure:
      enabled: ${AZURE_ENABLED:false}
      account-name: ${AZURE_ACCOUNT_NAME:your-azure-account}
      account-key: ${AZURE_ACCOUNT_KEY:your-azure-key}
      container-name: ${AZURE_CONTAINER_NAME:your-container}
   ````

## License

This project is released under the MIT License. See the [LICENSE](LICENSE) file for more information.
    