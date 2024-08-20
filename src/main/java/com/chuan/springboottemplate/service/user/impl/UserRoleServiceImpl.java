package com.chuan.springboottemplate.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuan.springboottemplate.mapper.UserRoleMapper;
import com.chuan.springboottemplate.pojo.entity.UserRole;
import com.chuan.springboottemplate.service.user.UserRoleService;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【userrole】的数据库操作Service实现
* @createDate 2024-08-20 12:22:33
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {

}




