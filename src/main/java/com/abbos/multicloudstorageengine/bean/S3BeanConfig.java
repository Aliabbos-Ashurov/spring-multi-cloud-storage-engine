package com.abbos.multicloudstorageengine.bean;

import com.abbos.multicloudstorageengine.config.S3Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * @author Aliabbos Ashurov
 * @since 10/February/2025  16:43
 **/
@Configuration
public class S3BeanConfig {

    @Bean
    public S3Client s3Client(S3Config s3Config) {
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(s3Config.getAccessKey(), s3Config.getSecretKey());
        return S3Client.builder()
                .region(Region.of(s3Config.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .build();
    }
}
