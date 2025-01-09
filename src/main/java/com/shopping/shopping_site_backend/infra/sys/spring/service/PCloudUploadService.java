package com.shopping.shopping_site_backend.infra.sys.spring.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PCloudUploadService {
  @Value("${pcloud.api_url}")
  private String apiUrl;
  private final String FOLDER_ID = "0"; // 可以是根目錄或某個子文件夾 ID

  public String uploadFile(MultipartFile file,String accessToken) throws IOException {
    // 使用 RestTemplate 發送請求
    RestTemplate restTemplate = new RestTemplate();

    // 設置 API 參數
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    // 文件數據
    ByteArrayInputStream fileInputStream = new ByteArrayInputStream(file.getBytes());
    HttpEntity<ByteArrayInputStream> fileEntity = new HttpEntity<>(fileInputStream, headers);

    // 構造上傳請求
    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("file", fileEntity);
    body.add("auth", accessToken);
    body.add("folderid", FOLDER_ID);

    // 上傳文件並接收響應
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

    return response.getBody();
  }
}