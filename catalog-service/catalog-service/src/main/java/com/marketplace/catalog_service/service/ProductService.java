package com.marketplace.catalog_service.service;

import com.marketplace.catalog_service.dto.*;
import com.marketplace.catalog_service.enums.Status;

import java.util.List;

public interface ProductService {
AdminProductDtoResponse createProduct (AdminProductDto adminProductDto);
AdminProductUpdateDto updateProduct (Long id , AdminProductDto adminProductDto );

PublicProductResponse activateProduct(Long productId);
PublicProductResponse deactivateProduct(Long productId);

InternalOrderDto increaseStock(Long productId, Integer quantity);

InternalOrderDto decreaseStock(Long productId, Integer quantity);

InternalOrderDto getProductById(Long productId);

 List< InternalOrderDto> getProductByStatus(Status status);

List<InternalOrderDto> getProductByIds(List<Long>ids);

void validateStockAvailability(Long productId, Integer quantity );


}
