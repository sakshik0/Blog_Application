package com.blog.app.SrpingBoot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.app.SrpingBoot.payloads.CategoryDto;

@Service
public interface CategoryService {
	
		CategoryDto createCategory(CategoryDto dto);
		CategoryDto UpdateCategory(CategoryDto dto,Integer Id);
		CategoryDto getCategory(Integer Id);
		List<CategoryDto> getall();
		void deleteCategory(Integer Id);
}
