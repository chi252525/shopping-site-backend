package com.shopping.shopping_site_backend;

import java.util.Base64;

public class Base64Example {
  public static void main(String[] args) {
    String username = "user";
    String password = "password";
    String auth = username + ":" + password;
    String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
    System.out.println(encodedAuth); // 输出: dXNlcjpwYXNzd29yZA==
  }
}
