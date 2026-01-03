package com.marketplace.catalog_service.controller;


import com.marketplace.catalog_service.dto.AdminProductDto;
import com.marketplace.catalog_service.dto.AdminProductDtoResponse;
import com.marketplace.catalog_service.dto.UpdateProduct;
import com.marketplace.catalog_service.service.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;



    public ProductController(ProductServiceImpl productServiceImpl){
        this.productServiceImpl = productServiceImpl;

    }


//    @PostMapping
//    public ResponseEntity<AdminProductDto>createProduct(@RequestBody AdminProductDtoResponse request){
//        AdminProductDto adminProductDto = productServiceImpl.createProduct(request);
//        return ResponseEntity.ok(adminProductDto);
//    }
//
//    @GetMapping("/{productId}")
//    public ResponseEntity<AdminProductDto>getById(@PathVariable Long productId){
//        AdminProductDto adminProductDto = productServiceImpl.getProductById(productId);
//        return ResponseEntity.ok(adminProductDto);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<AdminProductDto>> getAllProducts(){
//
//        return ResponseEntity.ok(productServiceImpl.getAllProducts());
//    }
//
//
//    @PatchMapping("/update/{productId}")
//    public ResponseEntity<AdminProductDto> updateProduct(@PathVariable Long productId, @RequestBody UpdateProduct update){
//
//        AdminProductDto updateData = productServiceImpl.updateProduct(productId,update);
//        return ResponseEntity.ok(productServiceImpl.getProductById(productId));
//
//    }
//
//@DeleteMapping("/remove/{productId}")
//public ResponseEntity<Void>deleteProduct(@PathVariable Long productId){
//    productServiceImpl.deleteProduct(productId);
//    return ResponseEntity.noContent().build();
//
//
//}
//





}
