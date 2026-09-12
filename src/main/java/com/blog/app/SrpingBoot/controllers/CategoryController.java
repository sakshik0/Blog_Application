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

import com.blog.app.SrpingBoot.payloads.CategoryDto;
import com.blog.app.SrpingBoot.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto dto) {
		CategoryDto cdto = this.categoryService.createCategory(dto);
		return new ResponseEntity<>(cdto, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateUser(@Valid @RequestBody CategoryDto dto, @PathVariable("categoryId") Integer Id) {
		CategoryDto updated = this.categoryService.UpdateCategory(dto, Id);

		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{categoryId}")
	public void delete(@PathVariable Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getuserbyid(@PathVariable Integer categoryId) {
		CategoryDto category = this.categoryService.getCategory(categoryId);

		return new ResponseEntity<>(category, HttpStatus.FOUND);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAll() {
		List<CategoryDto> dtos = this.categoryService.getall();

		return new ResponseEntity<>(dtos, HttpStatus.FOUND);
	}

}
