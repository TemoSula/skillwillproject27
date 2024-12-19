package com.example.skillwillproject27.Services;

import com.example.skillwillproject27.Models.UserModel;
import com.example.skillwillproject27.Repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {

    @Autowired
    UserRepository userRepository;

    private String secretKey = "ksjkdfjlksdjlfkjskldfjklsjdlkfjksjdf";

    public String generateToken(String username)
    {
        UserModel userModel = userRepository.findByUsername(username);

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) //1 kviriani
                .claim("username",username)
                .claim("role",userModel.getRole())
                .compact();
    }


    public Claims getAllClaims(String token)
    {
        Jws<Claims> allClaims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build().parseSignedClaims(token);
        Claims getpayload = allClaims.getPayload();
        return getpayload;
    }

    public boolean isExpiredToken(String token)
    {
        Claims clm = getAllClaims(token);
        Date expiredToken = clm.getExpiration();
        return expiredToken.before(new Date(System.currentTimeMillis()));
    }

}
