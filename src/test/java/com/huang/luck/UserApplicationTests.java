package com.huang.luck;

import com.huang.luck.controller.UserController;
import com.huang.luck.entity.User;
import com.huang.luck.mapper.AdminMapper;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;
import com.huang.luck.service.UserServiceImpl.UserServiceImplRedis;
import com.huang.luck.util.LuckTool.Md5Encryption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    UserController userController;
    @Autowired
    UserServiceImplRedis userServiceImplRedis;
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
    //redis测试

    @Test
    public  void TestPing() {
        Jedis jedis = new Jedis("192.254.70.10",6379);
        jedis.auth("123456");
        System.out.println(jedis.ping());
        Set<String> ks=jedis.keys("*");
        System.out.println(ks);
        jedis.set("k3","123");
        System.out.println(jedis.get("k3"));
    }

    @Autowired
    private RedisTemplate redisTemplate;
   @Test
    public void RedisZset(){
       redisTemplate.opsForSet().add("mySet",165);
       for(int i=0;i<10;i++){
           redisTemplate.opsForSet().add("mySet",i);
       }

       redisTemplate.opsForSet().add("mySet",16);
       System.out.println(redisTemplate.opsForSet().members("mySet"));
       System.out.println(redisTemplate.opsForSet().randomMembers("mySet",3));
       System.out.println(   redisTemplate.opsForSet().pop("mySet"));
       System.out.println(redisTemplate.opsForSet().members("mySet"));
       System.out.println(redisTemplate.opsForSet().members("mySet"));
       System.out.println(   redisTemplate.opsForSet().pop("mySet"));
       System.out.println(redisTemplate.opsForSet().members("mySet"));
       System.out.println(redisTemplate.opsForSet().members("mySet"));
       System.out.println(redisTemplate.opsForSet().pop("mySet", 2));
       System.out.println(redisTemplate.opsForSet().members("mySet"));


   }
    @Test
    public  void a(){
     /*   ArrayList<Object> objects = new ArrayList<Object>();
       objects.add(redisTemplate.opsForSet().members("mySet"));
        System.out.println(objects);*/
       userServiceImplRedis.AdminRedisSet("test",40);
       //这里是输出用户及拿到的卡片数字
        userServiceImplRedis.UserGetCard("test","13197916220",20);
        //这里是输出还剩余的卡片数字
        System.out.println(redisTemplate.opsForSet().members("test"));
    }



    //无法自动装配   ？？？？
    @Autowired
    AdminMapper adminMapper;
   @Test
    public  void AdminCheckGoodsName(){
       adminMapper.AdminGoodsNameCheck("迈阿密");
    }

}
