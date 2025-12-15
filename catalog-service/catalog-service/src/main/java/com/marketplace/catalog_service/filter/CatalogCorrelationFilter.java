package com.marketplace.catalog_service.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CatalogCorrelationFilter extends OncePerRequestFilter implements Ordered {

    private static final String CORRELATION_HEADER_ID = "X-Correlation-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String correlationId = request.getHeader(CORRELATION_HEADER_ID);


        if(correlationId==null||correlationId.isBlank()){
            correlationId= UUID.randomUUID().toString();
        }

        MDC.put(CORRELATION_HEADER_ID,correlationId);
        response.setHeader(CORRELATION_HEADER_ID,correlationId);

        try{
            filterChain.doFilter(request,response);
        }
        finally {
            MDC.remove(CORRELATION_HEADER_ID);
        }


    }

    @Override
    public int getOrder() {
        return -100;
    }
}
