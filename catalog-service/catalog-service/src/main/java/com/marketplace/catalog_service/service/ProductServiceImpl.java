package com.marketplace.catalog_service.service;


import com.marketplace.catalog_service.dto.*;
import com.marketplace.catalog_service.enums.Status;
import com.marketplace.catalog_service.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductServiceImpl implements  ProductService{


    @Override
    public AdminProductDtoResponse createProduct(AdminProductDto adminProductDto) {
        return null;
    }

    @Override
    public AdminProductDtoResponse updateProduct(AdminProductDto adminProductDto) {
        return null;
    }

    @Override
    public PublicProductResponse activateProduct(Long productId) {
        return null;
    }

    @Override
    public PublicProductResponse deactivateProduct(Long productId) {
        return null;
    }

    @Override
    public PublicProductResponse increaseStock(Long productId, Integer quantity) {
        return null;
    }

    @Override
    public PublicProductResponse decreaseStock(Long productId, Integer quantity) {
        return null;
    }

    @Override
    public InternalOrderDto getProductById(Long productId) {
        return null;
    }

    @Override
    public InternalOrderDto getProductByStatus(Status status) {
        return null;
    }

    @Override
    public List<InternalOrderDto> getProductByIds(List<Long> ids) {
        return List.of();
    }

    @Override
    public PublicProductResponse validateStockAvailability(Long productId, Integer quantity) {
        return null;
    }
}
