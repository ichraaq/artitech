package com.example.arti.util;
 
import java.nio.file.Path;
import java.nio.file.Paths;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
public class MvcConfig implements WebMvcConfigurer {
 
	
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("login");
		
	}
	
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    Path productUploadDir = Paths.get("/image");
    String productUploadPath = productUploadDir.toFile().getAbsolutePath();
    registry.addResourceHandler("/image/**").addResourceLocations("file:/"+ productUploadPath +"/");
    }
     

    
}
