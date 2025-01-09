package com.shopping.shopping_site_backend.shoppingSite.front.api.intranet.image;

import com.shopping.shopping_site_backend.infra.sys.spring.service.PCloudUploadService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/upload")
public class ImageController {

  @Autowired
  private PCloudUploadService pCloudUploadService;

  @PostMapping("/image")
  public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("accessToken") String accessToken) {
    try {
      String result = pCloudUploadService.uploadFile(file,accessToken);
      return ResponseEntity.ok("File uploaded successfully: " + result);
    } catch (IOException e) {
      return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
    }
  }
}
