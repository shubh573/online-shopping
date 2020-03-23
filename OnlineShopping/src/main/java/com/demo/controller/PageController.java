package com.demo.controller;

import org.slf4j.Logger;



import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.exception.ProductNotFoundException;
import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.repository.CategoryRepo;
import com.demo.repository.ProductRepo;

@RestController
public class PageController {
	
//	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@RequestMapping(value = {"/","/home","/index"})
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Home");
				
//		logger.info("Inside PageController index method - INFO");
//		logger.debug("Inside PageController index method - DEBUG");
	
	
		//Passing the list of categories
		mv.addObject("categories",categoryRepo.list()); 
		
		mv.addObject("userClickHome",true);
		return mv;
	}
	
	@RequestMapping("/about")
	public ModelAndView about() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About");
		mv.addObject("userClickAbout",true);
		return mv;
	}
	
	@RequestMapping("/contact")
	public ModelAndView contact() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Contact");
		mv.addObject("userClickContact",true);
		return mv;
	}
	
	
	/*
	 * Methods to load all the products based on category
	 */
	
	@RequestMapping(value = "show/all/products")
	public ModelAndView showAllProducts() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Products");
		
		//Passing the list of categories
		mv.addObject("categories",categoryRepo.list()); 
		
		mv.addObject("userClickAllProducts",true);
		return mv;
	}
	
	@RequestMapping(value = "show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		
		ModelAndView mv = new ModelAndView("page");
		
		// categoryRepo to fetch a single category
		Category category = categoryRepo.get(id);
		
		mv.addObject("title",category.getName());
		
		//Passing the list of categories
		mv.addObject("categories",categoryRepo.list());
		
		//Passing the single of category object
		mv.addObject("category",category);
		
		mv.addObject("userClickCategoryProducts",true);
		return mv;
	}
	
	/*
	 * Viewing a single product
	 */
	
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
	
	ModelAndView mv = new ModelAndView("page");
	Product product = productRepo.get(id);
	
	if(product == null) throw new ProductNotFoundException();
	
	// update the view count
	product.setViews(product.getViews() +1);
	productRepo.update(product);
	
	mv.addObject("title",product.getName());
	mv.addObject("product",product);
	
	mv.addObject("userClickShowProduct",true);
	
	return mv;
	}
	
}
