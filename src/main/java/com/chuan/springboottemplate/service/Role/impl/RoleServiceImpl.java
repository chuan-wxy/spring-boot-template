package com.chuan.springboottemplate.service.Role.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuan.springboottemplate.mapper.RoleMapper;
import com.chuan.springboottemplate.pojo.entity.Role;
import com.chuan.springboottemplate.service.Role.RoleService;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【role】的数据库操作Service实现
* @createDate 2024-08-20 12:26:42
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

}




