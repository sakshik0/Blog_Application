package com.blog.app.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Post {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Integer PostId;
		
		@Column(name="Post_Title" , length=100,nullable=false)
		private String title;
		
		@Column(length=10000)
		private String Content;
		
		private String imageName;
		
		private Date createdDate;
		
		@ManyToOne
		@JoinColumn(name="category_id")
		private Category category;
		
		@ManyToOne
		private User user;
}
