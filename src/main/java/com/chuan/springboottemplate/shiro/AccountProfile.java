package com.chuan.springboottemplate.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: chuan-wxy
 * @Date: 2024/8/19 11:00
 * @Description: 存在redis中的用户信息
 */
@Data
public class AccountProfile {


    private String uid;

    private String userAccountame;

    private String userName;

    private String avatar;

    private int status;

    public String getId() { //shiro登录用户实体默认主键获取方法要为getId
        return uid;
    }
}