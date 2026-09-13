package com.blog.app.SrpingBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.SrpingBoot.payloads.UserDto;
import com.blog.app.SrpingBoot.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserControllers {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto) {
		UserDto dto = this.userService.createUser(userdto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto dto, @PathVariable("userId") Integer userId) {
		UserDto updated = this.userService.updateUser(dto, userId);

		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{userId}")
	public void delete(@PathVariable Integer userId) {
		this.userService.deleteUser(userId);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getuserbyid(@PathVariable Integer userId) {
		UserDto user = this.userService.getUserbyId(userId);

		return new ResponseEntity<>(user, HttpStatus.FOUND);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAll() {
		List<UserDto> dtos = this.userService.getAlluser();

		return new ResponseEntity<>(dtos, HttpStatus.FOUND);
	}

}
