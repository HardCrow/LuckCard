package com.huang.luck;

import com.huang.luck.controller.AdminController;
import com.huang.luck.controller.UserController;
import com.huang.luck.entity.Admin;
import com.huang.luck.entity.Goods;
import com.huang.luck.entity.LuckRecode;
import com.huang.luck.entity.User;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class LuckApplicationTests {
    @Autowired
    UserService userService;
    @Autowired       //自动注入如果要注入两个的话就要写两次@Autowired
    UserMapper userMapper;
    @Autowired
    UserController userController;
    @Autowired
    AdminController adminController;
     //另一种方式
    @Test
    void FunTest01(){
        //管理员是批量添加数据 这里实例是一个
        LuckRecode luckRecode = new LuckRecode();
        luckRecode.setListName("huang");
        luckRecode.setCreateName("admin");
        luckRecode.setGoodsName("test");
        luckRecode.setCardRecode(2);
        userMapper.AddCardGoods(luckRecode);
    }

    @Test
    void FunTest02(){
        //批量生成卡片数
        Admin admin = new Admin();
        admin.setAccount("huang");
        Goods goods = new Goods();
        goods.setGoodsName("test05");  //货物名字就是由管理设置  前端给两个框 一个是设置名字的框一个是价格（卡片数量）的框
        //现在testlist好像没什么用，因为我不用list去存储数据了
        userService.AddCard("aaa", 0,"sda","test05");
        //userService.AddCard(admin,4,goods,"testList");
    }
    @Test
    void  FunTestController02(){
       // adminController.SetCardNum();
    }
    @Test
    void FunTestMapperUser01(){
        //这个方法暂时还没有用过
        List<LuckRecode> a=userMapper.CheckRecodeNums("test04","test04");
        System.out.println(a);
    }
    @Test
    void FunTestMapperUser02() {
        userMapper.UpdataGetAccount(1,"huang01","test03","test03");
    }

    @Test
    void FunTestUserService01(){
        User user = new User();
        user.setUserAccount("huang05");
        Goods goods = new Goods();
        goods.setGoodsName("test05");
        userService.GetCard(user,3,3,goods,"test05");
        //System.out.println(userService.GetCard(user, 2));
    }
    @Test
    void FunTestUserAccountIsNULL(){
        List<LuckRecode> luckRecodes = userMapper.CheckUserAccountIsNULL("test04", "test04", 5);
        //luckRecodes里面只有1条数据
        System.out.println(luckRecodes.size());
        System.out.println(luckRecodes.get(0).getUserAccount());
        System.out.println(luckRecodes);
       // for (int i=1;i<=luckRecodes.size();i++){
       //     System.out.println(luckRecodes.get(i));
       // }
    }
    @Test

    public void  FunTestUserService02(){
        System.out.println(userMapper.CountUserAccountNum("huang05"));
        //  System.out.println(userMapper.CheckUserAccount(2));

    }


}
