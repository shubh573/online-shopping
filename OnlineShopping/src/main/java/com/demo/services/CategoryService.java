package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.model.Category;
import com.demo.repository.CategoryRepo;

@Repository("categoryRepo")
public class CategoryService implements CategoryRepo {

	@Autowired
	private static List<Category> categories = new ArrayList<>();

	static {

		Category category = new Category();

		category.setId(1);
		category.setName("Television");
		category.setDescription("This is some description for TV");
		category.setImgURL("CAT_1.png");

		categories.add(category);

		//second category
		category = new Category();
 
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is some description for Mobile");
		category.setImgURL("CAT_2.png");

		categories.add(category);
		
		//third category
		category = new Category();
		 
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is some description for Laptop");
		category.setImgURL("CAT_3.png");

		categories.add(category);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		
		for(Category category : categories) {
			 
			if(category.getId() == id)
				return category;
		}
		return null;
	}
	

}
