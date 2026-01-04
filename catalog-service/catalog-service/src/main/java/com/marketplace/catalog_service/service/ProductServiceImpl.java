package com.marketplace.catalog_service.service;


import com.marketplace.catalog_service.dto.*;
import com.marketplace.catalog_service.entity.Catalog;
import com.marketplace.catalog_service.enums.Status;
import com.marketplace.catalog_service.exception.InsufficientStockException;
import com.marketplace.catalog_service.exception.ProductNotFoundException;
import com.marketplace.catalog_service.repository.CatalogRepository;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import java.nio.file.ProviderNotFoundException;
import java.util.List;

@Service
public class ProductServiceImpl implements  ProductService{

    private final CatalogRepository catalogRepository;
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(CatalogRepository catalogRepository){
        this.catalogRepository= catalogRepository;
    }


    @Override
    public AdminProductDtoResponse createProduct(AdminProductDto adminProductDto) {
        log.info("Request received by Service ");
        Catalog newProduct = new Catalog();
        log.debug("Creating Product as per the request");
        Catalog  product =  catalogRepository.save(mapToEntityFromDto(adminProductDto));
        log.info("Order creation : Success with Id={}",newProduct.getId());
      return mapToDtoFromEntity(product);

    }

    @Override
    public AdminProductUpdateDto updateProduct(Long id, AdminProductDto adminProductDto) {
        Catalog product = getProductByIdInterService(id);
        product.setStatus(adminProductDto.status());
        product.setDescription(adminProductDto.description());
        product.setStock(adminProductDto.stock());
        product.setName(adminProductDto.name());
        product.setCurrency(adminProductDto.currency());
        product.setPrice(adminProductDto.price());
        catalogRepository.save(product);
        return mapToDtoFromEntityForUpdate (product);
    }


    @Override
    public PublicProductResponse activateProduct(Long productId) {

        Catalog product = getProductByIdInterService(productId);
        product.setStatus(Status.ACTIVE);
        return mapToPublicDto(product);

    }

    @Override
    public PublicProductResponse deactivateProduct(Long productId) {
        Catalog product = getProductByIdInterService(productId);
        product.setStatus(Status.INACTIVE);
        return mapToPublicDto(product);
    }

    @Override
    public InternalOrderDto increaseStock(Long productId, Integer quantity) {
      Catalog product = getProductByIdInterService(productId);
      Integer total = product.getStock()+quantity;
      product.setStock(total);
      return mapToInternalDto(product);

    }

    @Override
    public InternalOrderDto decreaseStock(Long productId, Integer quantity) {


        Catalog product = getProductByIdInterService(productId);
        if (quantity>product.getStock()){
            throw new InsufficientStockException("Quantity can not be more than the current Stock quantity | Current Stock quantity :  "+product.getStock());
        }
        Integer total = product.getStock()-quantity;
        product.setStock(total);
        return mapToInternalDto(product);

    }

    @Override
    public InternalOrderDto getProductById(Long productId) {

        Catalog product = catalogRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("No product exist with id : "+productId));
        return mapToInternalDto(product);
    }

    private Catalog getProductByIdInterService(Long productId){
        return catalogRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("No product exist with id : "+productId));
    }

    @Override
    public List<InternalOrderDto> getProductByStatus(Status status) {
        return List.of();
    }

    @Override
    public List<InternalOrderDto> getProductByIds(List<Long> ids) {
        return List.of();
    }



    @Override
    public void validateStockAvailability(Long productId, Integer quantity) {

    }



    private Catalog mapToEntityFromDto (AdminProductDto adminProductDto){


        Catalog newProduct = new Catalog();
        newProduct.setDescription(adminProductDto.description());
        newProduct.setCurrency(adminProductDto.currency());
        newProduct.setName(adminProductDto.name());
        newProduct.setPrice(adminProductDto.price());
        newProduct.setDescription(adminProductDto.description());
        newProduct.setStock(adminProductDto.stock());
        newProduct.setStatus(adminProductDto.status());
        return  newProduct;

    }


    private AdminProductDtoResponse mapToDtoFromEntity(Catalog newProduct){
        return  new AdminProductDtoResponse(newProduct.getId(),newProduct.getName(),newProduct.getDescription() , newProduct.getPrice(),newProduct.getCurrency(),newProduct.getStock(),newProduct.getStatus(),newProduct.getCreatedAt());
    }


    private AdminProductUpdateDto mapToDtoFromEntityForUpdate(Catalog newProduct){
        return  new AdminProductUpdateDto(newProduct.getName(),newProduct.getDescription() , newProduct.getPrice(),newProduct.getCurrency(),newProduct.getStock(),newProduct.getStatus());
    }
    private  InternalOrderDto mapToInternalDto(Catalog product){
        return  new InternalOrderDto(product.getId(), product.getName(), product.getPrice(), product.getCurrency(), product.getStock(), product.getStatus());
    }

    private PublicProductResponse mapToPublicDto(Catalog product){
        return  new PublicProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCurrency(), product.getStock(), product.getStatus());
    }
}
