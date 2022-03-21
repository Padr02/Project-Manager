package com.example.application.model;

import com.example.application.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity extends AbstractEntity {
    private String title;
    private Date startDate;
    private boolean completed;
}
