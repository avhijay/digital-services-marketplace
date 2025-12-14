package com.marketplace.api_gateway.filter;


import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Component
public class CorrelationIdGlobalFilter implements GlobalFilter , Ordered {


    private static final String CORRELATION_ID_HEADER= "X-Correlation-Id";



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        String correlationId= request.getHeaders().getFirst(CORRELATION_ID_HEADER);

        if (correlationId==null||correlationId.isBlank()){
            correlationId= UUID.randomUUID().toString();
        }

        ServerHttpRequest request1=request.mutate().header(CORRELATION_ID_HEADER,correlationId)
                .build();

exchange.getResponse().getHeaders().add(CORRELATION_ID_HEADER,correlationId);


        System.out.println("CorrelationId = " + correlationId);



        return chain.filter(exchange.mutate().request(request1).build());
    }





    @Override
    public int getOrder() {
        return -1;
    }
}