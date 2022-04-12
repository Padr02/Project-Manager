package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import com.example.application.data.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class UserEntity extends AbstractEntity {

    @Column (nullable = false ,unique = true)
    @NotBlank
    private String username;

    @Email(message = "Email should be valid")
    @Column(nullable = false)
    @NotBlank
    private String email;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 10)
    private String password;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<TaskEntity> tasks;

    public UserEntity(String username, String email, RoleEnum role, String password) {
        this.email = email;
        this.username = username;
        this.role = role;
        this.password = password;

    }

}




