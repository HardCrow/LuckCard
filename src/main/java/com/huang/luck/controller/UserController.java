package com.huang.luck.controller;



import com.huang.luck.entity.User;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/users")
@Controller
@Slf4j
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/getCard")
    public  void UserGetCard(){  //这里应该return一个json字符串

     }
}
