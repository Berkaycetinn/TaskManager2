package com.berkaycetin.repository;

import com.berkaycetin.entities.TaskImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskImageRepository extends JpaRepository<TaskImage, Long> {
    List<TaskImage> findByTaskId(Long taskId);
}
