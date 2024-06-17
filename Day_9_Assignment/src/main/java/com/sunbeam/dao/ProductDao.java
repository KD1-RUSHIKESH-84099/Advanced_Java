package com.sunbeam.dao;

import java.util.List;

import com.sunbeam.Entities.Category;
import com.sunbeam.Entities.Product;



public interface ProductDao {
	
	String addProduct(Product product);
	
	Product getProductById(Long productId);
	
	List<Product> getProductsByCategoryAndRange(Category category , double min , double max);
	
	

	String getDiscount(Category category, double discount);
	
	

	

	String productPurchased(Long productId, int quantity);
	
	String deleteProduct(String string);

	
	

}
