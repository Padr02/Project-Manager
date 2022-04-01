package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import com.example.application.data.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class UserEntity extends AbstractEntity {

    @Column (nullable = false ,unique = true)
    private String username;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role; // måste kollas upp

    @Column(nullable = false)
    private String passwordSalt;

    //@Column(nullable = false)
    //private String passwordHash;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<TaskEntity> tasks;


    public UserEntity(String username, RoleEnum role, String passwordSalt) {
        this.username = username;
        this.role = role;
        this.passwordSalt = passwordSalt;
    }
}




