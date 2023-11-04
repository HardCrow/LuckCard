package com.huang.luck.controller;



import com.huang.luck.entity.Goods;
import com.huang.luck.entity.User;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;
import com.huang.luck.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public JsonResult<User> UserGetCard(User user, Integer Money, Integer num, Goods goods, String ListName){
        //这里应该return一个json字符串
        //user数据应该是前端可以传
        //Money也可以传
        //goods也可以传
        //num和listname在创建的时候看一下可不可以扔到一个地方进行数据共享
        //这里的话不太严谨  未完成
        userService.GetCard(user,Money,num,goods,ListName);
       return new JsonResult<User>(OK);
     }
}
