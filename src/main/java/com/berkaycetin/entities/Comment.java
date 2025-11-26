package com.berkaycetin.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String content;
	
	private Date createdDate;
	
	
	@OneToOne
	private CommentImage commentImage;
	
	 @ManyToOne
	    private User user; 
	 
	 @ManyToOne
	    private Task task;
	 
	 
	    public User getUser() {
		return user;
	}


	 public void setUser(User user) {
		 this.user = user;
	 }


	 public Task getTask() {
		 return task;
	 }


	 public void setTask(Task task) {
		 this.task = task;
	 }


		
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public CommentImage getCommentImage() {
		return commentImage;
	}


	public void setCommentImage(CommentImage commentImage) {
		this.commentImage = commentImage;
	}


	
	

}
