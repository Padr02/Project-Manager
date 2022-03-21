package com.example.application.model;

import com.example.application.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity extends AbstractEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleEntity> roles = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private TaskEntity tasks;

/*
    public UserEntity(String username, String password) {
        this.username = username;
        this.passwordSalt = RandomStringUtils.random(32);
        this.passwordHash = DigestUtils.(password+passwordSalt);
    }*/
}




