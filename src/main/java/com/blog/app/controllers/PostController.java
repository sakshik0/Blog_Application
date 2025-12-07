package com.blog.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.PostDto;
import com.blog.app.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto dto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto postdto = this.postService.createPost(dto, userId, categoryId);
		return new ResponseEntity<>(postdto, HttpStatus.CREATED);
	}

	@DeleteMapping("/post/{postd}")
	public ApiResponse delete(@PathVariable Integer postd) {
		this.postService.deletePost(postd);
		return new ApiResponse("Post Sucessfully Delete", true);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostforCategory(@PathVariable Integer categoryId) {
		List<PostDto> allPost = this.postService.getPostsByCategory(categoryId);

		return new ResponseEntity<>(allPost, HttpStatus.FOUND);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsforUser(@PathVariable Integer userId) {
		List<PostDto> dtos = this.postService.getPostsByUser(userId);

		return new ResponseEntity<>(dtos, HttpStatus.FOUND);
	}

	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getall() {
		List<PostDto> allPost = this.postService.getAllPost();

		return new ResponseEntity<>(allPost, HttpStatus.FOUND);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostswithId(@PathVariable Integer postId) {
		PostDto dtos = this.postService.getPostbyId(postId);

		return new ResponseEntity<>(dtos, HttpStatus.FOUND);
	}

	@GetMapping("/posts/{keyword}")
	public ResponseEntity<List<PostDto>> getPostswithId(@PathVariable String keyword) {
		List<PostDto> dtos = this.postService.searchPost(keyword);

		return new ResponseEntity<>(dtos, HttpStatus.FOUND);
	}

}
