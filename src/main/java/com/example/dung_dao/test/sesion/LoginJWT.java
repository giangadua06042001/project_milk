package com.example.dung_dao.test.sesion;

import com.example.dung_dao.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/jwt")
public class LoginJWT {
    @Autowired
    private IUserService userService;
    @PostMapping("/login")
    private ResponseEntity<?>loginJwt(@RequestParam("email")String email,
                                      @RequestParam("accountName")String accountName,
                                      @RequestParam("password")String password){

    }
}
