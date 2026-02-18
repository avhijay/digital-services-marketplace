package com.marketplace.api_gateway.rateLimiter;


import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UserKeyResolver implements KeyResolver {



    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return exchange.getPrincipal().map(principal -> principal.getName());
    }

    @Bean
    public RedisRateLimiter rateLimiter(){
        return new RedisRateLimiter(5,10);
    }

    @Bean
    public ErrorWebExceptionHandler rateLimitExceptionHandler(){
        return ((exchange, ex) -> {

            if (ex instanceof ResponseStatusException && ((ResponseStatusException)ex).getStatusCode()== HttpStatus.TOO_MANY_REQUESTS){
                exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap("Too many request".getBytes())));
            }

            return Mono.error(ex);
        });
    }

}
