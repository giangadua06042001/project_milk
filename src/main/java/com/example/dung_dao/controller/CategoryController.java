package com.example.dung_dao.controller;

import com.example.dung_dao.model.Category;
import com.example.dung_dao.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories/")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("")
    private ResponseEntity<Iterable<Category>>findAllCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @PostMapping("create")
    private ResponseEntity<Category>createCategory(@RequestBody Category category){
        try{
            return new ResponseEntity<>(categoryService.save(category),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
