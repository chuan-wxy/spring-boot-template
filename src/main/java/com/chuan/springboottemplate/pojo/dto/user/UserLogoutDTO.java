package com.chuan.springboottemplate.pojo.dto.user;

import lombok.Data;

/**
 * @Author: chuan-wxy
 * @Date: 2024/8/17 19:03
 * @Description:
 */
@Data
public class UserLogoutDTO {

    private String userAccount;

    private String code;
}
