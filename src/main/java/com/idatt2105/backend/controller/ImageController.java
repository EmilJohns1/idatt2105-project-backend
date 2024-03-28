package com.idatt2105.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.idatt2105.backend.service.AmazonClient;

@RestController
@RequestMapping("/api/storage")
public class ImageController {
  private AmazonClient amazonClient;

  @Autowired
  ImageController(AmazonClient amazonClient) {
    this.amazonClient = amazonClient;
  }

  @PostMapping("/uploadFile")
  public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file) {
    try {
      String imageUrl = this.amazonClient.uploadFile(file);
      return ResponseEntity.ok(imageUrl);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @DeleteMapping("/deleteFile")
  public String deleteFile(@RequestBody Map<String, String> payload) {
    String fileUrl = payload.get("url");
    return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
  }
}
