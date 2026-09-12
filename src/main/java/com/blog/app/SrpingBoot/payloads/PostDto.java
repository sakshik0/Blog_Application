package com.blog.app.SrpingBoot.payloads;

import java.util.Date;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
	private String title;
	private String content;
	private String imageName;
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryDto category;

	@ManyToOne
	private UserDto user;

}
