package com.sunbeam.dao;

import com.sunbeam.Entities.Product;

public interface ProductDao {
	
	String addProduct(Product product);
	
	Product getProductById(Long productId);
	

}
