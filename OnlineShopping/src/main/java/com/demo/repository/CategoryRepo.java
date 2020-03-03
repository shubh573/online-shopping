package com.demo.repository;

import java.util.List;

import com.demo.model.Category;

public interface CategoryRepo {

	List<Category> list();
	Category get(int id);
}