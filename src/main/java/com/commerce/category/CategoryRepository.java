package com.commerce.category;

import model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {

    public Category findByCategoryId(final String categoryId);
}
