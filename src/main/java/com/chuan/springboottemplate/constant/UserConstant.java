package com.chuan.springboottemplate.constant;

/**
 * 用户常量
 *
 * @Author: chuan-wxy
 * @Date: 2024/8/20 12:10
 * @Description:
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "user_login";

    //  region 权限

    /**
     * 默认角色
     */
    String DEAFAULT_ROLE = "default_user";

    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "admin";

    /**
     * 被封号
     */
    String BAN_ROLE = "ban";

    // endregion
}
