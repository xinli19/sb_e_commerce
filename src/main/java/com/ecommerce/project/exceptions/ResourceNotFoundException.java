package com.ecommerce.project.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    /*/ 自定义的字段，用于携带额外的错误信息
    关注点分离：Service 层的代码现在只需要关心业务逻辑，
    发现资源不存在时，只需throw new ResourceNotFoundException("Category", "id", categoryId);，无需关心 HTTP 响应的细节。
    String resourceName; // 资源名（如 "Category", "Product"）
    String field;        // 查找所用的字段名（如 "id", "name"）
    String fieldName;    // 字段的具体值（如 "Electronics"）
    Long fieldId;        // 字段的具体值（如 123L）*/
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String field, String fieldName, String resourceName) {
        super(String.format("Resource %s not found with id %s:%s", resourceName, fieldName,field));
        this.field = field;
        this.fieldName = fieldName;
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(String resourceName, String field, Long fieldId) {
       super(String.format("Resource %s not found with id %d:%s", resourceName, fieldId,field));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

