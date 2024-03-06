package com.huang.luck.controller;

import com.huang.luck.entity.Admin;
import com.huang.luck.entity.Goods;
import com.huang.luck.entity.User;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;


import com.huang.luck.service.UserServiceImpl.UserServiceImplRedis;
import com.huang.luck.service.UserServiceRedis;
import com.huang.luck.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/admin")
@Controller
@Slf4j
public class AdminController extends BaseController {  //这里继承例了baseController
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/AdminSetCard") //requestmapping默认为get或者post
    /*@RequestMapping(value = "xxx", method = RequestMehod.POST)等价@PostMapping("xxx")
      @RequestMapping(value = "xxx", method = RequestMehod.GET)等价@GetMapping("xxx")
      @GetMapping 是 @RequestMapping 的缩写，专门用于处理 HTTP GET 请求。不需要显式指定 method 属性，因为它默认为 GET。post也如此
      1.get请求一般是去取获取数据（其实也可以提交，但常见的是获取数据）；post请求一般是去提交数据。
       2.get因为参数会放在url中，所以隐私性，安全性较差，请求的数据长度是有限制的，
        不同的浏览器和服务器不同，一般限制在 2~8K 之间，更加常见的是 1k 以内；
         post请求是没有的长度限制，请求数据是放在body中；
       3.get请求刷新服务器或者回退没有影响，post请求回退时会重新提交数据请求。
       4.get请求可以被缓存，post请求不会被缓存。
       5.get请求会被保存在浏览器历史记录当中，post不会。get请求可以被收藏为书签，因为参数就是url中，但post不能。它的参数不在url中。
       6.get请求只能进行url编码（appliacation-x-www-form-urlencoded）,post请求支持多种（multipart/form-data等）。*/
    public void  SetCardNum(String CreateName,Integer Price,String GoodsName,String ListName){
        //前端传入价格和 名字
        //当然前端必然可以传入管理员信息过来
        //这里就是模拟前端传递管理员名字过来
        //这里就模拟前端传递货物的价格直接在方法里面使用   listName的话好像没用过  listname是同名商品间的唯一标识
        //前端可以传递形参：CreatNname，Price，GoodsName，Listname  其中CreateName是admin登入后然后网页传入，其他是直接填入表格后传递
        userService.AddCard(CreateName,Price,GoodsName,ListName);
    }



    //redis1.0版本 拿去卡片
    @Autowired
    UserServiceRedis userServiceRedis;
@RequestMapping("/AdminRedisSetCard")
    public void RedisSetCard(String name,int price){
      // if (name==)
        userServiceRedis.AdminRedisSet(name,price);

    }

}
