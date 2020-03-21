package com.demo.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.demo.model.Category;
import com.demo.repository.CategoryRepo;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static CategoryRepo categoryRepo;

	private Category category;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.demo");
		context.refresh();
		categoryRepo = (CategoryRepo)context.getBean("categoryRepo");
	}
	
//	@Test
//	public void testAddCategory() {
//		
//		category = new Category();
//		
//		category.setName("Mobile");
//		category.setDescription("This is some description for Mobile");
//		category.setImgURL("CAT_103.png");
//		
//		assertEquals("Successfully added a category inside the table!",true,categoryRepo.add(category));
//		
//	}
	
//	@Test
//	public void testGetCategory() {
//		
//		category = categoryRepo.get(10);
//		
//		assertEquals("Successfully fetched a single category from the table!","Television",category.getName());
//		
//	}
	
	@Test
	public void testUpdateCategory() {
		
		category = categoryRepo.get(17);
		
		category.setName("Television");
		category.setActive(true);
		assertEquals("Something went wrong while updating a single category in the table!",true,categoryRepo.update(category));
		
		
	}
	
//	@Test
//	public void testDeleteCategory() {
//		
//		category = categoryRepo.get(15);		
//		assertEquals("Something went wrong while deleting a single category in the table!",true,categoryRepo.delete(category));
//		
//		
//	}
	
//	@Test
//	public void testListCategory() {
//					
//		assertEquals("Something went wrong while fetching the list of categories from the table!",3,categoryRepo.list().size());
//		
//	}
	
	
//	@Test
//	public void testCRUDCategory() {
//		
//		// add operation
//		category = new Category();
//		
//		category.setName("Laptop");
//		category.setDescription("This is some description for laptop!");
//		category.setImgURL("CAT_1.png");
//		
//		assertEquals("Something went wrong while adding a category inside the table!",true,categoryRepo.add(category));
//		
//		
//		category = new Category();
//		
//		category.setName("Television");
//		category.setDescription("This is some description for television!");
//		category.setImgURL("CAT_2.png");
//		
//		assertEquals("Something went wrong while adding a category inside the table!",true,categoryRepo.add(category));
//
//		
//		// fetching and updating the category
//		category = categoryRepo.get(17);
//		
//		category.setName("TV");
//		
//		assertEquals("Something went wrong while updating a single category in the table!",true,categoryRepo.update(category));
//		
//		
//		// delete the category
//		assertEquals("Something went wrong while deleting a single category in the table!",true,categoryRepo.delete(category));
//		
//		
//		//fetching the list
//		assertEquals("Something went wrong while fetching the list of categories from the table!",1,categoryRepo.list().size());		
//				
//		
//	}
	
}
