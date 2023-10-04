package com.example.dung_dao.controller;

import com.example.dung_dao.model.User;
import com.example.dung_dao.service.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    private ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            Date date = new Date();
            user.setDateCreated(date);
            return new ResponseEntity<>(userService.saveUser(user),HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/show")
    private ModelAndView showFormRegister(){
        ModelAndView modelAndView=new ModelAndView("Register");
        modelAndView.addObject("newUsers",new User());
        return modelAndView;
    }
}
