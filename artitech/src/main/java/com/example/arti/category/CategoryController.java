package com.example.arti.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository repo;
	
	@Secured(value= {"admin"})
	@GetMapping("/categories")
	public String listCategories(Model model) {	
		
		List<Category> listCategories = repo.findAll();
		model.addAttribute("listCategories",listCategories);
		
		
		return "categories";
	}
	
	@Secured(value= {"admin"}) 
	@GetMapping("/categories/new")
	public String showCategoryNewForm(Model model) {	
		model.addAttribute("category", new Category());
		return "category_form";
	}
	
	@Secured(value= {"admin"})
	@PostMapping("/categories/save")
	public String saveCategory(Category category) {
		repo.save(category);
		return "redirect:/categories";
	}

}
