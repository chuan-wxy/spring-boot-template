package com.chuan.springboottemplate.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chuan.springboottemplate.common.BaseResponse;
import com.chuan.springboottemplate.pojo.entity.User;
import com.chuan.springboottemplate.pojo.vo.UserVO;

import java.util.List;

/**
 * 特殊接口
 *
 * @Author chuan-wxy
 * @Create 2024/8/18 15:34
 */
public interface AdminService extends IService<User> {
    BaseResponse<UserVO> searchUserById(String uuid);

    BaseResponse<List<UserVO>> searchUserByGneder(String gender);

    BaseResponse<Void> deleteUserByUuid(String uuid);
}
