package com.chuan.springboottemplate.service.email;

import com.chuan.springboottemplate.common.BaseResponse;
import com.chuan.springboottemplate.pojo.vo.CaptchaVO;

/**
 * 描述
 *
 * @Author chuan-wxy
 * @Create 2024/8/15 13:55
 */
public interface EmailService {
    BaseResponse<Void> getCaptchaCode(String email,String content);
}
