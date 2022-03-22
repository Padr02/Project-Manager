package com.example.application.data.repository;

import com.example.application.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity getByUsername(String username);

}
