package com.berkaycetin.controller;

import com.berkaycetin.entities.Comment;
import com.berkaycetin.entities.Task;
import com.berkaycetin.entities.TaskImage;
import com.berkaycetin.repository.TaskImageRepository;
import com.berkaycetin.service.CommentService;
import com.berkaycetin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController

public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskImageRepository taskImageRepository;
    @Autowired
    private CommentService commentService;


    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> saveTask(@RequestBody Task newTask) {
        Task savedTask = taskService.saveTask(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }




        @PostMapping("/tasks/{id}/images")
        public ResponseEntity<String> uploadImage(
                @PathVariable Long id,
                @RequestParam("file") MultipartFile file
        ) throws IOException {
            taskService.saveTaskImage(id, file); // ✅ metod burada çağrılıyor
            return ResponseEntity.ok("Image uploaded successfully");
        }

    @GetMapping("/tasks/{taskId}/images")
    public ResponseEntity<List<TaskImage>> getImagesByTask(@PathVariable Long taskId) {
        List<TaskImage> images = taskImageRepository.findByTaskId(taskId);
        return ResponseEntity.ok(images);
    }

    @PutMapping("tasks/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task updatedTask) {

        Task task = taskService.updateTask(id, updatedTask);
        return ResponseEntity.ok(task);
    }

    // 🔸 Görev sil
    @DeleteMapping("tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully!");
    }


//göreve yorum ekleme

    @PostMapping("/tasks/{taskId}/comments")
    public ResponseEntity<Comment> addComment(
            @RequestParam Long taskId,
            @RequestParam Long userId,
            @RequestParam String content,
            @RequestParam(required = false) MultipartFile imageFile
    ) {
        try {
            Comment savedComment = commentService.addComment(taskId, userId, content, imageFile);
            return ResponseEntity.ok(savedComment);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    // Göreve ait tüm yorumları listeleme

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Comment>> getCommentsByTask(@PathVariable Long taskId) {
        List<Comment> comments = commentService.getCommentsByTask(taskId);
        return ResponseEntity.ok(comments);
    }


    //  Yorum silme

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }






    @PostMapping("/comments/{commentId}/image")
    public ResponseEntity<Comment> addCommentImage(
            @RequestParam Long commentId,
            @RequestParam MultipartFile imageFile
    ) {
        try {
            Comment updatedComment = commentService.addCommentImage(commentId, imageFile);
            return ResponseEntity.ok(updatedComment);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}



