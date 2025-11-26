package com.berkaycetin.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	private String title;
	
	
	private String descrition;
	
	private String status;
	
	private Date createdDate;
	
	private Date dueDate;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskImage> taskImages = new ArrayList<>();
	

	@ManyToOne
	private User user;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescrition() {
		return descrition;
	}


	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}







    public List<TaskImage> getTaskImages() {
        return taskImages;
    }

    public void setTaskImages(List<TaskImage> taskImages) {
        this.taskImages = taskImages;
    }





}


