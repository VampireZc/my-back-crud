package com.zc.mybackcrud;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    // 生成jwt
    @Test
    public void generateToken(){
        Map<String, Object> mapInfo = new HashMap<>();
        mapInfo.put("name", "zhangcong");
        mapInfo.put("role", "admin");
        mapInfo.put("id", 123456);

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "zhangcong") // 设置密钥
                .addClaims(mapInfo) // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 36000 * 1000)) // 设置有效期一分钟
                .compact(); // 生成令牌

        System.out.println(jwt);
    }

    // 解析jwt
    @Test
    public void paserToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6bnVsbCwidXNlcm5hbWUiOiJ3dXlvbmciLCJleHAiOjE3OTM1OTc3Mzh9.ZDvX7_BQStsF8Lht3PHZWSe3QsZlzB-ppznslIG-usA";
        Claims body = Jwts.parser()
                .setSigningKey("emhhbmdjb25n") // 设置密钥
                .parseClaimsJws(token) // 解析令牌
                .getBody();

        System.out.println(body);
    }
}
