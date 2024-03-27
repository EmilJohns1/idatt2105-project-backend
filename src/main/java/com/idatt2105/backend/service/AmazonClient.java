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

@Service
public class AmazonClient {

  @Autowired private Environment env;

  private String endpointUrl;
  private String accessKey;
  private String secretKey;
  private String bucketName;

  @PostConstruct
  private void init() {
    endpointUrl = env.getProperty("ENDPOINT_URL");
    accessKey = env.getProperty("ACCESS_KEY");
    secretKey = env.getProperty("SECRET_KEY");
    bucketName = env.getProperty("BUCKET_NAME");
  }

  private File convertMultiPartToFile(MultipartFile file) throws IOException {
    File convFile = new File(file.getOriginalFilename());
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(file.getBytes());
    fos.close();
    return convFile;
  }

  private String generateFileName(MultipartFile multiPart) {
    String originalFileName = multiPart.getOriginalFilename();
    String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
    String uniqueId = UUID.randomUUID().toString();
    return uniqueId + "_" + originalFileName;
  }

  private void uploadFileToS3Bucket(String fileName, File file) {
    BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
    AmazonS3 s3client =
        AmazonS3ClientBuilder.standard()
            .withRegion("eu-north-1")
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();

    s3client.putObject(new PutObjectRequest(bucketName, fileName, file));
  }

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
