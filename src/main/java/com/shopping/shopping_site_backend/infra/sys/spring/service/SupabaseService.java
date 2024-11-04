package com.shopping.shopping_site_backend.infra.sys.spring.service;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.shopper.GoogleUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SupabaseService {

  @Autowired private RestTemplate restTemplate;

  private final String SUPABASE_URL =
      "https://https://cmshrobcnfymbtafeicp.supabase.co/rest/v1/<YOUR_TABLE>";
  private final String API_KEY = "<YOUR_SUPABASE_API_KEY>";

  public List<GoogleUser> getUsers() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + API_KEY);
    headers.set("apikey", API_KEY);

    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<List<GoogleUser>> response =
        restTemplate.exchange(
            SUPABASE_URL,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<List<GoogleUser>>() {});

    return response.getBody();
  }
}
