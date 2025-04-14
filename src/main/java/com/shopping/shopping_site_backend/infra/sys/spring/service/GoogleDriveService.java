package com.shopping.shopping_site_backend.infra.sys.spring.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Builder;
import com.google.api.services.drive.model.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GoogleDriveService {

  private static final String APPLICATION_NAME = "My Spring Boot App";

  public Drive getDriveService(OAuth2AuthenticationToken authentication) throws IOException, GeneralSecurityException {
    OAuth2User user = authentication.getPrincipal();
    String accessToken = user.getName();//TODO: get access token from OAuth2AuthenticationToken

    HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

    return new Builder(httpTransport, jsonFactory,
        request -> request.getHeaders().setAuthorization("Bearer " + accessToken)).setApplicationName(APPLICATION_NAME).build();
  }

  public String uploadFile(MultipartFile file, OAuth2AuthenticationToken authentication) throws IOException, GeneralSecurityException {
    Drive driveService = getDriveService(authentication);

    File fileMetadata = new File();
    fileMetadata.setName(file.getOriginalFilename());

    InputStreamContent mediaContent = new InputStreamContent(file.getContentType(), file.getInputStream());
    File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
        .setFields("id")
        .execute();

    return uploadedFile.getId();
  }
}