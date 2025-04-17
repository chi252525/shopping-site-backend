package com.shopping.shopping_site_backend.infra.sys.spring.controller.auth;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;

public class GoogleDriveUtils {

  private static final String APPLICATION_NAME = "shopping-site-admin-front";
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  public static Drive getDriveService() throws Exception {
    InputStream serviceAccountStream =
        GoogleDriveUtils.class.getResourceAsStream("/service-account.json");

    if (serviceAccountStream == null) {
      throw new FileNotFoundException("service-account.json not found in resources folder");
    }

    GoogleCredentials credentials =
        ServiceAccountCredentials.fromStream(serviceAccountStream)
            .createScoped(Collections.singletonList("https://www.googleapis.com/auth/drive"));

    HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

    final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    return new Drive.Builder(httpTransport, JSON_FACTORY, requestInitializer)
        .setApplicationName(APPLICATION_NAME)
        .build();
  }
}
