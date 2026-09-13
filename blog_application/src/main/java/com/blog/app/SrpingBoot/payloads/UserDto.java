package com.blog.app.SrpingBoot.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
 
	private int id;
	
	@NotBlank
	@Size(min=4, message="Name length should be greater than equal to 4")
	private String name;
	
	@NotBlank
	private String about;
	
	@NotBlank
	@Size(min=4,message="Password should be greater than 3")
	private String password;
	
	@Email(message="Email is not valid")
	private String email;
}
