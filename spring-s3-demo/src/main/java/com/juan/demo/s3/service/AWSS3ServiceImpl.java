package com.juan.demo.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class AWSS3ServiceImpl implements AWSS3Service {

    private final AWSS3Helper awss3Helper;

    public AWSS3ServiceImpl(AWSS3Helper awss3Helper) {
        this.awss3Helper = awss3Helper;
    }

    @Override
    public void uploadFile(MultipartFile multipartFile) {
        try {
            final File mainFile = convertMultiPartFileToFile(multipartFile);
            String newFileName = UUID.randomUUID() + "_" + mainFile.getName();
            awss3Helper.uploadFile(newFileName, mainFile);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            log.error("Error converting the multipartFile to file", ex);
        }
        return file;
    }


}
