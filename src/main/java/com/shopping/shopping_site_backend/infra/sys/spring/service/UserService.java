package com.shopping.shopping_site_backend.infra.sys.spring.service;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.member.GoogleUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService {

    GoogleUser getUserByOAuth2User(OAuth2User oAuth2User);
}
