package com.example.application.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AbstractEntity {
    //private String firstName;
    //private String lastName;
    //private String email;
    private String username;
    private String passwordSalt;
    private String passwordHash;
    private RoleEnum role;
    //@ManyToOne(fetch = FetchType.EAGER)
    //private TaskEntity tasks;

/*
    public UserEntity(String username, String password) {
        this.username = username;
        this.passwordSalt = RandomStringUtils.random(32);
        this.passwordHash = DigestUtils.(password+passwordSalt);
    }*/

    public UserEntity(@Autowired String username, String password, RoleEnum role) {
        this.username = username;
        this.role = role;
        this.passwordSalt= RandomStringUtils.random(32);
        this.passwordHash= DigestUtils.sha1Hex(password);
    }


    public boolean checkPassword(String password){
        return DigestUtils.sha1Hex(password+passwordSalt).equals(passwordHash);
    }
}




