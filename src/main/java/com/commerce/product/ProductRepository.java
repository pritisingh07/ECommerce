package com.commerce.product;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.Product;


public interface ProductRepository extends MongoRepository<Product, String>{
	
	public Product findByProductId(String productId);
	
	public List<Product> findByCategoryId(String categoryId);

}
