package com.chuan.springboottemplate.controller.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: chuan-wxy
 * @Date: 2024/8/18 9:02
 * @Description:
 */
@Slf4j
@RequestMapping("/image")
@Controller
public class ImageController {

    @PostMapping("/upload-avatar")
    public String uploadAvatar() {
        return null;
    }
}
