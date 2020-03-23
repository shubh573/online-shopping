package com.demo.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.repository.CategoryRepo;
import com.demo.repository.ProductRepo;
import com.demo.util.FileUploadUtility;
import com.demo.validator.ProductValidator;


@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	//private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProduct(@RequestParam(name="operation",required=false) String operation) {
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		Product nProduct = new Product();
		
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product",nProduct);
		
		if(operation!=null) {
			
			if(operation.equals("product")) {
				
				mv.addObject("message","Product Submitted successfully!");
			}
			else if(operation.equals("category")) {
				mv.addObject("message", "Category Submitted Successfully!");
			}
		}
		
		return mv;	
	}
	
	
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");	
		// fetch product from database
		Product nProduct = productRepo.get(id);
		// set the product fetch from database
		mv.addObject("product",nProduct);
		
		return mv;	
	}
	
	//handling product submission
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult result, 
			Model model, HttpServletRequest request	) {
		
		if(mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, result);
		}
		else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, result);
			}
		}
		
		// check if there are any errors
		if(result.hasErrors()) {
		 
			model.addAttribute("userClickManageProducts",true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message","Validation failed for Product Submission!");
			return "page";
		}
		
		//logger.info(mProduct.toString());
		
		if(mProduct.getId() == 0) {
			//create a new product record
			productRepo.add(mProduct);
		}
		else {
			//update a product record
			productRepo.update(mProduct);
		}
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			
			FileUploadUtility.uploadFile(request, mProduct.getFile(),mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	@RequestMapping(value = "/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		
		// fetch the product from database and update activate or deactivate based on the value of active field
		Product product = productRepo.get(id);
		boolean isActive = product.isActive();
		product.setActive(!product.isActive());
		productRepo.update(product);
		
		return (isActive)?"You have successfully deactivated the product with id" + product.getId() :
						"You have successfully deactivated the product with id" + product.getId();
	}
	
	
	// to handle category submission
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		 
		// add the new category
		categoryRepo.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	
	
	//returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		
		return categoryRepo.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();	
	}
 }
