package com.blog.app.SrpingBoot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.app.SrpingBoot.payloads.UserDto;


@Service
public interface UserService{
	
	UserDto createUser(UserDto user);
	UserDto getUserbyId(Integer id);
	UserDto updateUser(UserDto user,Integer Id);
	List<UserDto> getAlluser();
	void deleteUser(Integer Id);

}
