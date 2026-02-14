package com.marketplace.api_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtAuthenticationConverter {

    @Bean
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtMonoConverter(){
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();

        return jwt->{
            Collection<GrantedAuthority>authorities = new ArrayList<>(converter.convert(jwt));

            List<String> roles = jwt.getClaimAsStringList("roles");


            if(roles!=null){
                roles.forEach(role-> authorities.add(new SimpleGrantedAuthority(role)));

            }
            return Mono.just(new JwtAuthenticationToken(jwt,authorities));

        };


    }


}
