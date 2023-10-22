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
    @PostMapping("/test/{id}")
    private ResponseEntity<Product>createProduct(@RequestBody Product product, HttpServletRequest request,@PathVariable Long id){
        Optional<User>user=userService.findById(id);
        if(testJwt.authenticateRequest(request)){
            Date date=new Date();
            product.setDateCreated(date);
            Product productCreate=productService.save(product);

            ProductUser productUser=new ProductUser( productCreate.getProductId(),productCreate,user.get());
            productUserService.save(productUser);
            return new ResponseEntity<>(productCreate, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/creat/{id}")
    private ResponseEntity<Product>testCreateProduct(@RequestBody Product product,HttpServletRequest request,@PathVariable Long id){
        Optional<User>user=userService.findById(id);
        String email= testJwt.checkAuthenticateRequest(request);
        if (user.isPresent()) {
            if (email.equals(user.get().getEmail())) {
                Date date = new Date();
                product.setDateCreated(date);
                Product productTest = productService.save(product);
                ProductUser productUser = new ProductUser(productTest.getProductId(), productTest, user.get());
                productUserService.save(productUser);
                return new ResponseEntity<>(productTest, HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping()
    private ResponseEntity<Iterable<Product>>listProduct(){
      Iterable<Product>listProduct=productService.findAll();
      return new ResponseEntity<>(listProduct,HttpStatus.OK);
    }
}

