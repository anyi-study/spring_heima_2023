package com.softmen;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class SpringProjectXm1ApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void jwt() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "laoou")//算法签名
                .setClaims(claims)//自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//毫秒值 *1000 =1小时
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void test1() {
        Claims claims = Jwts.parser()
                .setSigningKey("laoou")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY4OTA1NjQwNn0.ypTKyq3aOCIcTvlIymdr9qO4XxcgaFnfrQ-8HaeGwOU")
                .getBody();
        System.out.println(claims);
    }
}
