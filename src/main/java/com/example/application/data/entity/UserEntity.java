package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import com.example.application.data.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.atmosphere.config.service.Message;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class UserEntity extends AbstractEntity {

    @Column (nullable = false ,unique = true)
    private String username;

    @Email(message = "Email should be valid")
    @Column(nullable = false)
    private String email;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role; // måste kollas upp

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<TaskEntity> tasks;

    @Transient
    @Column(name = "enabled")
    private boolean enabled;

    public UserEntity(String username,String email, RoleEnum role, String password) {
        this.email=email;
        this.username = username;
        this.role = role;
        this.password = password;
        this.enabled = false;
    }
}




