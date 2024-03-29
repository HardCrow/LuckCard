package com.huang.luck.service.UserServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huang.luck.entity.PrizeRecode;
import com.huang.luck.mapper.AdminMapper;
import com.huang.luck.service.UserServiceRedis;
import com.huang.luck.service.ex.GoodsListNULLException;
import com.huang.luck.util.LuckTool.NumsRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@Component
public class UserServiceImplRedis implements UserServiceRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    NumsRandom numsRandom;

    private static final String REDIS_KEY_PREFIX = "user:";
    Duration expiration = Duration.ofDays(7);
    //此方法是管理员去调用生成一个set结构  生成的名字就利用mysql中武器名字
    public void AdminRedisSet(String name, int price) {
        for (int i = 0; i < price; i++) {
            //stringredistemplate的add方法只能添加string类型
            stringRedisTemplate.opsForSet().add(name, String.valueOf(i));
        }
    }
    public String UserGetCard(String name, String userAccount, int price) {
        if (name.equals(null)) {
            throw new GoodsListNULLException("出错了");
        }
        else if (stringRedisTemplate.opsForSet().size(name) != 0) {
        if (name.equals(adminMapper.AdminGoodsNameCheck(name))) {
            //这里是专门用来存储用户的拿取到的键的
                stringRedisTemplate.opsForSet().add(name+"1",userAccount);
               // System.out.println(stringRedisTemplate.opsForSet().members(name+userAccount));
                Collection<Object> values = stringRedisTemplate.opsForHash().entries(userAccount).values();  //拿到前面已经拿到的卡片
                //System.out.println(values);
                StringBuilder result = new StringBuilder();
                if (values.size() == 0) {
                } else {
                    result.append(values);
                }
                StringBuilder result2 = new StringBuilder();
                result2.append(stringRedisTemplate.opsForSet().pop(name, price));
               // System.out.println("这个是为了后面遍历加的map"+stringRedisTemplate.opsForHash().entries(name+"map"));
                result.append(result2);
                //  System.out.println("这里输出result"+result);
                String value = result.toString();
                stringRedisTemplate.opsForHash().put(userAccount, userAccount, value);
                 System.out.println(stringRedisTemplate.opsForHash().entries(userAccount));
                stringRedisTemplate.opsForHash().putAll(name + "map", stringRedisTemplate.opsForHash().entries(userAccount));
                System.out.println("这个是为了后面遍历加的map"+stringRedisTemplate.opsForHash().entries(name+"map"));
                //可以在每次拿取卡片后进行判断redis中的set大小是否为0，为0则生成随机数即可然后遍历出账户传回给网页端
                //现在该代码区每次都需要越界后才进行生成随机数并且回传给前端页面即可
            if (stringRedisTemplate.opsForSet().size(name) != 0) {
                //此处为空
            }
            else {
                String FinalNum =  NumsRandom.generateRandomNumber(price).toString();
                Map<Object, Object> originalMap = stringRedisTemplate.opsForHash().entries(name + "map");
                // 创建一个新的 Map<String, String>
                Map<String, String> stringMap = new HashMap<>();
                // 遍历原始 Map，将键和值转换为字符串，并放入新的 Map 中
                for (Map.Entry<Object, Object> entry : originalMap.entrySet()) {
                    String key = entry.getKey().toString();
                    String value1 = entry.getValue().toString();
                    stringMap.put(key, value1);
                }
                for (String member : stringRedisTemplate.opsForSet().members(name+"1")) {
                    stringRedisTemplate.opsForHash().delete(name + "map",member);
                    stringRedisTemplate.opsForHash().delete(member,member);
                    stringRedisTemplate.opsForSet().remove(name+member,member);
                }
                //下面是获取了java中的Map<String,String> strinmap的键
                for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                    if (entry.getValue().contains(FinalNum)) {
                        //如果包含finalnum，则回传给他的键
                        //这里把中奖的所有信息都存入到一个redis的map中。
                        //下面是准备把个人账户显示的操作
                        PrizeRecode myObject= new PrizeRecode(entry.getKey(), price,name,FinalNum);
                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            String key = REDIS_KEY_PREFIX + myObject.getPrizeName();
                            // 将 Java 对象序列化为 JSON 字符串
                            String jsonString = objectMapper.writeValueAsString(myObject);
                            stringRedisTemplate.opsForValue().set(key,jsonString,expiration);
                            //这里设置了3天的过期时间 后续换成钱的操作还没有写
                         //   System.out.println("Serialized JSON: " + jsonString);
                        } catch (JsonProcessingException e) {
                            // 处理序列化异常
                            e.printStackTrace();
                        }
                        return entry.getKey();//这里的entry.getKey()输出的就是中奖人的名字或者账户
                    }
                }
            }
            }
        else {throw new GoodsListNULLException("数据库中没有该信息"); }
        }
        else {
            String FinalNum =  NumsRandom.generateRandomNumber(price).toString();
            Map<Object, Object> originalMap = stringRedisTemplate.opsForHash().entries(name + "map");
            // 创建一个新的 Map<String, String>
            Map<String, String> stringMap = new HashMap<>();
            // 遍历原始 Map，将键和值转换为字符串，并放入新的 Map 中
            for (Map.Entry<Object, Object> entry : originalMap.entrySet()) {
                String key = entry.getKey().toString();
                String value = entry.getValue().toString();
                stringMap.put(key, value);
            }
            for (String member : stringRedisTemplate.opsForSet().members(name+"1")) {
                stringRedisTemplate.opsForHash().delete(name + "map",member);
                stringRedisTemplate.opsForHash().delete(member,member);
                stringRedisTemplate.opsForSet().remove(name+member,member);
            }
           // System.out.println("这里是121的"+stringRedisTemplate.opsForHash().entries("121"));
         //   System.out.println("这里是131的"+stringRedisTemplate.opsForHash().entries("131"));
           // System.out.println("这里是set 131的"+stringRedisTemplate.opsForSet().members("火蛇131"));
          //  System.out.println("这里是set 121的"+stringRedisTemplate.opsForSet().members("火蛇121"));
          //  System.out.println("这里是火蛇map"+stringRedisTemplate.opsForHash().entries("火蛇map"));
            for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                if (entry.getValue().contains(FinalNum)) {
                    return entry.getKey();
                }
            }
        }
        return "抢卡成功";
    }
    }

 /*  //这里是版本1.0，失败版本，太过于复杂，后续没有办法进行业务上的实现
  public void UserGetCard(String name,String userAccount,int price) {
        //利用传递过来的名字和价格去抢卡片数字
        //版本1.0  名字还没有用上
        Map<String, ArrayList<Object>> hashMap = new HashMap<String, ArrayList<Object>>();
        // String Name=name;
        if (name.equals(null)) {
            throw new GoodsListNULLException("出错了");
        } else if (stringRedisTemplate.opsForSet().size(name) != 0) {
            //假设这管理员设置了10个数字
            //接下来我就抢10个数字
            //但是没有判别条件
            if (name.equals(adminMapper.AdminGoodsNameCheck(name))) {

                Collection<Object> GetUserCard = stringRedisTemplate.opsForHash().entries(userAccount).values();

                List<String> list = new ArrayList<>((Collection<String>) (Collection<?>) GetUserCard);
                System.out.println("这里是没有拼接的时候的list"+list);
                List<String> pop = stringRedisTemplate.opsForSet().pop(name, price);
                System.out.println("这里是没有拼接的时候的pop"+pop);
                list.addAll(pop);//把原来的和现在的卡片数字拼接起来
                ArrayList<Object> objects = new ArrayList<Object>();
              //  objects.add(stringRedisTemplate.opsForSet().pop(name, price));
                 objects.add(list);
                System.out.println("这是拼接后的list"+list);
                hashMap.put(userAccount, objects);
                Map<String, String> stringMap = new HashMap<>();
                // 遍历传入的Map
                for (Map.Entry<String, ArrayList<Object>> entry : hashMap.entrySet()) {
                    // 将ArrayList<Object>转换为字符串，并放入新的Map中
                    String valueAsString = new ConvertArrayListToString().convertArrayListToString(entry.getValue());
                    stringMap.put(entry.getKey(), valueAsString);
                }
                //现在的问题是用户第二次重复抢一个项目的卡片时候，第一次抢的数量会被第二次的覆盖
                //这样的话只能把第一次的value值拼接到第二次的value里面去
                //这里的话要做一个监视器去监视用户抢一个项目的次数如果大于1的话就要拼接上去小于等于1的话不用理他
                stringRedisTemplate.opsForHash().putAll(userAccount,stringMap);  //我现在把hashmap扔进去redis中
                Map<Object, Object> map=(Map<Object, Object>) hashMap;
                System.out.println("这里是"+hashMap);  //这里只是显示一下并没有存储，每一次都是调用完后就被垃圾回收了
            } else {
                throw new GoodsListNULLException("暂时还未收录这把武器");
            }
            //     throw  new
        } else {

            //这里就是确定抢卡账户的代码，但是要把上面的账户及数字全部存入redis中不然hashmap为0不进行遍历
            int valueToFind = numsRandom.NumsRandom(price);
           Map<String, String> valueToKeyMap = new HashMap<>();

         //   for (Map.Entry<String, ArrayList<Object>> entry : hashMap.entrySet()) {
          //      String key = entry.getKey();
          //      ArrayList<Object> dataList = entry.getValue();
           //     if (dataList.contains(valueToFind)) {
            //        System.out.println("Value " + valueToFind + " found in account: " + key);
               // }
         //   }

            //    throw new CardNumberIsNull("没有卡片可以抢了");}

        }
  //  }

//}*/
