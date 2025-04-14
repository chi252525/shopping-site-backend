package com.shopping.shopping_site_backend.infra.sys.spring.controller.auth;

import com.shopping.shopping_site_backend.infra.sys.spring.service.GoogleDriveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "http://localhost:9000") // 允許 Quasar 前端跨域
@RequiredArgsConstructor
public class FileController {

  private final GoogleDriveService googleDriveService;

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(
      @RequestParam("file") MultipartFile file, OAuth2AuthenticationToken authentication) {
    try {
      String url = googleDriveService.uploadFile(file, authentication);
      return ResponseEntity.ok(url);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }
}
