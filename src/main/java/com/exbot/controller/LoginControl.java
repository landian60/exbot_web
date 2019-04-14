package com.exbot.controller;

import com.exbot.model.User;
import com.exbot.service.UserService;
import com.exbot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: tan
 * @Date: 2019/1/8 9:24
 * Describe: 登录控制
 */
@Controller
public class LoginControl {

    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("phone") String phone,
                                 @RequestParam("authCode") String authCode,
                                 @RequestParam("newPassword") String newPassword,
                                 HttpServletRequest request){

        String trueMsgCode = (String) request.getSession().getAttribute("trueMsgCode");

        if(!authCode.equals(trueMsgCode)){
            return "0";
        }
        User user = userService.findUserByPhone(phone);
        if(user == null){
            return "2";
        }
        MD5Util md5Util = new MD5Util();
        String MD5Password = md5Util.encode(newPassword);
        userService.updatePasswordByPhone(phone, MD5Password);

        return "1";
    }

}
