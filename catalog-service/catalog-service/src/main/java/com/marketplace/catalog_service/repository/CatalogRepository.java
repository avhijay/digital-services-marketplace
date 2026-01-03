package com.marketplace.catalog_service.repository;

import com.marketplace.catalog_service.entity.Catalog;
import com.marketplace.catalog_service.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog,Long> {

   List<Catalog> findByIdIn(List<Long> ids);
   List<Catalog> findByStatus(Status status);

   List<Catalog>findByNameContainingIgnoreCase(String name);



}
