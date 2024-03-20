package com.huang.luck.controller;



import com.huang.luck.entity.Goods;
import com.huang.luck.entity.User;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;
import com.huang.luck.service.UserServiceRedis;
import com.huang.luck.service.ex.GoodsListNULLException;
import com.huang.luck.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    @Autowired
    UserServiceRedis userServiceRedis;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

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
         // System.out.println("1111111111111111");
             return new JsonResult<User>(OK); //JsonResult<User> 判断是否为user对象  然后调用构造器
                                             // public JsonResult(Integer state)  下面也有解释

       }
      @RequestMapping("/login")
      public  JsonResult<User> UserLogin(String account,String password){
       //  try{ userService.login(user);}
          userService.login(account,password);
        // catch (Exception e){
         //    log.info("出现了未知的错误："+e);
       //      return new JsonResult<>(e);  //这里不可以删掉 ，删掉后会执行下面的return  前端就会成功登入
       //  }
          return  new JsonResult<User>(OK);   //前面和后面都有解释 这里只是调用了一个构造器
      }
     //用户不用自己输入想要拿卡的名字，点击图片后前端就直接拿到名字，等待用户输入想要拿到的卡片数，与该数字一起传入后端
    //接口也实现完成但是总感觉少了点东西
    //这里的接口可以实现中奖人和中奖奖品公示的方法
     @RequestMapping("/UserGetCard")
    public  JsonResult<String> RUserGetCard(String name,int price,String UserAccount){
         String result=userServiceRedis.UserGetCard(name,UserAccount,price);
      //   System.out.println(result);
        // System.out.println("Performing asynchronous task on thread: " + Thread.currentThread().getName());
         return new JsonResult<String>(OK,result);
     }
     @RequestMapping("/UserPersonCheck")
    public void UserPersonCheck(String userAccount){
         //登入后传入userAccount然后利用这个去redis里面查询
         //查询后前端可以直接显示了
         //要记得设置了过期时间，后续操作还没有做
         String s = stringRedisTemplate.opsForValue().get("user:" + userAccount);
         System.out.println(s);
     }
}
