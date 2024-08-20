package com.chuan.springboottemplate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.chuan.springboottemplate.pojo.entity.User;

/**
 * 描述
 *
 * @Author chuan-wxy
 * @Create 2024/8/14 20:27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
