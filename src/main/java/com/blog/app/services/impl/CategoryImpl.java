package com.blog.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Category;
import com.blog.app.exceptions.NotFoundException;
import com.blog.app.payloads.CategoryDto;
import com.blog.app.repositories.CategoryRepo;
import com.blog.app.services.CategoryService;

@Service
public class CategoryImpl implements CategoryService{
	
	@Autowired
	CategoryRepo catrepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto dto) {
		Category category=this.cdtotoc(dto);
		this.catrepo.save(category);
		CategoryDto savedCategory=this.ctocdto(category);
		return savedCategory;
	}

	@Override
	public CategoryDto UpdateCategory(CategoryDto dto, Integer Id) {
		
		Category category=this.catrepo.findById(Id).orElseThrow(() -> new NotFoundException("Category", "id", Id));
		
		this.catrepo.delete(category);
		
		category=this.cdtotoc(dto);
		
		this.catrepo.save(category);
		
		//CategoryDto savedDto=this.ctocdto(category);
		
		return dto;
	}

	@Override
	public CategoryDto getCategory(Integer Id) {
		
		Category category=this.catrepo.findById(Id).orElseThrow(() -> new NotFoundException("Category", "id", Id));
		
		CategoryDto found=this.ctocdto(category);
		
		return found;
	}

	@Override
	public List<CategoryDto> getall() {
		
		List<Category> getallCategory=this.catrepo.findAll();
		
		List<CategoryDto> allCategory=getallCategory.stream().map((category)->this.ctocdto(category)).collect(Collectors.toList());
		
		return allCategory;
	}

	@Override
	public void deleteCategory(Integer Id) {
		
		Category category=this.catrepo.findById(Id).orElseThrow(() -> new NotFoundException("Category", "id", Id));
		this.catrepo.delete(category);
	}
	
	CategoryDto ctocdto(Category category)
	{
		return this.modelMapper.map(category, CategoryDto.class);
	}
    
	Category cdtotoc(CategoryDto dto)
	{
		return this.modelMapper.map(dto, Category.class);
	}
}
