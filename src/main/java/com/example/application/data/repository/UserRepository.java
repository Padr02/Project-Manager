package com.example.application.data.repository;

import com.example.application.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
        @Query("select u from UserEntity u where u.username like(:searchTerm) ")
        UserEntity getUserByUsername(@Param("searchTerm") String username);

}
