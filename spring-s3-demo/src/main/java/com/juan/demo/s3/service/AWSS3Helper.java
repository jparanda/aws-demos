package com.juan.demo.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class AWSS3Helper {

    @Value("${aws.s3.bucket}")
    private String awsS3BucketName;

    private final AmazonS3 amazonS3;

    public AWSS3Helper(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void uploadFile(String fileName, File file) throws IOException {
        log.debug("Starting upload file with name ---> {}", file.getName());
        final PutObjectRequest putObjectRequest = new PutObjectRequest(awsS3BucketName, fileName, file);
        amazonS3.putObject(putObjectRequest);
        log.debug("File {} was uploaded successfully", file);
    }

}
