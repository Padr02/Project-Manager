package com.example.application.repository;

import com.example.application.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    //metoder saknas
}
