package com.chuan.springboottemplate.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Manager;
import com.chuan.springboottemplate.common.enums.ShiroConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;

/**
 * @Author: chuan-wxy
 * @Date: 2024/8/19 9:31
 * @Description:
 */
@Data
@Component
@Slf4j
@ConfigurationProperties(prefix = "woj.jwt")
public class JwtUtil {
    private String secret = "23e2q1exd2q1e4dxq14edxq2145rq23r5fasdasdq2wed21qed2q1edq21";

    private long expire = 30 * 60;

    @Autowired
    private RedisUtil redisUtil;

    private long checkRefreshExpire;

    /**
     * 获取 JwtToken 并存入redis
     *
     * @param userAccount
     * @return
     */
    public String generateJwt(String userAccount) {
        Date nowDate = new Date();

        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        String token = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(userAccount)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)// 签名算法
                .compact();
        redisUtil.set(ShiroConstant.SHIRO_TOKEN_KEY + userAccount, token, expire);
        redisUtil.set(ShiroConstant.SHIRO_TOKEN_REFRESH + userAccount, "1", checkRefreshExpire);
        return token;
    }

    /**
     * 解析JwtToken
     *
     * @param token
     * @return
     */
    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("解析jwt失败");
            log.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * 从redis中清除JwtToken
     *
     * @param uid
     */
    public void cleanToken(String uid) {
        redisUtil.del(ShiroConstant.SHIRO_TOKEN_KEY + uid, ShiroConstant.SHIRO_TOKEN_REFRESH + uid);
    }

    /**
     * 检查token是否存在
     * @param uid
     * @return
     */
    public boolean hasToken(String uid) {
        return redisUtil.hasKey(ShiroConstant.SHIRO_TOKEN_KEY + uid);
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

}
