package com.chuan.springboottemplate.controller.admin;

import com.chuan.springboottemplate.annotation.AuthCheck;
import com.chuan.springboottemplate.common.BaseResponse;
import com.chuan.springboottemplate.constant.UserConstant;
import com.chuan.springboottemplate.pojo.vo.UserVO;
import com.chuan.springboottemplate.service.user.AdminService;
import com.chuan.springboottemplate.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员接口
 *
 * @Author: chuan-wxy
 * @Date: 2024/8/18 10:57
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/amdin")
@Tag(name = "AdminController")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;
    /**
     * 根据id查询用户
     * @param uuid
     * @return
     */
    @GetMapping("search-by-uuid")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<UserVO> searchUserById(String uuid) {
        return adminService.searchUserById(uuid);
    }

    /**
     * 根据性别查询用户
     * @param gender
     * @return
     */
    @GetMapping("/search-list-by-gender")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<UserVO>> searchUserByGender(String gender) {
        return adminService.searchUserByGneder(gender);
    }

    /**
     * 根据uuid删除用户
     * @param uuid
     * @return
     */
    @GetMapping("/delete-by-uuid")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Void> deleteUserByUuid(String uuid) {
        return adminService.deleteUserByUuid(uuid);
    }
}
