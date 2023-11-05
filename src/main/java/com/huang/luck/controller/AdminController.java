package com.huang.luck.controller;

import com.huang.luck.entity.Admin;
import com.huang.luck.entity.Goods;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name="adminController层",description = "管理员添加货物的操作")
@RestController
@RequestMapping("/admin")
@Controller
@Slf4j
public class AdminController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/AdminSetCard")
    public void  SetCardNum(){
        //前端传入价格和 名字
        //当然前端必然可以传入管理员信息过来
        //这里就是模拟前端传递管理员名字过来
        Admin admin = new Admin();
        admin.setAccount("huang");
        //这里模拟前端传递货物名字
        Goods goods = new Goods();
        goods.setGoodsName("test03");
        //这里就模拟前端传递货物的价格直接在方法里面使用   listName的话好像没用过  listname是同名商品间的唯一标识
        userService.AddCard(admin,2,goods,"test03");
    }


}
