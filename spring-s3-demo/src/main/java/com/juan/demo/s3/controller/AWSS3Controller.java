package com.juan.demo.s3.controller;

import com.juan.demo.s3.service.AWSS3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3/v1")
public class AWSS3Controller {

    private final AWSS3Service awss3Service;

    public AWSS3Controller(AWSS3Service awss3Service){
        this.awss3Service = awss3Service;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        awss3Service.uploadFile(file);
        String response = String.format("The file %s was uploaded successfully", file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
