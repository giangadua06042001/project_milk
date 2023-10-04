package com.example.dung_dao.controller;

import com.example.dung_dao.model.Product;
import com.example.dung_dao.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @PostMapping()
    private ResponseEntity<Product>createProduct(@RequestBody Product product){
        Date date=new Date();
        product.setDateCreated(date);
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }
}
