package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.Category;
import com.demo.repository.CategoryRepo;

@Controller
public class PageController {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@RequestMapping(value = {"/","/home","/index"})
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Home");
		
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
	
	
}
