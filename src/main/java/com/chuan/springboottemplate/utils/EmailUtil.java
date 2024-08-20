package com.chuan.springboottemplate.utils;

import jakarta.annotation.PostConstruct;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import com.chuan.springboottemplate.service.email.impl.EmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @Author chuan-wxy
 * @Create 2024/8/15 9:46
 */
@Component
public class EmailUtil {
    private static final Logger log = LoggerFactory.getLogger(EmailUtil.class);

    @Value("${emailUtil.hostName}")
    private String hostName;
    @Value("${emailUtil.charset}")
    private String charset;
    @Value("${emailUtil.account}")
    private String account;
    @Value("${emailUtil.name}")
    private String name;
    @Value("${emailUtil.password}")
    private String password;

    private static  String staticHostName;

    private static  String staticCharset;

    private static  String staticAccount;

    private static  String staticName;

    private static  String staticPassword;
    
    private EmailUtil(){}
    
    @PostConstruct
    public void init() {
        staticHostName = hostName;
        staticCharset = charset;
        staticAccount = account;
        staticName = name;
        staticPassword = password;
        System.out.println(staticCharset);
    }

    public static boolean send(String emailAddress, String message,String content) {
        HtmlEmail email = new HtmlEmail();
        try {
            //服务器地址
            email.setHostName(staticHostName);
            //字符集
            email.setCharset(staticCharset);
            //收件人邮箱
            email.addTo(emailAddress);
            //发件人
            email.setFrom(staticAccount, staticName);
            email.setAuthentication(staticAccount, staticPassword);
            //主题
            email.setSubject("AcKing验证码");
            //正文
            if(content != null){
                email.setMsg(content + message);
            }else {
                email.setMsg("您的验证码为：" + message);
            }
            log.info("EmailUtil 正在向{}发送内容为：{}的邮件",emailAddress,message);
            email.send();
            return true;
        } catch (EmailException e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }
}
