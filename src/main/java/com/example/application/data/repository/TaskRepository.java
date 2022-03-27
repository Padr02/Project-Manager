package com.example.application.data.repository;

import com.example.application.data.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    @Query("select a from TaskEntity a where lower(a.owner.username) like lower(concat( '%', :searchTerm, '%')) ")
    List<TaskEntity> search(@Param("searchTerm") String searchTerm);
   /* @Query("select t from TaskEntity t where lower(t.title) like lower(concat( '%', :searchTerm,'%'))")
    List<TaskEntity> search(@Param("searchTerm") String searchTerm);*/
}

    //List<TaskEntity> findByOwner_username(String title);
    //List<TaskEntity> getTaskList();


