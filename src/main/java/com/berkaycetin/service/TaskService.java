package com.berkaycetin.service;
import java.util.Date;
import com.berkaycetin.entities.Comment;
import com.berkaycetin.entities.CommentImage;
import com.berkaycetin.entities.TaskImage;
import com.berkaycetin.repository.CommentRepository;
import org.springframework.web.multipart.MultipartFile;

import com.berkaycetin.entities.Task;
import com.berkaycetin.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        Task newTask = new Task();
        BeanUtils.copyProperties(task, newTask);
        return taskRepository.save(newTask);
    }


    //  Görev güncelleme
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        // Sadece dolu gelen alanları güncelle
        if (updatedTask.getTitle() != null) existingTask.setTitle(updatedTask.getTitle());
        if (updatedTask.getDescrition() != null) existingTask.setDescrition(updatedTask.getDescrition());
        if (updatedTask.getStatus() != null) existingTask.setStatus(updatedTask.getStatus());
        if (updatedTask.getDueDate() != null) existingTask.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(existingTask);
    }

    //  Görev silme
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    public Comment addCommentImage(Long commentId, MultipartFile imageFile) throws IOException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Yorum bulunamadı"));

        CommentImage img = new CommentImage();
        img.setBase64Image(imageFile.getBytes());
        img.setUploadDate(new Date());
        img.setComment(comment);

        comment.setCommentImage(img);  // tek resim için

        return commentRepository.save(comment);
    }

    //  Task'a resim ekleme
    public void saveTaskImage(Long taskId, MultipartFile file) throws IOException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        TaskImage taskImage = new TaskImage();
        taskImage.setImage(file.getBytes());
        taskImage.setUploadDate(new Date());
        taskImage.setTask(task);

        task.getTaskImages().add(taskImage); // taskImages List<TaskImage> olmalı

        taskRepository.save(task);
    }
}

