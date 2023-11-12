package com.huang.luck.controller;



import com.huang.luck.entity.Goods;
import com.huang.luck.entity.User;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;
import com.huang.luck.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
      @RequestMapping("/reg")
      public JsonResult<?> UserReg(User user){
         try{ userService.Reg(user);}
         catch (DataIntegrityViolationException e){
             log.info("出现了未知的错误："+e);
             //前端的话应该限制客户为空，不允许前端客户使用时表单的数据 对应mysql的数据不能为空值
             //前端也应该限制用户使用时候的长度等输入的任何问题
             //前端只需要传递正确的数据给后端即可   空输入不是后端的问题
             //后端也只要输出给前端正确的json字符数据即可
         }
          System.out.println("1111111111111111");
             return new JsonResult<User>(OK); //JsonResult<User> 判断是否为user对象  然后调用构造器
                                             // public JsonResult(Integer state)  下面也有解释

       }

      @RequestMapping("/login")
      public  JsonResult<User> UserLogin(String useraccount, String password){
          userService.login(useraccount, password);
          return  new JsonResult<User>(OK);   //前面和后面都有解释 这里只是调用了一个构造器


      }
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
       //这里如果报错的话不用我们自己去调用构造器
        //spring直接报错 直接调用 public JsonResult(Throwable e)这个构造器因此我们只需要
        //去调用public JsonResult(Integer state)
        //    public JsonResult(Integer state, E data)这两个构造器即可
       //这里的OK表示200  在jsonResult的类中调用的是public JsonResult(Integer state)这个构造器
        //BaseController中if里面判断类型没有instance 200的情况，所以在BaseController里面直接return 200； 前端200表示成功
     }
}
