package com.marketplace.catalog_service.service;

import com.marketplace.catalog_service.dto.*;
import com.marketplace.catalog_service.dto.Internal.*;
import com.marketplace.catalog_service.enums.Status;

import java.util.List;

public interface ProductService {
AdminProductDtoResponse createProduct (AdminProductDto adminProductDto);
AdminProductUpdateDto updateProduct (Long id , AdminProductDto adminProductDto );

PublicProductResponse activateProduct(Long productId);
PublicProductResponse deactivateProduct(Long productId);

InternalProductDto increaseStock(Long productId, Integer quantity);

InternalProductDto decreaseStock(Long productId, Integer quantity);

InternalProductDto getProductById(Long productId);

 List<InternalProductDto> getProductByStatus(Status status);

List<InternalProductDto> getProductByIds(List<Long>ids);

void validateStockAvailability(Long productId, Integer quantity );



// inter-service for order generation

 ValidateProductsResponse validateProductsForOrder(ValidateProductsRequest validateProductsRequest);
 void updateProductStock(List<ProductQuantity>stockUpdateInternals);


}
