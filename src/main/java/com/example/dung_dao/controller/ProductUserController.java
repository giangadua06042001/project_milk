package com.example.dung_dao.controller;

import com.example.dung_dao.model.ProductUser;
import com.example.dung_dao.service.product_user.IProductUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("product_user")
public class ProductUserController {
    @Autowired
    private IProductUserService productUserService;
    @PostMapping("/create")
    private ResponseEntity<ProductUser>productUserResponseEntity(@RequestBody ProductUser productUser){
        return new ResponseEntity<>(productUserService.save(productUser), HttpStatus.OK);
    }
}
