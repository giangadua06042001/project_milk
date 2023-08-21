package com.example.dung_dao.controller;

import com.example.dung_dao.model.Customers;
import com.example.dung_dao.service.customers.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/customers/")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping()
    private ResponseEntity<Iterable<Customers>>findAllCustomer(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }
    @PostMapping("register")
    private ResponseEntity<?>saveCustomers(@Valid  @RequestBody Customers customers, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            Date date=Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            customers.setDateCreated(date);
            customerService.save(customers);
            return ResponseEntity.ok("Bạn đã đăng kí thành công");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
