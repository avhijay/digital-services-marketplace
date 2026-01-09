package com.marketplace.catalog_service.controller;


import com.marketplace.catalog_service.dto.AdminProductDto;
import com.marketplace.catalog_service.dto.AdminProductDtoResponse;
import com.marketplace.catalog_service.dto.Internal.InternalProductDto;
import com.marketplace.catalog_service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    public ProductController(ProductService productService){
        this.productService=productService;
    }


    @PostMapping
    public ResponseEntity<AdminProductDtoResponse> createProduct (@RequestBody AdminProductDto adminProductDto ){

      AdminProductDtoResponse adminProductDtoResponse=   productService.createProduct(adminProductDto);
      URI location = URI.create("/products"+adminProductDtoResponse.id());
      return ResponseEntity.created(location).body(adminProductDtoResponse);

    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<InternalProductDto> getProductById(@PathVariable Long productId){
        InternalProductDto internalProductDto = productService.getProductById(productId);
        return ResponseEntity.ok(internalProductDto);
    }


    @PatchMapping("/increase/stock/")
    public ResponseEntity<InternalProductDto> increaseStock(@RequestBody InternalProductDto internalProductDto){

        InternalProductDto internalProductDto1 = productService.increaseStock(internalProductDto.id(), internalProductDto.stock());
        return ResponseEntity.ok(internalProductDto1);

    }





    @PatchMapping("/decrease/stock/")
    public ResponseEntity<InternalProductDto> decreaseStock(@RequestBody InternalProductDto internalProductDto){

        InternalProductDto internalProductDto1 = productService.decreaseStock(internalProductDto.id(), internalProductDto.stock());
        return ResponseEntity.ok(internalProductDto1);

    }


}
