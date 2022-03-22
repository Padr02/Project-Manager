package com.example.application.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity extends AbstractEntity {
    @Column()
    private String title;
    @Column
    private String owner;
    @Column()
    private String startDate;
    @Column()
    private String completed;
}
