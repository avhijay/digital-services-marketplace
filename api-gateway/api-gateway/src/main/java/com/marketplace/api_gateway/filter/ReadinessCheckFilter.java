package com.marketplace.api_gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Component
public class ReadinessCheckFilter implements GlobalFilter, Ordered {

    private final WebClient webClient;

    public ReadinessCheckFilter(WebClient.Builder builder) {
        this.webClient = builder.build();
    }



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        if (!path.startsWith("/orders")){
            return chain.filter(exchange);
        }

        return webClient.get()
                .uri("lb://ORDER-SERVICE/actuator/health/readiness")
                .exchangeToMono(response-> {


                    if (response.statusCode().is2xxSuccessful()){
                        return chain.filter(exchange);
                    }

                    return respondServiceUnavailable(exchange);
                }).timeout(Duration.ofSeconds(1))
                .onErrorResume(ex-> respondServiceUnavailable(exchange));

    }



    private  Mono<Void> respondServiceUnavailable(ServerWebExchange exchange){

   ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
        response.getHeaders().add("X-Readiness-State","DOWN");



        byte[] body = """
                { "message": "Order Service temporarily unavailable" }
                """.getBytes();


        return response.writeWith(
                Mono.just(
                        response.bufferFactory().wrap(body)
                )
        );





    }








    @Override
    public int getOrder() {
        return -1;
    }
}
