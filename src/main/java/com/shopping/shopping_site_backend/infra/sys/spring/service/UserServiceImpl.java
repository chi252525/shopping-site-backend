package com.shopping.shopping_site_backend.infra.sys.spring.service;

import com.shopping.shopping_site_backend.infra.dataprovider.entity.member.GoogleUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public GoogleUser getUserByOAuth2User(OAuth2User oAuth2User) {
        String id = oAuth2User.getAttribute("sub");
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");

        return new GoogleUser(id, name, email);
    }
}
