package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import com.example.application.data.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
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

    @Column(nullable = false)
    private String passwordHash;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<TaskEntity> tasks;

    public UserEntity(String username, String password, RoleEnum role) {
        this.username = username;
        this.role = role;
        this.passwordSalt = RandomStringUtils.random(32);
        this.passwordHash = DigestUtils.sha1Hex(password + passwordSalt);
    }

    public boolean checkPassword(String password){
        return DigestUtils.sha1Hex(password + passwordSalt).equals(passwordHash);
    }
}




