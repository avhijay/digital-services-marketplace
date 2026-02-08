package security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthorizationServerConfig {


    @Bean
    public SecurityFilterChain authorixationSecurityFilterChain(HttpSecurity http) throws Exception {


        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer();

        http
                .securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())

                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())

                .csrf(csrf -> csrf.ignoringRequestMatchers(
                        authorizationServerConfigurer.getEndpointsMatcher()
                ))

                .with(authorizationServerConfigurer, Customizer.withDefaults());
        return http.build();

    }
}
