package com.example.dung_dao.controller;

import com.example.dung_dao.model.Category;
import com.example.dung_dao.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @PostMapping()
    private ResponseEntity<Category>createCategory(@RequestBody Category category){
        Date date=new Date();
        category.setCreateDate(date);
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }
}
