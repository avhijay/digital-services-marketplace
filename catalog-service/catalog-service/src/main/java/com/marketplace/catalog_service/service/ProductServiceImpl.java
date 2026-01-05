package com.marketplace.catalog_service.service;


import com.marketplace.catalog_service.dto.*;
import com.marketplace.catalog_service.entity.Catalog;
import com.marketplace.catalog_service.enums.Status;
import com.marketplace.catalog_service.exception.InsufficientStockException;
import com.marketplace.catalog_service.exception.ProductNotFoundException;
import com.marketplace.catalog_service.repository.CatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements  ProductService{

    private final CatalogRepository catalogRepository;
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(CatalogRepository catalogRepository){
        this.catalogRepository= catalogRepository;
    }


    @Override
    public AdminProductDtoResponse createProduct(AdminProductDto adminProductDto) {
        log.info("Request received by Service : creating product..");

        log.debug("Creating Product as per the request");
        Catalog  product =  catalogRepository.save(mapToEntityFromDto(adminProductDto));
        log.info("Order creation : Success with Id={}",product.getId());
      return mapToDtoFromEntity(product);

    }

    @Override
    public AdminProductUpdateDto updateProduct(Long id, AdminProductDto adminProductDto) {
        log.info("Request received  by the Service : updating product..");
        log.debug("Validating if the product exist by id ={} :",id);
        Catalog product = getProductByIdInterService(id);
        log.info("Validation of product successful ={}",id);
        product.setStatus(adminProductDto.status());
        product.setDescription(adminProductDto.description());
        product.setStock(adminProductDto.stock());
        product.setName(adminProductDto.name());
        product.setCurrency(adminProductDto.currency());
        product.setPrice(adminProductDto.price());
        catalogRepository.save(product);
        log.info("Updating of product : success with id={} ",id);
        return mapToDtoFromEntityForUpdate (product);
    }


    @Override
    public PublicProductResponse activateProduct(Long productId) {
log.info("Request received : activateProduct :{}",productId);
        Catalog product = getProductByIdInterService(productId);
        product.setStatus(Status.ACTIVE);
        log.info("Activation of product : success with id={}",productId);
        return mapToPublicDto(product);

    }

    @Override
    public PublicProductResponse deactivateProduct(Long productId) {
        log.info("Request received : deactivateProduct :{}",productId);
        Catalog product = getProductByIdInterService(productId);
        product.setStatus(Status.INACTIVE);
        log.info("Deactivation of product : success with id={}",productId);
        return mapToPublicDto(product);
    }

    @Override
    public InternalProductDto increaseStock(Long productId, Integer quantity) {
        log.info("Request received : increaseStock :{}",productId);
      Catalog product = getProductByIdInterService(productId);
      Integer total = product.getStock()+quantity;
      product.setStock(total);
        log.info("IncreaseStock  of product : success with id={}",productId);
      return mapToInternalDto(product);

    }

    @Override
    public InternalProductDto decreaseStock(Long productId, Integer quantity) {


        Catalog product = getProductByIdInterService(productId);
        if (quantity>product.getStock()){
            throw new InsufficientStockException("Quantity can not be more than the current Stock quantity | Current Stock quantity :  "+product.getStock());
        }
        Integer total = product.getStock()-quantity;
        product.setStock(total);
        return mapToInternalDto(product);

    }

    @Override
    public InternalProductDto getProductById(Long productId) {

        Catalog product = catalogRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("No product exist with id : "+productId));
        return mapToInternalDto(product);
    }

    private Catalog getProductByIdInterService(Long productId){
        return catalogRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("No product exist with id : "+productId));
    }

    @Override
    public List<InternalProductDto> getProductByStatus(Status status) {
        List<Catalog> products = catalogRepository.findByStatus(status);
        List<InternalProductDto> internalProductDto;
        internalProductDto=products.stream().map(this::mapToInternalDto).toList();
        return internalProductDto;
    }

    @Override
    public List<InternalProductDto> getProductByIds(List<Long> ids) {
        List<Catalog>products = catalogRepository.findByIdIn(ids);
        List<InternalProductDto> productDtos;
        productDtos = products.stream()
                .map(this::mapToInternalDto).toList();
        return productDtos;
    }



    @Override
    public void validateStockAvailability(Long productId, Integer quantity) {
        Catalog product = getProductByIdInterService(productId);
       if(product.getStock()< quantity){
           throw  new InsufficientStockException("Product quantity not matched with the current stock :"+productId);
       }



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
    private InternalProductDto mapToInternalDto(Catalog product){
        return  new InternalProductDto(product.getId(), product.getName(), product.getPrice(), product.getCurrency(), product.getStock(), product.getStatus());
    }

    private PublicProductResponse mapToPublicDto(Catalog product){
        return  new PublicProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCurrency(), product.getStock(), product.getStatus());
    }
}
