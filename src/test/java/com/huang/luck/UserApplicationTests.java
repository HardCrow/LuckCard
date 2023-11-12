package com.huang.luck;

import com.huang.luck.controller.UserController;
import com.huang.luck.entity.User;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;
import com.huang.luck.util.LuckTool.Md5Encryption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest
@RunWith(SpringRunner.class)
public class UserApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    UserController userController;
    @Test
   public void UserRegMapperInsert(){   //有用 11.11
        User user = new User();
        user.setUserAccount("11.11开始进行注册");
        user.setUserName("黄英杰");
        user.setUserpsd("123");
        userMapper.insert(user);
    }
    @Test
    public  void  UserRegServiceImp(){
        User user = new User();
        user.setUserAccount("11.11开始进行注册");
        user.setUserName("黄英杰第二次测试");
        user.setUserpsd("123456");
        userService.Reg(user);
    }
    @Test
    public void UserFindByUserAccountUserMapper(){   //测试利用account查询user记录
        System.out.println( userMapper.findByAccount("13197916220"));
    }
    @Test
    public void UserFindByUserAccountServiceImpl(){
      //  System.out.println(userService.login("13197916220","123456"));
       // System.out.println("----------------------------------------------------------");
      //  System.out.println(userService.login("黄英杰","13197916200"));
      //  System.out.println(userController.UserLogin("黄","123456"));
       System.out.println(userService.login("13197916220", "123456"));
       // System.out.println(userMapper.findSaltAccount("13197916220"));
        //Md5Encryption md5Encryption = new Md5Encryption();
       // System.out.println(md5Encryption.getMD5Password("123456","1D506216-92A4-4CF1-A549-127304F4015B"));
    }
}
