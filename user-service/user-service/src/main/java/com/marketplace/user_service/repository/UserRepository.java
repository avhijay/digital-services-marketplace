package com.marketplace.user_service.repository;

import com.marketplace.user_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Long, Users> {

    Optional<Users>findByEmail(String emailId);
    boolean existByEmail(String emailId);


}
