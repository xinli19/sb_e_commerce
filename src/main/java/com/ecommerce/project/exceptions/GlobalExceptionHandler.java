package com.ecommerce.project.exceptions;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
//拦截整个应用程序中所有 @RestController（或 @Controller）抛出的异常。

public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //whenever method argument not valid exception happens, you need to excute the rest
    public Map<String,String>myMethodArgNotValidException(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        // - 创建一个HashMap来存储错误信息（字段名和错误消息） JSON 格式
        e.getBindingResult().getAllErrors().forEach((error)-> {
            String fieldName = ((FieldError) error).getField();
            //将通用的 ObjectError 转换为更具体的 FieldError
            //获取验证失败的字段名（如 "email"、"username"）
            String errorMessage = ((FieldError) error).getDefaultMessage();
            //获取该字段验证失败时的默认错误消息（如 "不能为空"）
            errors.put(fieldName, errorMessage);
        });
    return errors;
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String>myResourceNotFoundException(ResourceNotFoundException e){
        String errorMessage = e.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<String>myAPIException(APIException e){
        String errorMessage = e.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}

