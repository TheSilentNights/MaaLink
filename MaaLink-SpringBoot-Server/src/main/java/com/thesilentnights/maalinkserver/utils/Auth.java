package com.thesilentnights.maalinkserver.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Auth {
    private static final String SECRET_KEY = "maalinkDefination";

    public static String createToken(String id, long ttlMillis) {

        byte[] secretKeyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, Jwts.SIG.HS256.getId());


        Date now = new Date();
        Date expiration = new Date(now.getTime() + ttlMillis);

        return Jwts.builder().subject(id).issuedAt(now).expiration(expiration) // 设置过期时间
                .signWith(secretKeySpec, Jwts.SIG.HS256) // 设置签名密钥和签名算法
                .compact(); // 生成JWT字符串
    }

    public static boolean checkToken(String token) {
        // 解析token
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(token).getPayload();
        // 验证负载中的信息
        String subject = claims.getSubject(); // 获取用户ID或其他信息
        Date expiration = claims.getExpiration(); // 获取过期时间
        System.out.println(expiration.toString());

        // 验证token是否过期
        if (expiration.before(new Date())) {
            return false;
        }
        return true;
    }
}
