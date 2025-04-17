package com.shopping.shopping_site_backend.infra.sys.spring.controller.auth;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
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
  public ResponseEntity<String> uploadFile(
      @RequestParam("file") MultipartFile file, @RequestParam("baseSku") String baseSku) {
    try {
      Drive driveService = GoogleDriveUtils.getDriveService();

      // 建立（或取得）指定資料夾
      String folderId = createFolderIfNotExists(driveService, baseSku);

      // 設定檔案 metadata 並上傳
      File fileMetadata = new File();
      fileMetadata.setName(file.getOriginalFilename());
      fileMetadata.setParents(Collections.singletonList(folderId)); // 存進資料夾

      FileContent mediaContent = new FileContent(file.getContentType(), convertToFile(file));

      File uploadedFile =
          driveService
              .files()
              .create(fileMetadata, mediaContent)
              .setFields("id, webViewLink")
              .execute();

      // 設定權限為「公開檢視」
      Permission permission = new Permission();
      permission.setType("anyone");
      permission.setRole("reader");
      driveService.permissions().create(uploadedFile.getId(), permission).execute();

      // 傳回可嵌入圖片網址
      String viewUrl = "https://drive.google.com/uc?id=" + uploadedFile.getId();

      return ResponseEntity.ok(viewUrl);

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

  private String createFolderIfNotExists(Drive driveService, String folderName) throws IOException {
    String folderId = null;

    // 先檢查是否已有同名資料夾
    String query =
        String.format(
            "mimeType='application/vnd.google-apps.folder' and name='%s' and trashed=false",
            folderName);
    FileList result =
        driveService.files().list().setQ(query).setFields("files(id, name)").execute();

    if (!result.getFiles().isEmpty()) {
      folderId = result.getFiles().get(0).getId(); // 用第一個找到的
    } else {
      // 建立新資料夾
      File fileMetadata = new File();
      fileMetadata.setName(folderName);
      fileMetadata.setMimeType("application/vnd.google-apps.folder");

      File folder = driveService.files().create(fileMetadata).setFields("id").execute();

      folderId = folder.getId();
    }

    return folderId;
  }
}
