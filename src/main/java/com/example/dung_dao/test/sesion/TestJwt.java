package com.example.dung_dao.test.sesion;

import com.example.dung_dao.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class TestJwt {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME_MS = 3600000; // Thời gian hết hạn của JWT (1 giờ)

    public static String createJWT(User user) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + EXPIRATION_TIME_MS);
        return Jwts.builder()
                .setSubject(user.getUserName())
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .claim("email", user.getEmail())
                .signWith(SECRET_KEY)
                .compact();

    }

    public static boolean checkAndExplainJWT(String jwt) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build().
                    parseClaimsJws(jwt);
            Claims jwtclaims = claimsJws.getBody();
            String userId = jwtclaims.getSubject();
            String email = (String) jwtclaims.get("email");
            System.out.println("userId" + ":" + userId);
            System.out.println("email" + ":" + email);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {


    }
}
