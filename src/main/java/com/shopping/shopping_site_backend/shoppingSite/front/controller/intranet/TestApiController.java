package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestApiController {

  @GetMapping("/get")
  public ResponseEntity<Object> getProducts() {
    System.out.println("GET API");
    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }

  // OPTIONS API
  @RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
  public ResponseEntity<Object> options() {
    System.out.println("options API");
    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }
}
