package com.marketplace.catalog_service.controller;


import com.marketplace.catalog_service.dto.ProductDto;
import com.marketplace.catalog_service.dto.ProductRequest;
import com.marketplace.catalog_service.dto.ProductResponse;
import com.marketplace.catalog_service.dto.UpdateProduct;
import com.marketplace.catalog_service.service.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;



    public ProductController(ProductService productService){
        this.productService=productService;

    }


    @PostMapping
    public ResponseEntity<ProductDto>createProduct(@RequestBody ProductRequest request){
        ProductDto productDto = productService.createProduct(request);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto>getById(@PathVariable Long productId){
        ProductDto productDto = productService.getProductById(productId);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts(){

        return ResponseEntity.ok(productService.getAllProducts());
    }


    @PatchMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(  @PathVariable Long productId, @RequestBody UpdateProduct update){

        ProductDto updateData = productService.updateProduct(productId,update);
        return ResponseEntity.ok(productService.getProductById(productId));

    }

@DeleteMapping("/remove/{productId}")
public ResponseEntity<Void>deleteProduct(@PathVariable Long productId){
    productService.deleteProduct(productId);
    return ResponseEntity.noContent().build();


}






}
