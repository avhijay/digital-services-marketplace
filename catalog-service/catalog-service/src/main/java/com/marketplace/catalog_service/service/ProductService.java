package com.marketplace.catalog_service.service;

import com.marketplace.catalog_service.dto.AdminProductDto;
import com.marketplace.catalog_service.dto.AdminProductDtoResponse;
import com.marketplace.catalog_service.dto.InternalOrderDto;
import com.marketplace.catalog_service.dto.PublicProductResponse;
import com.marketplace.catalog_service.enums.Status;

import java.util.List;

public interface ProductService {
AdminProductDtoResponse createProduct (AdminProductDto adminProductDto);
AdminProductDtoResponse updateProduct (AdminProductDto adminProductDto );

PublicProductResponse activateProduct(Long productId);
PublicProductResponse deactivateProduct(Long productId);

PublicProductResponse increaseStock(Long productId, Integer quantity);

PublicProductResponse decreaseStock(Long productId, Integer quantity);

InternalOrderDto getProductById(Long productId);

InternalOrderDto getProductByStatus(Status status);

List<InternalOrderDto> getProductByIds(List<Long>ids);

PublicProductResponse validateStockAvailability(Long productId, Integer quantity );


}
