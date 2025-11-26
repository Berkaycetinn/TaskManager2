package com.berkaycetin.service;
import com.berkaycetin.entities.*;
import org.springframework.web.multipart.MultipartFile;

import com.berkaycetin.repository.CommentRepository;
import com.berkaycetin.repository.TaskRepository;
import com.berkaycetin.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          TaskRepository taskRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // Yorum ekleme
    public Comment addComment(Long taskId, Long userId, String content, MultipartFile imageFile) throws IOException {
        //  Görev kontrolü
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Görev bulunamadı"));

        //  Kullanıcı kontrolü
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        // Yeni yorum oluştur
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreatedDate(new Date());
        comment.setTask(task);
        comment.setUser(user);

        // Eğer görsel varsa ekle
        if (imageFile != null && !imageFile.isEmpty()) {
            CommentImage img = new CommentImage();
            img.setBase64Image(imageFile.getBytes()); // byte[] setleme
            img.setUploadDate(new Date());

            // çift yönlü ilişki
            img.setComment(comment);
            comment.setCommentImage(img);
        }

        //  Kaydet
        return commentRepository.save(comment);
    }

    //  Göreve ait yorumları getir
    public List<Comment> getCommentsByTask(Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }

    //  Yorum silme
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public void saveTaskImage(Long taskId, MultipartFile file) throws IOException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        TaskImage taskImage = new TaskImage();
        taskImage.setImage(file.getBytes());
        taskImage.setUploadDate(new java.util.Date());
        taskImage.setTask(task);

        task.getTaskImages().add(taskImage);

        taskRepository.save(task);
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

}
