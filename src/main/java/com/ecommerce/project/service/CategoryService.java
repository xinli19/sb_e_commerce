package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;

import java.util.List;
///with all the business logic here.
/// interface for loose coupling

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);
    String updateCategory(Long categoryId,Category category);
}
