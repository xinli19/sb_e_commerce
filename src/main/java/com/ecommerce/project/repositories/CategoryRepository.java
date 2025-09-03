package com.ecommerce.project.repositories; // 您的包名可能不同

// 1. 导入必要的接口
import com.ecommerce.project.models.Category; // 导入您的实体类
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

// 2. 继承 JpaRepository 并指定泛型参数
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(@NotBlank String categoryName);
    // Category 是要管理的obj，long是id的类
    // Spring Data JPA 会自动为我们实现所有基本CRUD方法。

}