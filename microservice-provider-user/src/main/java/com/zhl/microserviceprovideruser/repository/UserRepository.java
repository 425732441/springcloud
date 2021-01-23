package com.zhl.microserviceprovideruser.repository;

import com.zhl.microserviceprovideruser.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {
}
