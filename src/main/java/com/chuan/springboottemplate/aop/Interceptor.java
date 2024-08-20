package com.chuan.springboottemplate.aop;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import com.chuan.springboottemplate.annotation.AuthCheck;
import com.chuan.springboottemplate.common.ResultStatus;
import com.chuan.springboottemplate.common.enums.UserRoleEnum;
import com.chuan.springboottemplate.constant.UserConstant;
import com.chuan.springboottemplate.mapper.RoleMapper;
import com.chuan.springboottemplate.service.Role.RoleService;
import com.chuan.springboottemplate.service.user.UserRoleService;
import com.chuan.springboottemplate.service.user.UserService;
import com.chuan.springboottemplate.utils.JwtUtil;
import com.chuan.springboottemplate.utils.RedisUtil;
import com.chuan.springboottemplate.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * Aop 拦截器
 *
 * @Author: chuan-wxy
 * @Date: 2024/8/19 20:05
 * @Description: 用aop时实现的认证和授权
 */
@Slf4j
@Aspect
@Component
public class Interceptor {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleService roleService;

    @Around("@annotation(authCheck)")
    public Object   AuthenticationInterceptor(ProceedingJoinPoint point, AuthCheck authCheck) throws Throwable {


        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        String jwt = request.getHeader("Authorization");
        // 当前登录用户
        if (jwt == null) {
            log.info("jwt不存在,未登录");
            return ResultUtils.error("未登录");
        }

        boolean res = redisUtil.hasKey(jwt);
        if(!res) {
            log.info("登陆过期，请重新登录");
            return ResultUtils.error("登陆过期，请重新登录");
        }

        Claims claims = jwtUtil.getClaimByToken(jwt);
        if(claims == null) {
            return ResultUtils.error(ResultStatus.SYSTEM_ERROR);
        }

        String userAccount = claims.getSubject();
        System.out.println(userAccount);
        log.info(userAccount+"通过认证");

        List<String> roleList = roleMapper.SelectRoleByUserAccount(userAccount);

        String mustRole = authCheck.mustRole();

        // 不需要权限，放行
        if (mustRole == null || mustRole == UserConstant.DEAFAULT_ROLE) {
            return point.proceed();
        }

        if (mustRole.equals("admin") && (!roleList.contains(UserRoleEnum.AMDIN.getText()) && !roleList.contains(UserRoleEnum.ROOR.getText()))) {
            return ResultUtils.error(ResultStatus.ACCESS_DENIED);
        }

        if (mustRole.equals("root") && (!roleList.contains(UserRoleEnum.ROOR.getText()))) {
            return ResultUtils.error(ResultStatus.ACCESS_DENIED);
        }

        if(mustRole.equals("no_submit") && roleList.contains(UserRoleEnum.NO_SUBMIT_USER.getText())) {
            return ResultUtils.error("该账号禁止提交");
        }

        if(mustRole.equals("no_discuss_user") && roleList.contains(UserRoleEnum.NO_DISCUSS_USER.getText())) {
            return ResultUtils.error("该账号禁止发帖讨论");
        }

        if(mustRole.equals("mute_user") && roleList.contains(UserRoleEnum.MUTE_USER.getText())) {
            return ResultUtils.error("该账号禁止回复");
        }

        // 通过权限校验，放行
        return point.proceed();
    }
}
