package com.berkaycetin.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class CommentImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Lob
    private byte[] base64Image;
	
	private Date uploadDate;

    @OneToOne(mappedBy = "commentImage", cascade = CascadeType.ALL)
	private Comment comment;


    public byte[] getBase64ImageImage() {
        return base64Image;
    }


    public void setBase64Image(byte[] base64Image) {
        this.base64Image = base64Image;

    }
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}





	public Date getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	public Comment getComment() {
		return comment;
	}


	public void setComment(Comment comment) {
		this.comment = comment;
	}



	
	
}
