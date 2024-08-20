package com.chuan.springboottemplate.controller;

import com.chuan.springboottemplate.annotation.AuthCheck;
import com.chuan.springboottemplate.common.BaseResponse;
import com.chuan.springboottemplate.constant.UserConstant;
import com.chuan.springboottemplate.exception.StatusFailException;
import com.chuan.springboottemplate.pojo.dto.user.UserAddDTO;
import com.chuan.springboottemplate.pojo.dto.user.UserLoginDTO;
import com.chuan.springboottemplate.pojo.dto.user.UserLogoutDTO;
import com.chuan.springboottemplate.pojo.dto.user.UserProfileDTO;
import com.chuan.springboottemplate.pojo.vo.UserLoginVO;
import com.chuan.springboottemplate.service.email.EmailService;
import com.chuan.springboottemplate.service.user.UserService;
import com.chuan.springboottemplate.utils.RedisUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "UserControl")
public class UserControler {



    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 获取验证码
     *
     * @return
     */
    @GetMapping("/get-register-code")
    public BaseResponse<Void> getRegisterCode(@RequestParam(value = "email", required = true) String email) {
        return emailService.getCaptchaCode(email,null);
    }

    /**
     * 用户注册
     *
     * @param userAddDTO
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Void> register(@RequestBody(required = true) UserAddDTO userAddDTO) throws StatusFailException {
        return userService.register(userAddDTO);
    }

    /**
     * 登录
     *
     * @return BaseResponse<UserLoginVO>
     */
    @PostMapping("/login")
    public BaseResponse<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) {
        return userService.login(userLoginDTO, request, response);
    }

    /**
     * 修改用户信息
     */
    @PostMapping("/profile")
    @AuthCheck(mustRole = UserConstant.DEAFAULT_ROLE)
    public BaseResponse<Void> updateProfile(@RequestBody(required = true) UserProfileDTO userProfileDTO) {
        return userService.updateProfile(userProfileDTO);
    }

    /**
     * 注销获取验证码
     *
     * @return
     */
    @PostMapping("/get-logout-code")
    @AuthCheck(mustRole = UserConstant.DEAFAULT_ROLE)
    public BaseResponse<Void> getLogoutCode(@RequestBody UserLogoutDTO userLogoutDTO) {
        return userService.getLogoutCode(userLogoutDTO);
    }

    /**
     * 注销账号
     */
    @PostMapping("/logoutforever")
    @AuthCheck(mustRole = UserConstant.DEAFAULT_ROLE)
    public BaseResponse<Void> logoutForever(@RequestBody UserLogoutDTO userLogoutDTO) {
        return userService.logoutForever(userLogoutDTO);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    @AuthCheck(mustRole = UserConstant.DEAFAULT_ROLE)
    public BaseResponse<Void> logout(@RequestBody UserLogoutDTO userLogoutDTO) {
        return null;
    }

}
