package com.example.dung_dao.test.sesion;

import com.example.dung_dao.model.User;
import com.example.dung_dao.model.dto.UserDTO;
import com.example.dung_dao.service.user.IUserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("session")
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
            return new ResponseEntity<>(userDTO, HttpStatus.NOT_FOUND);
        }

    }

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @RequestMapping("/login")
    public ModelAndView Index(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model) {
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        return new ModelAndView("Login");
    }

    @PostMapping("/doLogin")
    public ModelAndView doLoginSession(@ModelAttribute("user") User user, Model model,
                                       @CookieValue(value = "setUser", defaultValue = "") String setUser,
                                       HttpServletRequest request, HttpServletResponse response) {
        if (user.getEmail().equals("giangadua06042001@gmail.com") &&
                user.getPassword().equals("06042001")) {
            setUser = user.getEmail();
            Cookie cookie = new Cookie("user", setUser);
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            Cookie[]cookies=request.getCookies();
            for (Cookie cookieGetNow:cookies
                 ) {
                if(cookieGetNow.getName().equals("setUser")){
                    cookieGetNow.setValue("");
                }
                model.addAttribute("cookieValue",cookieGetNow);
            }
            model.addAttribute("cookie",cookies);
            return new ModelAndView("Index");
        }
        else {
            user.setEmail("");
            Cookie cookie = new Cookie("setUser", setUser);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("message", "Login failed. Try again.");
        }
        return new ModelAndView("Login");
     }


}
