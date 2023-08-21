package com.example.dung_dao.controller.loginController;

import com.example.dung_dao.model.dto.CustomerDTO;
import com.example.dung_dao.service.customers.ICustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@CrossOrigin("*")
//public class LoginCustomer {
//    private static final String CLIENT_ID = "537600243548-sn10qab7em2fqo0ohhn02846g07tvf6v.apps.googleusercontent.com";
//    private ICustomerService customerService;
//    @PostMapping("login-oauth2")
//    private ResponseEntity<CustomerDTO>loginCustomer(@RequestBody String token , HttpServletRequest request){
//       try {
//
//       }catch (Exception e){
//          CustomerDTO loginResponse=new CustomerDTO();
//            loginResponse.setMessage("Đăng nhập thất bại");
//            return new ResponseEntity<>(loginResponse, HttpStatus.NOT_FOUND);
//       }
//    }
//}
