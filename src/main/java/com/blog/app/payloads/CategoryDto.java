package com.blog.app.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
public class CategoryDto {
		
	private Integer Id;
	
	@NotBlank
	@Size(min=4,message="Title should be of length more than equal to 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10,message="Description should be of length more than equal to 10")
	private String categoryDescription;
	
}
