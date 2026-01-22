package com.marketplace.api_gateway.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {



    @Bean(name = "loadBalancerBuilder")
    @LoadBalanced
    public WebClient.Builder loadBalancerBuilder(){
        return WebClient.builder();
    }

}
