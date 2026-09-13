package com.blog.app.SrpingBoot.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.SrpingBoot.entities.Category;
import com.blog.app.SrpingBoot.entities.Post;
import com.blog.app.SrpingBoot.entities.User;
import com.blog.app.SrpingBoot.exceptions.NotFoundException;
import com.blog.app.SrpingBoot.payloads.PostDto;
import com.blog.app.SrpingBoot.repositories.CategoryRepo;
import com.blog.app.SrpingBoot.repositories.PostRepo;
import com.blog.app.SrpingBoot.repositories.UserRepo;
import com.blog.app.SrpingBoot.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PostRepo postRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto dto, Integer userId, Integer CategoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User", "Id", userId));

		Category category = this.categoryRepo.findById(CategoryId)
				.orElseThrow(() -> new NotFoundException("category", "id", CategoryId));
		Post newPost = this.modelMapper.map(dto, Post.class);
		newPost.setImageName("default.png");
		newPost.setCreatedDate(new Date());
		newPost.setCategory(category);
		newPost.setUser(user);
		Post post = this.postRepo.save(newPost);
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto dto, Integer postid) {
		Post updatePost = this.postRepo.findById(postid).orElseThrow(() -> new NotFoundException("Post", "Id", postid));
		 
		updatePost.setTitle(dto.getTitle());
		updatePost.setContent(dto.getContent());
		
		Post uPost=this.postRepo.save(updatePost);
		PostDto newPost=this.modelMapper.map(uPost, PostDto.class);
		return newPost;
	}

	@Override
	public void deletePost(Integer postid) {
		Post post=this.postRepo.findById(postid).orElseThrow(() -> new NotFoundException("Post", "Id", postid));
		
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> allPost = this.postRepo.findAll();

		List<PostDto> allPostDto = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return allPostDto;
	}

	@Override
	public PostDto getPostbyId(Integer Id) {
		Post post = this.postRepo.findById(Id).orElseThrow(() -> new NotFoundException("Post", "Id", Id));
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer CategoryId) {
		Category category = this.categoryRepo.findById(CategoryId)
				.orElseThrow(() -> new NotFoundException("category", "Id", CategoryId));

		List<Post> allPost = this.postRepo.findByCategory(category);
		List<PostDto> allPostDto = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return allPostDto;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer UserId) {
		User user = this.userRepo.findById(UserId)
				.orElseThrow(() -> new NotFoundException("User", "Id", UserId));

		List<Post> allPost = this.postRepo.findByUser(user);
		List<PostDto> allPostDto = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return allPostDto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
        List<Post> allPost=this.postRepo.findAll();
        
        List<Post> allPostContain= allPost.stream().filter(post -> post.getContent().contains(keyword)).collect(Collectors.toList());
        
        List<PostDto> allPostDtos=allPostContain.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
        return allPostDtos;
	}

}
