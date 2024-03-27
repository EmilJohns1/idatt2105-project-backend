package com.idatt2105.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.idatt2105.backend.service.AmazonClient;

@RestController
@RequestMapping("/storage/")
public class ImageController {
  private AmazonClient amazonClient;

  @Autowired
  ImageController(AmazonClient amazonClient) {
    this.amazonClient = amazonClient;
  }

  @PostMapping("/uploadFile")
  public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
    try {
      return this.amazonClient.uploadFile(file);
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  @DeleteMapping("/deleteFile")
  public String deleteFile(@RequestBody Map<String, String> payload) {
    String fileUrl = payload.get("url");
    return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
  }
}
