package com.demo.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.demo.model.Product;
import com.demo.repository.ProductRepo;

public class ProductTestCase {

	
	private static AnnotationConfigApplicationContext context;

	private static ProductRepo productRepo;
	
	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.demo");
		context.refresh();
		productRepo = (ProductRepo)context.getBean("productRepo");
	}
	
//	@Test
//	public void testCRUDProduct() {
//		
//		// create operation
//		product = new Product();
//				
//		product.setName("Realme X50");
//		product.setBrand("Realme");
//		product.setDescription("This is some description for Realme mobile phones!");
//		product.setUnitPrice(38000);
//		product.setActive(true);
//		product.setCategoryId(18);
//		product.setSupplierId(3);
//		
//		assertEquals("Something went wrong while inserting a new product!",
//				true,productRepo.add(product));		
//		
//	
//		// reading and updating the category
//		product = productRepo.get(106);
//		product.setName("Samsung Galaxy S20");
//		assertEquals("Something went wrong while updating the existing record!",
//				true,productRepo.update(product));		
//				
//		assertEquals("Something went wrong while deleting the existing record!",
//				true,productRepo.delete(product));		
//		
//		// list
//		assertEquals("Something went wrong while fetching the list of products!",
//				7,productRepo.list().size());		
//				
//	}
	
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!",
				6,productRepo.listActiveProducts().size());				
	} 
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching the list of products!",
				4,productRepo.listActiveProductsByCategory(18).size());
		assertEquals("Something went wrong while fetching the list of products!",
				2,productRepo.listActiveProductsByCategory(16).size());
	} 
	
	@Test
	public void testGetLatestActiveProduct() {
		assertEquals("Something went wrong while fetching the list of products!",
				3,productRepo.getLatestActiveProducts(3).size());
		
	} 
}
