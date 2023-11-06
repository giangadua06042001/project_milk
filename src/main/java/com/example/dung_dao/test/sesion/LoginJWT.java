package com.example.dung_dao.test.sesion;

import com.example.dung_dao.model.User;
import com.example.dung_dao.service.user.IUserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

//    @PostMapping("login/{id}")
//    private ResponseEntity<Claims> testId(@RequestParam("email") String email,
//                                          @RequestParam("password") String password,
//                                          @PathVariable("id") Long id, HttpServletRequest request) {
//        Optional<User> user = userService.findById(id);
//        if (user.isPresent()) {
//            if (user.get().getEmail().equals(email) && user.get().getPassword().equals(password)) {
//                String jwt = TestJwt.createJWT(user.get());
//                Claims jwtExample = TestJwt.decryptionJwt(jwt);
//                HttpSession session = request.getSession();
//                session.setAttribute("jwt", jwt);
//                return new ResponseEntity<>(jwtExample, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
    @PostMapping("test/jwt")
    private ResponseEntity<?>creatJwt(@RequestBody User user,HttpServletRequest request){
        boolean jwt=userService.checkIsValidUser(user.getEmail(), user.getPassword());
        if(jwt){
            String jwtUser=TestJwt.createJWT(user);
            HttpSession session=request.getSession();
            session.setAttribute("jwt",jwtUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/get_jwt")
    private ResponseEntity<String> getJwt(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String jwt = (String) session.getAttribute("jwt");
        if (jwt != null) {
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("JWT is not present in session", HttpStatus.NOT_FOUND);
        }
    }


}
