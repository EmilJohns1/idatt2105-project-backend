package com.idatt2105.backend.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import jakarta.annotation.PostConstruct;

/** Service for handling Amazon S3 file uploads and deletions. */
@Service
public class AmazonClient {

  @Autowired private Environment env;

  private String endpointUrl;
  private String accessKey;
  private String secretKey;
  private String bucketName;

  /** Initializes the AmazonClient with the necessary environment variables. */
  @PostConstruct
  private void init() {
    endpointUrl = env.getProperty("ENDPOINT_URL");
    accessKey = env.getProperty("ACCESS_KEY");
    secretKey = env.getProperty("SECRET_KEY");
    bucketName = env.getProperty("BUCKET_NAME");
  }

  /**
   * Converts a MultipartFile to a File.
   *
   * @param file MultipartFile to convert.
   * @return File representation of the MultipartFile.
   * @throws IOException if the file cannot be converted.
   */
  private File convertMultiPartToFile(MultipartFile file) throws IOException {
    File convFile = new File(file.getOriginalFilename());
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(file.getBytes());
    fos.close();
    return convFile;
  }

  /**
   * Generates a unique file name for a MultipartFile.
   *
   * @param multiPart MultipartFile to generate a file name for.
   * @return Unique file name for the MultipartFile.
   */
  private String generateFileName(MultipartFile multiPart) {
    String originalFileName = multiPart.getOriginalFilename();
    String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
    String uniqueId = UUID.randomUUID().toString();
    return uniqueId + "_" + originalFileName;
  }

  /**
   * Uploads a file to an S3 bucket.
   *
   * @param fileName Name of the file to upload.
   * @param file File to upload.
   */
  private void uploadFileToS3Bucket(String fileName, File file) {
    BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
    AmazonS3 s3client =
        AmazonS3ClientBuilder.standard()
            .withRegion("eu-north-1")
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();

    s3client.putObject(new PutObjectRequest(bucketName, fileName, file));
  }

  /**
   * Uploads a MultipartFile to an S3 bucket.
   *
   * @param multipartFile MultipartFile to upload.
   * @return URL of the uploaded file.
   */
  public String uploadFile(MultipartFile multipartFile) {
    String fileUrl = "";
    try {
      File file = convertMultiPartToFile(multipartFile);
      String fileName = generateFileName(multipartFile);
      fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
      uploadFileToS3Bucket(fileName, file);
      file.delete();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return fileUrl;
  }

  /**
   * Deletes a file from an S3 bucket.
   *
   * @param fileUrl URL of the file to delete.
   * @return Message indicating the success of the deletion.
   */
  public String deleteFileFromS3Bucket(String fileUrl) {
    String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
    String bucketName = fileUrl.substring(fileUrl.indexOf("://") + 3, fileUrl.indexOf(".s3."));
    BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
    AmazonS3 s3client =
        AmazonS3ClientBuilder.standard()
            .withRegion("eu-north-1")
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();

    s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    return "Successfully deleted";
  }
}
