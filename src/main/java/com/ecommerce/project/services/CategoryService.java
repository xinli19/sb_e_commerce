package com.ecommerce.project.services;

import com.ecommerce.project.models.Category;

import java.util.List;
///with all the business logic here.
/// interface for loose coupling

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);
    Category updateCategory(Long categoryId,Category category);
}
