package com.blog.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.entities.User;
import com.blog.app.exceptions.NotFoundException;
import com.blog.app.payloads.UserDto;
import com.blog.app.repositories.UserRepo;
import com.blog.app.services.UserService;


@Service
public class UserImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userdto) {
		User user = this.dtoTouser(userdto);
		User save = this.userRepo.save(user);
		UserDto savedUser = this.usertodto(save);
		return savedUser;
	}

	@Override
	public UserDto getUserbyId(Integer id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new NotFoundException("User", "id", id));
		UserDto dto = this.usertodto(user);
		return dto;
	}

	@Override
	public UserDto updateUser(UserDto dto, Integer id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new NotFoundException("User", "id", id));

		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setAbout(dto.getAbout());
		
		this.userRepo.save(user);
		
		UserDto updateduser = this.usertodto(user);
        
		return updateduser;
	}

	@Override
	public List<UserDto> getAlluser() {

		List<User> getall = this.userRepo.findAll();
		List<UserDto> getDto = getall.stream().map((user) -> this.usertodto(user)).collect(Collectors.toList());
		return getDto;
	}

	@Override
	public void deleteUser(Integer Id) {
		this.userRepo.findById(Id).orElseThrow(() -> new NotFoundException("User", "id", Id));
		this.userRepo.deleteById(Id);

	}

	public User dtoTouser(UserDto dto) {
		
		User user = this.modelMapper.map(dto, User.class);;
		
//		user.setId(dto.getId());
//		user.setName(dto.getName());
//		user.setAbout(dto.getAbout());
//		user.setEmail(dto.getEmail());
//		user.setPassword(dto.getPassword());
		return user;
	}

	public UserDto usertodto(User user) {
		UserDto dto = this.modelMapper.map(user, UserDto.class);
//		dto.setAbout(user.getAbout());
//		dto.setId(user.getId());
//		dto.setEmail(user.getEmail());
//		dto.setName(user.getName());
//		dto.setPassword(user.getPassword());
		return dto;
	}
}
