package com.berkaycetin.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class TaskImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Lob
    private byte[] image;


    private Date uploadDate;
	
	@ManyToOne
	private Task task;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public byte[] getImage() {
        return image;
    }


    public void setImage(byte[] image) {
        this.image = image;

    }


	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}



}
