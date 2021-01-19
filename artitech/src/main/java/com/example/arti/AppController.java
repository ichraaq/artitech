package com.example.arti;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class AppController {

	@GetMapping("/")
	public String viewHomePage() {
		return "index";
		
	}
	
	
}
