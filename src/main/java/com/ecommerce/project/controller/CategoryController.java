package com.ecommerce.project.controller;
import com.ecommerce.project.models.Category;
import com.ecommerce.project.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api")
//No validation here!
public class CategoryController {
   // private List<Category> categories = new ArrayList<>();
    private CategoryService categoryService;

    //constructor injection:
    // since you have a single constructor that requires CategoryService as a parameter, Spring will handle the dependency injection without the need for @Autowired.
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category>list =  categoryService.getAllCategories();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added succesfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    //use ResponseEntity instead of using the string.
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
            String status = categoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(status);}
    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @PathVariable Long categoryId, @RequestBody Category category) {
            categoryService.updateCategory(categoryId, category);
            return new ResponseEntity<>("Category updated succesfully", HttpStatus.OK);
    }
}
