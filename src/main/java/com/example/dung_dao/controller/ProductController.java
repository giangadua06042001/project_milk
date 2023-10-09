package com.example.dung_dao.controller;

import com.example.dung_dao.model.Product;
import com.example.dung_dao.service.product.IProductService;
import com.example.dung_dao.test.sesion.TestJwt;
import jakarta.servlet.http.HttpServletRequest;
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
    @Autowired
    private TestJwt testJwt;
    @PostMapping("/test")
    private ResponseEntity<Product>createProduct(@RequestBody Product product, HttpServletRequest request){
        if(testJwt.authenticateRequest(request)){
            Date date=new Date();
            product.setDateCreated(date);
            return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


    }
}
