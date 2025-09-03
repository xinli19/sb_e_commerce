package com.ecommerce.project.services;

import com.ecommerce.project.models.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.exceptions.*;

import java.util.ArrayList;
import java.util.List;
//@Service for injection
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;
   // private List<Category> categories = new ArrayList<>();
    //private long id = 0;
    private Long nextId = 1L;


    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        //return categories;
        return categories;
    }

    @Override
    public void createCategory(Category category){
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category "+ savedCategory.getCategoryName() +" already exists");
        }
       // category.setCategoryId(nextId++);
        categoryRepository.save(category);
        //category.setCategoryId(nextId++);
       //boolean added = categories.add(category);
    }

    @Override
   /* public String deleteCategory(Long categoryId){
        boolean wasRemoved = categories.removeIf(c->c.getCategoryId().equals(categoryId));
        if (wasRemoved)
            return "Category with id "+ categoryId+" has been deleted";
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
    }*/
    public String deleteCategory(Long categoryId) {
        /* 使用流找到匹配的类别
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        // 从列表中移除找到的类别
        categories.remove(category);
        return "Category with categoryId: " + categoryId + " deleted successfully !!";*/

        categoryRepository.deleteById(categoryId);
        return "Category with categoryId: " + categoryId + " deleted successfully !!";
    }
    public Category updateCategory(Long categoryId,Category category){
        List<Category> categories = categoryRepository.findAll();
        Category categoryFound = categories.stream()
                .filter(c-> c.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
        categoryFound.setCategoryName(category.getCategoryName());
        return categoryRepository.save(categoryFound);


    }
}
