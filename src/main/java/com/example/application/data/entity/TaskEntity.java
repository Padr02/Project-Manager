package com.example.application.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity extends AbstractEntity {
    @Column()
    private boolean completed;
    @Column()
    private String title;
    @Column
    private String owner;
    @Column()
    private LocalDate startDate;
    @Column()
    private LocalDate deadline;
}
