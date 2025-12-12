package com.marketplace.catalog_service.service;


import com.marketplace.catalog_service.dto.ProductDto;
import com.marketplace.catalog_service.dto.ProductRequest;
import com.marketplace.catalog_service.dto.ProductResponse;
import com.marketplace.catalog_service.dto.UpdateProduct;
import com.marketplace.catalog_service.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private static final AtomicLong idGeneration = new AtomicLong(1);
    private final Map<Long , ProductDto> products = new ConcurrentHashMap<>();

    public ProductDto createProduct (ProductRequest request){


        Long productId = idGeneration.getAndIncrement();
        ProductDto newProduct = new ProductDto();
        newProduct.setCategory(request.getCategory());
        newProduct.setName(request.getName());
        newProduct.setPrice(request.getPrice());
        newProduct.setDescription(request.getDescription());
        newProduct.setProductId(productId);
        products.put(productId,newProduct);

        return newProduct;

    }

    public ProductDto getProductById(Long productId){
        if (products.get(productId)==null){
            throw new ProductNotFoundException("No product found with the id : "+productId);
        }
        return  products.get(productId);

    }

    public List<ProductDto> getAllProducts(){
        return  products.values().stream().toList();
    }

    public ProductDto updateProduct(Long productId , UpdateProduct update){


            ProductDto product = getProductById(productId);

            product.setDescription(update.getDescription());
            product.setName(update.getName());
            product.setPrice(update.getPrice());
            products.put(productId, product);


        return product;
    }


    public void deleteProduct(Long productId){

     if (products.get(productId)!=null) {
         products.remove(productId);
     }else{
        throw  new ProductNotFoundException("No product found with the id of :"+ productId);
     }

    }


}
