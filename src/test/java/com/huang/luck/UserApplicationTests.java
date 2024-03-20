package com.huang.luck;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.huang.luck.controller.UserController;
import com.huang.luck.entity.PrizeRecode;
import com.huang.luck.entity.User;
import com.huang.luck.mapper.AdminMapper;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;
import com.huang.luck.service.UserServiceImpl.UserServiceImplRedis;
import com.huang.luck.service.UserServiceRedis;
import com.huang.luck.util.LuckTool.NumsRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;


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


@Autowired
    AdminMapper adminMapper;
   @Autowired
    UserServiceRedis userServiceRedis;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public  void AdminCheckGoodsName(){

        userServiceRedis.AdminRedisSet("火蛇",10);
        String a=  userServiceRedis.UserGetCard("火蛇","131",4);
        System.out.println(a);
        String b=  userServiceRedis.UserGetCard("火蛇","131",4);
        System.out.println(b);
       String c= userServiceRedis.UserGetCard("火蛇","121",2);
        System.out.println(c);
      //  String c=  userServiceRedis.UserGetCard("火蛇","121",1);
      //  System.out.println(c);
    }
@Test
public void ads(){
      //  stringRedisTemplate.opsForHash().put("aa","12","11");
     //   stringRedisTemplate.opsForHash().delete("aa","12");
    stringRedisTemplate.opsForSet().add("11", "s1", "s2", "s3");
    stringRedisTemplate.opsForSet().add("11","13");
    System.out.println(stringRedisTemplate.opsForSet().members("11"));
  //  stringRedisTemplate.opsForSet().pop("11",stringRedisTemplate.opsForSet().size("11"));
}
    @Test
    public void maps(){
        Map<Object, Object> objectObjectMap = new HashMap<Object, Object>();
        Map<Object, Object> objectObjectMap1 = new HashMap<Object, Object>();
        objectObjectMap.put("111","111");
        objectObjectMap1.put("222","222");
        Map<Object, Object> map=(Map<Object, Object>) objectObjectMap;
        Map<Object, Object> map1=(Map<Object, Object>) objectObjectMap1;
        stringRedisTemplate.opsForHash().putAll("test1",map);
        stringRedisTemplate.opsForHash().putAll("test1",map1);
        System.out.println("值"+stringRedisTemplate.opsForHash().entries("test1"));

    }
    @Test
    public void asdx(){

    }

    @Test
    public void aad(){

        stringRedisTemplate.opsForHash().put("aa","131","1,2,12,32");
        stringRedisTemplate.opsForHash().put("aa","131","1,2,12,3s2");
        stringRedisTemplate.opsForHash().put("ab","131","1,2,12,32");
        System.out.println(stringRedisTemplate.opsForHash().entries("aa"));//获取map中的值
        Collection<Object> ab = stringRedisTemplate.opsForHash().entries("ab").values();
        ArrayList<String> list = new ArrayList<>((Collection<String>) (Collection<?>) ab);
        list.add("12s");
        System.out.println(list);
        StringBuilder result = new StringBuilder();
        result.append(list);
        String str=result.toString();
        stringRedisTemplate.opsForHash().put("ab","131",str);
        System.out.println(stringRedisTemplate.opsForHash().entries("ab").values());
    }


@Test
    public void asd(){
    System.out.println(NumsRandom.generateRandomNumber(10));
    System.out.println(NumsRandom.generateRandomNumber(10));
    System.out.println(NumsRandom.generateRandomNumber(10));
    System.out.println(NumsRandom.generateRandomNumber(10));
    System.out.println(NumsRandom.generateRandomNumber(10));
    System.out.println(NumsRandom.generateRandomNumber(10));
    System.out.println(NumsRandom.generateRandomNumber(10));
    System.out.println(NumsRandom.generateRandomNumber(10));
    System.out.println(NumsRandom.generateRandomNumber(10));
    System.out.println(NumsRandom.generateRandomNumber(10));
    System.out.println(NumsRandom.generateRandomNumber(10));
    System.out.println(NumsRandom.generateRandomNumber(10));
      }


    private static final String REDIS_KEY_PREFIX = "user:";
    @Test
    public void tt(){
          //模拟对象序列化的过程

        PrizeRecode myObject = new PrizeRecode("John", 30,"AD","22");
        // 创建一个 ObjectMapper 实例
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String key = REDIS_KEY_PREFIX + myObject.getPrizeName();
            // 将 Java 对象序列化为 JSON 字符串
            String jsonString = objectMapper.writeValueAsString(myObject);
            stringRedisTemplate.opsForValue().set(key,jsonString);
            System.out.println("Serialized JSON: " + jsonString);
        } catch (JsonProcessingException e) {
            // 处理序列化异常
            e.printStackTrace();
        }
    }
    @Test
    public void SerTest(){
        //String json = "{\"prizer\":\"AD\",\"price\":30,\"prizeName\":\"John\",\"prizeNum\":\"22\"}";
        String s = stringRedisTemplate.opsForValue().get("user:John");
        try {
            PrizeRecode prizeRecord = PrizeRecode.fromJson(s);
            System.out.println(prizeRecord.getPrizer());
            System.out.println(prizeRecord.getPrice());
            System.out.println(prizeRecord.getPrizeName());
            System.out.println(prizeRecord.getPrizeNum());}
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
