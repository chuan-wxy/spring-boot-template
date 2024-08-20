package com.chuan.springboottemplate.shiro;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.system.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.chuan.springboottemplate.pojo.dto.user.UserProfileDTO;
import com.chuan.springboottemplate.pojo.entity.User;
import com.chuan.springboottemplate.service.user.UserService;
import com.chuan.springboottemplate.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: chuan-wxy
 * @Date: 2024/8/19 10:47
 * @Description:
 */
@Component
@Slf4j
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String userId = jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", userId)
                .select("uuid", "userAccount", "userName", "realname", "avatar", "status");

        User user = userService.getOne(queryWrapper, false);
        if (user == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        if (user.getStatus() == 1) {
            throw new LockedAccountException("该账户已被封禁，请联系管理员进行处理！");
        }

        AccountProfile accountProfile = new AccountProfile();
        BeanUtil.copyProperties(user, accountProfile);
        accountProfile.setUid(user.getUuid());
        return new SimpleAuthenticationInfo(accountProfile, jwtToken.getCredentials(), getName());

    }
}
