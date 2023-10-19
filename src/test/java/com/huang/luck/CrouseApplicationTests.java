package com.huang.luck;

import com.huang.luck.controller.UserController;
import com.huang.luck.entity.Goods;
import com.huang.luck.entity.LuckRecode;
import com.huang.luck.entity.User;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;
import com.huang.luck.service.ex.ServiceException;
import com.huang.luck.util.LuckTool.SetPrice;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Test
    void testGoods(){
        Goods goods = userMapper.findByGoodsName("霓虹");
        System.out.println(goods.toString());

    }
    @Test
    void testCkeckGoods(){
        Goods goods = userMapper.findByGoodsName("霓虹");
        System.out.println(goods.toString());
    }
    @Test
    void test() {
        User user = new User();
        user.setUserName("asd0");
        user.setUserpsd("123");
        user.setUserAccount("231516112");
      //  Integer insert = userMapper.insert(user);
    //    System.out.println(insert);
    }
    @Test
    void testServiceInsert(){
        try {
            User users = new User();
            users.setUserName("huang6");
            users.setUserpsd("123");
            users.setUserAccount("1213");
            userService.insert(users);
            //要测试这个地方 先把userserviceimpl的插入删掉 不然会重复，然后报错
            System.out.println("okkkkkkkkkkkkkkk");
        } catch (ServiceException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }

    }
    @Test
    void testLogin(){
     try {
         userService.login("123","huang1");
     }catch (ServiceException e){
         System.out.println(e.getClass());
         System.out.println(e.getMessage());
     }
    }
@Test
    void testServiceAddGoods(){
    Goods goods = new Goods();
    goods.setGoodsName("迈阿密");
    goods.setPrice(1600000);
    userService.AddGoods(goods);
}
@Test
    void testServiceLuck(){
   // userService.luck(6);
    System.out.println(Thread.currentThread().getName());
  //  System.out.println(userService.luck(10,3));

}

//抢卡测试 成功
    @Test
    void testServiceLUCK(){
       // Object o = new Object();
        //试一下多线程
      //  Thread userthread = new Thread();

        /* 测试的时候必须先new user 和goods
        * user设置useraccount的值
        * goods设置name值
        * 然后调用service的luck方法
        * */
        SetPrice setPrice = new SetPrice();
        setPrice.SetPrice1(6);  //设置价格 一般是管理员设置
      //  SetPrice setPrice1 = new SetPrice();
      //  setPrice1.SetPrice1(8);
        User user1 = new User();
        int i = hashCode();
        User user2 = new User();
        user2.setUserAccount("111");
       user1.setUserAccount("131");
        Goods goods = new Goods();
        goods.setGoodsName("测试");
     //   System.out.println("拿到的卡片:"+userService.LUCK(user1,goods,8));
     //   System.out.println("拿到的卡片:"+userService.LUCK(user1,goods,2));
        // user1.setId(1);  //id好像什么影响  用唯一标识即可
       // System.out.println( Goods.arraylist.get(6));
        System.out.println("拿到的卡片:"+userService.LUCK(user1,goods,3));
        System.out.println("拿到的卡片:"+userService.LUCK(user2,goods,3));
      //  System.out.println("拿到的卡片:"+userService.LUCK(user2,goods,3));
        System.out.println("剩余卡片:"+Goods.arraylist); //因为是静态的所以可以这里直接调用
       // System.out.println(userService.LUCK(user1.getId(),2));
       // Goods.arraylist.get(6);

        //这块地方还没有解决，一直为null
//  userService.LUCK(2);
       //  aaa=1111;
      //  System.out.println(aaa);
      //  if (Goods.arraylist==null){
       //     System.out.println(111111);
          //Goods.arraylist.add(123);
       // }

      //  System.out.println(Thread.currentThread().getName());
        //  System.out.println(userService.luck(10,3));
    }
//中奖查询的方法
   
    @Test
    void testLuckCard(){
       int pirzeCard=userService.LuckCard(6);
        System.out.println(pirzeCard);
        System.out.println(userService.CheckPrizeCard(pirzeCard)+"中奖了");
        System.out.println("恭喜你！中奖了");
     //   System.out.println(userMapper.CheckLuckCode(4));
    }
    @Test
    void aaa(){
        SetPrice setPrice = new SetPrice();
        setPrice.SetPrice1(100);
        User user1 = new User();
        user1.setId(22);
      //  System.out.println(userService.LUCK(20));  //取到后应该存储到数据库（redis）

    }


@Test
    void test333(){
        List<String> list = Arrays.asList("a","b","c","1221","sdad");
        int index = (int) (Math.random()* list.size());
        System.out.println(list.get(index));

     }
     @Test
    void testLuckRecode(){
         LuckRecode luckRecode = new LuckRecode();
         luckRecode.setCardRecode(5);
         luckRecode.setGoodsName("x线");
         luckRecode.setUserAccount("huangtest1");
         userMapper.AddCard(luckRecode);
     }
     @Test
    void testCheckLuckCode(){
        int a=5;
        System.out.println( userMapper.CheckLuckCode(a));
     }

}
