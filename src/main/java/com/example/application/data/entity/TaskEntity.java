package com.example.application.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
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
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    public TaskEntity() {
    }

    public TaskEntity(boolean completed, String title, LocalDate startDate, LocalDate deadline, UserEntity owner) {
        this.completed = completed;
        this.title = title;
        this.startDate = startDate;
        this.deadline = deadline;
        this.owner = owner;
    }


}
