package com.example.dung_dao.test.sesion;

import com.example.dung_dao.model.User;
import com.example.dung_dao.service.user.IUserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Component
public class TestJwt {
    @Autowired
    private IUserService iUserService;
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_TIME_MS = 3600000; // Thời gian hết hạn của JWT (1 giờ)

    // Tạo JWT
    public void storeJwtInCookie(HttpServletResponse response, String jwt) {
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setPath("/"); // Đảm bảo cookie có thể sử dụng trên toàn bộ ứng dụng
        cookie.setMaxAge(3600); // Thời gian sống của cookie (giây), ở đây là 1 giờ
        cookie.setHttpOnly(true); // Chỉ cho phép truy cập cookie qua HTTP, không cho JavaScript
        response.addCookie(cookie);
    }

    public static String createJWT(User user) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + EXPIRATION_TIME_MS);
        return Jwts.builder()
                .setSubject(user.getPassword())
                .setIssuer(user.getEmail())
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .claim("email", user.getEmail())
                .claim("password", user.getPassword())
                .signWith(SECRET_KEY)
                .compact();
    }

    // Giải mã JWT
    public static Claims decryptionJwt(String jwt) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build().parseClaimsJws(jwt);
        Claims claimsJwt = claimsJws.getBody();
        Date expirationDate = claimsJwt.getExpiration();
        Date date = new Date();
        if (date.before(expirationDate)) {
            // JWT is still valid
        }
        return claimsJwt;
    }



    public Optional<User> checkAuthenticateRequest(HttpServletRequest request){
        String jwt=extractJWTFromRequest(request);
        if(jwt!=null){
            Claims claims=decryptionJwt(jwt);
            String email=claims.get("email",String.class);
            String password=claims.get("password",String.class);
            iUserService.checkIsValidUser(email,password);
            return iUserService.findUsersByEmail(email);

        }
        return Optional.empty();
    }

    // Phương thức trích JWT từ tiêu đề (Yêu cầu gửi về)
    public static String extractJWTFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7).trim(); // Loại bỏ "Bearer " để lấy JWT
        }
        return null;
    }
}