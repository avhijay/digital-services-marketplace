package com.marketplace.auth_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain mySecurityFilterChain(HttpSecurity httpSecurity) throws  Exception{

        httpSecurity.csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(authorization-> authorization.requestMatchers("/oauth2/**").
                        permitAll()
                        .anyRequest().authenticated()

                ).formLogin(Customizer.withDefaults());
        return httpSecurity.build();


    }

}
