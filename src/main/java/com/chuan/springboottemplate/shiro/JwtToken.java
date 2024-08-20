package com.chuan.springboottemplate.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: chuan-wxy
 * @Date: 2024/8/19 10:52
 * @Description:
 */
public class JwtToken implements AuthenticationToken{

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
