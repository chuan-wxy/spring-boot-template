package com.chuan.springboottemplate.service.user;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.chuan.springboottemplate.common.BaseResponse;
import com.chuan.springboottemplate.exception.StatusFailException;
import com.chuan.springboottemplate.pojo.dto.user.UserAddDTO;
import com.chuan.springboottemplate.pojo.dto.user.UserLoginDTO;
import com.chuan.springboottemplate.pojo.dto.user.UserLogoutDTO;
import com.chuan.springboottemplate.pojo.dto.user.UserProfileDTO;
import com.chuan.springboottemplate.pojo.entity.User;
import com.chuan.springboottemplate.pojo.vo.UserLoginVO;
import com.chuan.springboottemplate.pojo.vo.UserVO;

import java.util.List;

/**
 * 公共接口
 *
 * @Author chuan-wxy
 * @Create 2024/8/14 20:25
 */
public interface UserService extends IService<User> {

    BaseResponse<Void> getRegisterCode(String email,String content);

    BaseResponse<Void> register(UserAddDTO userAddDTO) throws StatusFailException;

    BaseResponse<UserLoginVO> login(UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response);

    BaseResponse<Void> updateProfile(UserProfileDTO userProfileDTO);

    BaseResponse<Void> logout(UserLogoutDTO userLogoutDTO);

    BaseResponse<Void> getLogoutCode(UserLogoutDTO userLogoutDTO);

    BaseResponse<Void> logoutForever(UserLogoutDTO userLogoutDTO);

}
