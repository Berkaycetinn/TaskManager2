package com.berkaycetin.repository;

import com.berkaycetin.entities.Task;
import com.berkaycetin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<Task> findById(Long id);
}
