package com.commerce.category;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.Category;

public interface CategoryRepository extends MongoRepository<Category, String>{

	public Category findByCategoryId(String categoryId);
}
