package com.example.dung_dao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("")
    public String showHomePage(){
        return "/Index";
    }
    @GetMapping("/login")
    public String showFormLogin(){
        return "/Login";
    }

    @GetMapping("/register")
    public String showFormRegister() {
        return "/Register";
    }

}
