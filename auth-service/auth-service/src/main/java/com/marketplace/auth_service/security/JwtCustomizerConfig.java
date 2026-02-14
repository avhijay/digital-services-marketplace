package com.marketplace.auth_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.List;

public class JwtCustomizerConfig {

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext>tokenCustomizer(){
        return context ->{
            if ("access_token".equals(context.getTokenType().getValue())){
                context.getClaims().claim("roles", List.of("ROLE_ADMIN","ROLE_USER"));
            }
        };
    }


}
