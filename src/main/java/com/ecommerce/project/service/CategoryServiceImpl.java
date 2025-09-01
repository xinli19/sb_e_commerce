package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
//@Service for injection
@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categories = new ArrayList<>();
    //private long id = 0;
    private Long nextId = 1L;
    @Override
    public List<Category> getAllCategories(){
        return categories;
    };

    @Override
    public void createCategory(Category category){
        category.setCategoryId(nextId++);
       boolean added = categories.add(category);
    };

    @Override
   /* public String deleteCategory(Long categoryId){
        boolean wasRemoved = categories.removeIf(c->c.getCategoryId().equals(categoryId));
        if (wasRemoved)
            return "Category with id "+ categoryId+" has been deleted";
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
    }*/
    public String deleteCategory(Long categoryId) {
        // 使用流找到匹配的类别
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        // 从列表中移除找到的类别
        categories.remove(category);
        return "Category with categoryId: " + categoryId + " deleted successfully !!";
    }
    public String updateCategory(Long categoryId,Category category){
        Category categoryFound = categories.stream()
                .filter(c-> c.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
        categoryFound.setCategoryName(category.getCategoryName());
        return "Category with categoryId: " + categoryId + " updated successfully !!";

    }
}
