package com.shopping.shopping_site_backend.infra.sys.spring.controller.auth;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/upload")
public class FileUploadController {
  private static final String FOLDER_ID = "1fdHf6jLw89qW5B-ZJHKmZ-PRmpW2q9D5";

  @PostMapping
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      Drive driveService = GoogleDriveUtils.getDriveService();

      File fileMetadata = new File();
      fileMetadata.setName(file.getOriginalFilename());
      fileMetadata.setParents(Collections.singletonList(FOLDER_ID)); // Google Drive folder ID

      FileContent mediaContent = new FileContent(file.getContentType(), convertToFile(file));

      File uploadedFile =
          driveService
              .files()
              .create(fileMetadata, mediaContent)
              .setFields("id, webViewLink")
              .execute();

      return ResponseEntity.ok( uploadedFile.getId());

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Upload failed: " + e.getMessage());
    }
  }

  private java.io.File convertToFile(MultipartFile multipartFile) throws IOException {
    java.io.File convFile =
        new java.io.File(
            System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(multipartFile.getBytes());
    fos.close();
    return convFile;
  }
}
