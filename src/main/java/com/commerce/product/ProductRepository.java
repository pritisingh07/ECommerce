package com.commerce.product;

import model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {

    public Product findByProductId(final String productId);

    public List<Product> findByCategoryId(final String categoryId);

}
