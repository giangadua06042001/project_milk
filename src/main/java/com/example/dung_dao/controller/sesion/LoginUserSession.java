package com.example.dung_dao.controller.sesion;

import com.example.dung_dao.model.User;
import com.example.dung_dao.model.dto.UserDTO;
import com.example.dung_dao.service.user.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//.,,
@SessionAttributes("user")
public class LoginUserSession {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    private ResponseEntity<UserDTO> sessLogin(@RequestBody User user, HttpServletRequest request) {
        Optional<User> finder = userService.findUserByUserName(user.getUserName());
        if (finder.isPresent()) {
            User userCheck = finder.get();
            if (userCheck.isBlock()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setMessage("Tài khoản của bạn đã bị khóa,Liên hệ với 19001898 để nhận được sự trợ giúp ");
                return new ResponseEntity<>(userDTO, HttpStatus.EXPECTATION_FAILED);
            } else if (userCheck.getPassword().equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", userCheck.getUserId());
                session.setAttribute("userName", userCheck.getUserName());
                session.setAttribute("fullName", userCheck.getFullName());
                session.setAttribute("isBlock", userCheck.isBlock());
                session.setAttribute("role", userCheck.getRole());
                session.setAttribute("number", userCheck.getNumber());
                session.setMaxInactiveInterval(43200);
                UserDTO userDTO = new UserDTO();
                userDTO.setMessage("Bạn đã đăng nhập thành công");
                userDTO.setUserId(user.getUserId());
                userDTO.setFullName(user.getFullName());
                userDTO.setUserName(userCheck.getUserName());
                userDTO.setBlock(userCheck.isBlock());
                userDTO.setRole(userCheck.getRole());
                return ResponseEntity.ok(userDTO);

            } else {
                UserDTO userDTO = new UserDTO();
                userDTO.setMessage("Đăng nhập đã thất bại ");
                return new ResponseEntity<>(userDTO, HttpStatus.UNAUTHORIZED);
            }
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setMessage("Không tìm thấy tên đăng nhập đó");
            return new ResponseEntity<>(userDTO,HttpStatus.NOT_FOUND);
        }

    }
}
