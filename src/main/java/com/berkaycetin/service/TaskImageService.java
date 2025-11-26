package com.berkaycetin.service;

import com.berkaycetin.entities.Task;
import com.berkaycetin.entities.TaskImage;
import com.berkaycetin.repository.TaskImageRepository;
import com.berkaycetin.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Service
public class TaskImageService {

    @Autowired
    private TaskImageRepository taskImageRepository;

    @Autowired
    private TaskRepository taskRepository;

    public TaskImage uploadTaskImage(Long taskId, MultipartFile file) throws IOException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        TaskImage taskImage = new TaskImage();
        taskImage.setTask(task);
        taskImage.setUploadDate(new java.sql.Date(System.currentTimeMillis()));


        String base64 = Base64.getEncoder().encodeToString(file.getBytes());
        taskImage.setImage(base64.getBytes());

        return taskImageRepository.save(taskImage);
    }
}

