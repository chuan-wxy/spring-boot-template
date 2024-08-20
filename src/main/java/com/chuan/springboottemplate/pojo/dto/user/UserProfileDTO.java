package com.chuan.springboottemplate.pojo.dto.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息DTO
 *
 * @Author: chuan-wxy
 * @Date: 2024/8/17 10:50
 * @Description: 用于用户修改个人信息
 */
@Data
public class UserProfileDTO implements Serializable {

    private String uuid;

    /**
     * 账号（邮箱）
     */
    private String userAccount;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * 学校
     */
    private String school;

    /**
     * 专业
     */
    private String course;

    /**
     * 学号
     */
    private String number;

    /**
     * 性别
     */
    private String gender;

    /**
     * github地址
     */
    private String github;

    /**
     * 博客地址
     */
    private String blog;

    /**
     * 个性签名
     */
    private String signature;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
