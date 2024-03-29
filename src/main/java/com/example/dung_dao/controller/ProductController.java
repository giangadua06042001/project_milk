package com.example.dung_dao.controller;

import com.example.dung_dao.model.Product;
import com.example.dung_dao.model.ProductUser;
import com.example.dung_dao.model.User;
import com.example.dung_dao.service.product.IProductService;
import com.example.dung_dao.service.product_user.IProductUserService;
import com.example.dung_dao.service.user.IUserService;
import com.example.dung_dao.test.sesion.TestJwt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    IProductUserService productUserService;
    @Autowired
    private TestJwt testJwt;
    @PostMapping("/create")
    private ResponseEntity<Product> createProduct(@RequestBody Product product, HttpServletRequest request) {
        Optional<User> user = testJwt.checkAuthenticateRequest(request);
        if (user.isPresent()) {
            Date date = new Date();
            product.setDateCreated(date);
            Product productNew = productService.save(product);
            ProductUser productUser = new ProductUser(product, user.get());
            productUserService.save(productUser);
            return new ResponseEntity<>(productNew, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping()
    private ResponseEntity<Iterable<Product>> listProduct() {
        Iterable<Product> listProduct = productService.findAll();
        return new ResponseEntity<>(listProduct, HttpStatus.OK);
    }
}

