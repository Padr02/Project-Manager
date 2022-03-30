package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity extends AbstractEntity {

    @Column(nullable = false)
    private boolean completed;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner;

    /* public TaskEntity(boolean completed, String title, LocalDate startDate, LocalDate deadLine, UserEntity owner) {
        this.completed = completed;
    } */
}
