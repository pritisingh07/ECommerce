package com.commerce.category;

import config.MessageConstants;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericCategoryRepository {

    @Autowired
    CategoryRepository repository;

    public Category getCategoryById(final String categoryId) {
        return repository.findByCategoryId(categoryId);
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public String saveCategory(final Category category) {

        Category savedCategory = repository.save(category);
        return savedCategory.getCategoryId();

    }

    public String updateCategory(final String id, final Category category) {

        Category categoryInDb = repository.findByCategoryId(id);
        category.setId(categoryInDb.getId());

        return repository.save(category).getCategoryId();

    }

    public String deleteCategoryById(final String id) {

        Category category = repository.findByCategoryId(id);
        if (category != null) {
            repository.delete(category);
            return MessageConstants.CATEGORY_DELETED;
        } else {
            return MessageConstants.CATEGORY_NOT_PRESENT;
        }
    }

}
