package com.example.application.model;

import com.example.application.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends AbstractEntity {
    private String role;
}
