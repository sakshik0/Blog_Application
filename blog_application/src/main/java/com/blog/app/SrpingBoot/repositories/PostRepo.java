package com.blog.app.SrpingBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.SrpingBoot.entities.Category;
import com.blog.app.SrpingBoot.entities.Post;
import com.blog.app.SrpingBoot.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{
		
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
}
