package com.demo.repository;

import java.util.List;

import com.demo.model.Category;

public interface CategoryRepo {
	
	
	Category get(int id);
	List<Category> list();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
	
}