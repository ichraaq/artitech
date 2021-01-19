package com.example.arti.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.arti.category.Category;


@Entity
@Table(name="products")
public class Product {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;

	@Column(length = 45, nullable = false, unique= true)
	private String name;
	
	private float price;
	
	private String description;
	
	@Column(length =64, nullable = true)	
	private String image;
	

@ManyToOne
@JoinColumn(name = "category_id")
	private Category category;
	
@Transient
public String getImagePath() {
	if(image == null ) return null;
	return "/image/"+id+"/"+ image;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}


	public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public float getPrice() {
	return price;
}

public void setPrice(float price) {
	this.price = price;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;
}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
