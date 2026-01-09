package com.marketplace.order_service.client;


import com.marketplace.order_service.dto.internal.InternalProductDto;
import com.marketplace.order_service.exception.CatalogServiceNotRespondingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CatalogClientFallbackFactory implements FallbackFactory<CatalogClient> {

    private static final Logger log = LoggerFactory.getLogger(CatalogClientFallbackFactory.class);


    @Override
    public CatalogClient create(Throwable cause) {
        return new CatalogClient() {
            @Override
            public InternalProductDto getProductById(Long productId) {
                log.error("Fallback Triggered for Catalog Client for productId={} | Reason={}",productId,cause.getMessage());

                throw new CatalogServiceNotRespondingException("Catalog-Service not responding");
            }
        };
    }
}
