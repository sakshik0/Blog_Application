package com.blog.app.SrpingBoot.services;

import java.util.List;

import com.blog.app.SrpingBoot.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto dto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto dto,Integer postid);
	
	void deletePost(Integer postid);
	
	List<PostDto> getAllPost();
	
	PostDto getPostbyId(Integer Id);
	
	List<PostDto> getPostsByCategory(Integer CategoryId);
	
	List<PostDto> getPostsByUser(Integer UserId);
	
	List<PostDto> searchPost(String keyword);
	
}
