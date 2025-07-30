package com.thesilentnights.maalinkserver.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Auth {
    private static final String SECRET_KEY = "xC2yV7dQ7dV7hX0gU2cO0iY0bO9yU9rO";

    public static String createToken(String id, long ttlMillis) {

        byte[] secretKeyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, Jwts.SIG.HS256.key().build().getAlgorithm());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + ttlMillis);

        return Jwts.builder().subject(id).issuedAt(now).expiration(expiration) // 设置过期时间
                .signWith(secretKeySpec) // 设置签名密钥和签名算法
                .compact(); // 生成JWT字符串
    }

    public static boolean checkToken(String token, String verifiedToken, String username) {
        // 解析token
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(token).getPayload();
        // 验证负载中的信息
        if (!verifiedToken.equals(token) && !username.equals(claims.getSubject())) {
            return false;
        }

        Date expiration = claims.getExpiration(); // 获取过期时间

        // 验证token是否过期
        if (expiration.before(new Date())) {
            return false;
        }

        //全都通过就ok
        return true;
    }
}
