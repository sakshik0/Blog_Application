package com.blog.app.SrpingBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.SrpingBoot.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
