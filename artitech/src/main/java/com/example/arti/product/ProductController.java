package com.example.arti.product;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.arti.category.Category;
import com.example.arti.category.CategoryRepository;



@Controller
public class ProductController {
  
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	
	//@GetMapping("/login")
	//public String login()
	//{return "login";
	
	//}
	
	
	//@GetMapping("/logout")
	//public String logout()
	//{return "login";
	
	//}
	
		
	
	//affiche la liste des produits
	@Secured(value= {"admin", "client"})
	@GetMapping("/products/new")
	public String showNewProductForm(Model model) {
		List<Category> listCategories =categoryRepo.findAll();
	     model.addAttribute("product", new Product());
	 	 model.addAttribute("listCategories", listCategories);
		return "product_form";
		}

	
	@Secured(value= {"admin", "client"})
	@PostMapping("/products/save")
	public String saveProduct( Product product, @RequestParam("fileImage") MultipartFile multipartFile) 
			throws IOException {
		
	    	 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		       product.setImage(fileName);
		       Product savedProduct = productRepo.save(product); 
		   	   
	   	        String uploadDir = "/image/" + savedProduct.getId();
	   		 
	   	        Path uploadPath= Paths.get(uploadDir);
	   	        
	   	        if(!Files.exists(uploadPath)) {
	   	        	Files.createDirectories(uploadPath);
	   	        }
		      
	   	        
	   	      try (InputStream inputStream =multipartFile.getInputStream()){
	   	        Path filePath = uploadPath.resolve(fileName);
	   	        Files.copy(inputStream,  filePath, StandardCopyOption.REPLACE_EXISTING);
	   	      } catch (IOException e) {
	   	    	  throw new IOException("could not save uploaded file"+ fileName);
	   	      }
		    	
	   	    	return "redirect:/products";
	}
	
	@Secured(value= {"admin", "client"})
	@GetMapping("/products")
	public String listProduct(Model model) {	
		
		List<Product> listProducts = productRepo.findAll();
		model.addAttribute("listProducts",listProducts);
		
		
		return "products";
	}
		
	
}


