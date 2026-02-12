package com.marketplace.api_gateway.security;


import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain (ServerHttpSecurity security){


        security.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange-> {
                    exchange.pathMatchers("/auth/**").permitAll()
                            .pathMatchers("/actuator/**").permitAll()

                            .pathMatchers(HttpMethod.GET,"/payment/**")
                            .hasAuthority("SCOPE_payment.read")

                            .pathMatchers(HttpMethod.POST,"/payments/**")
                            .hasAuthority("SCOPE_payment.write")


                            .anyExchange().authenticated();

                });
        security.oauth2ResourceServer(oauth2->{
            oauth2.jwt(jwt->{

                jwt.jwtDecoder(jwtDecoder());
            });

            oauth2.authenticationEntryPoint((exchange, ex) -> {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
                    }).accessDeniedHandler((exchange, denied) -> {

                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        return exchange.getResponse().setComplete();
            });
        });

        return security.build();


    }


    @Bean
    public ReactiveJwtDecoder
    jwtDecoder(){


        return NimbusReactiveJwtDecoder
                .withJwkSetUri("")
                .build();
    }


}
