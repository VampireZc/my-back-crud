package com.zc.mybackcrud.untils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class TokenUtils {
    private final static String SECRET = "emhhbmdjb25n";
    private final static long EXPIRE = 60 * 1000; // 1 分钟
    // 生成token
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) // 过期时间
                .compact();
    }
    // 解析token
    public static Claims parseToken(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
