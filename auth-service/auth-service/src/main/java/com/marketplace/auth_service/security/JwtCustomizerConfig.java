package com.marketplace.auth_service.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.List;

public class JwtCustomizerConfig {


    private static final Logger log = LoggerFactory.getLogger(JwtCustomizerConfig.class);

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext>tokenCustomizer(){
        return context ->{
            if ("access_token".equals(context.getTokenType().getValue())){
                log.info("TOKEN_ISSUED subject={}  scope={}",context.getPrincipal().getName(),context.getAuthorizedScopes());

                context.getClaims().claim("roles", List.of("ROLE_ADMIN","ROLE_USER"));
            }
        };
    }


}
