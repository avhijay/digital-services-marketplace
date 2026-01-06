package com.marketplace.user_service.repository;

import com.marketplace.user_service.entity.Users;
import com.marketplace.user_service.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.catalog.Catalog;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users>findByEmail(String emailId);
    boolean existByEmail(String emailId);
List<Users>findByStatus(Status status);
Users findByUniqueUserId(String userId);


}
