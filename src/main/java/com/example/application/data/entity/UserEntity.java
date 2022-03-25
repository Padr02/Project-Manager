package com.example.application.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class UserEntity extends AbstractEntity {
    @Column (nullable = false, unique = true)
    private String username;

    @Column (nullable = false)
    private RoleEnum role;

    @Column(nullable = false)
    private String passwordSalt;

    @Column(nullable = false)
    private String passwordHash;

    @OneToMany(mappedBy = "owner")
    @JsonIgnoreProperties
    private Set<TaskEntity> tasks;

/*
    public UserEntity(String username, String password) {
        this.username = username;
        this.passwordSalt = RandomStringUtils.random(32);
        this.passwordHash = DigestUtils.(password+passwordSalt);
    }*/

    public UserEntity() {
    }

    public UserEntity(String username, String password, RoleEnum role) {
        this.username = username;
        this.role = role;
        this.passwordSalt= RandomStringUtils.random(32);
        this.passwordHash= DigestUtils.sha1Hex(password+ passwordSalt);
    }

    public boolean checkPassword(String password){
        return  DigestUtils.sha1Hex(password+passwordSalt).equals(passwordHash);
    }

}




