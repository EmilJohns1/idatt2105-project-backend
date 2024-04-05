package com.idatt2105.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.idatt2105.backend.service.AmazonClient;

import io.swagger.v3.oas.annotations.tags.Tag;

/** The ImageController class handles HTTP requests related to image storage. */
@RestController
@Tag(name = "Image", description = "Operations related to image storage")
@RequestMapping("/api/storage")
public class ImageController {
  private AmazonClient amazonClient;

  @Autowired
  ImageController(AmazonClient amazonClient) {
    this.amazonClient = amazonClient;
  }

  /**
   * Uploads a file to the S3 bucket.
   *
   * @param file the file to upload
   * @return a ResponseEntity containing the URL of the uploaded file
   */
  @PostMapping("/uploadFile")
  public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file) {
    try {
      String imageUrl = this.amazonClient.uploadFile(file);
      return ResponseEntity.ok(imageUrl);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  /**
   * Deletes a file from the S3 bucket.
   *
   * @param payload the URL of the file to delete
   * @return a message indicating if the file was deleted successfully
   */
  @DeleteMapping("/deleteFile")
  public String deleteFile(@RequestBody Map<String, String> payload) {
    try {
      this.amazonClient.deleteFileFromS3Bucket(payload.get("fileUrl"));
      return "File deleted successfully";
    } catch (Exception e) {
      return "File deletion failed";
    }
  }
}
