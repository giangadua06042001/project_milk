package com.example.dung_dao.test.sesion;

import com.example.dung_dao.model.User;
import com.example.dung_dao.service.user.IUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/jwt")
public class LoginJWT {
    @Autowired
    private IUserService userService;

    @PostMapping("login/{id}")
    private ResponseEntity<Claims> testId(@RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            if (user.get().getEmail().equals(email) && user.get().getPassword().equals(password)) {
                String jwt = TestJwt.createJWT(user.get());
                Claims jwtExample = TestJwt.decryptionJwt(jwt);
                user.get().setJWT(jwt);
                userService.save(user.get());
                return new ResponseEntity<>(jwtExample, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
