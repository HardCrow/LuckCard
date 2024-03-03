package com.huang.luck.service.UserServiceImpl;

import com.huang.luck.mapper.AdminMapper;
import com.huang.luck.service.UserServiceRedis;
import com.huang.luck.service.ex.CardNameException;
import com.huang.luck.service.ex.CardNumberIsNull;
import com.huang.luck.service.ex.GoodsListNULLException;
import com.huang.luck.util.LuckTool.ConvertArrayListToString;
import com.huang.luck.util.LuckTool.NumsRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserServiceImplRedis implements UserServiceRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    NumsRandom  numsRandom;

    //rediszset
    //管理员先利用价格创建一个zset
    //价格需要在数据库查询后调用过来

    //此方法是管理员去调用生成一个set结构  生成的名字就利用mysql中武器名字
    public void AdminRedisSet(String name,int price){
        for(int i=0;i<price;i++){

            //stringredistemplate的add方法只能添加string类型
            stringRedisTemplate.opsForSet().add(name,String.valueOf(i));
        }
    }

    public void UserGetCard(String name,String userAccount,int price) {
        //利用传递过来的名字和价格去抢卡片数字
        //版本1.0  名字还没有用上
        Map<String, ArrayList<Object>> hashMap = new HashMap<String, ArrayList<Object>>();
        // String Name=name;
        if (name.equals(null)) {
            throw new GoodsListNULLException("出错了");
        } else if (stringRedisTemplate.opsForSet().size(name) != 0) {
            if (name.equals(adminMapper.AdminGoodsNameCheck(name))) {
                ArrayList<Object> objects = new ArrayList<Object>();
                objects.add(stringRedisTemplate.opsForSet().pop(name, price));
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
                System.out.println(hashMap);  //这里只是显示一下并没有存储，每一次都是调用完后就被垃圾回收了
            } else {
                throw new GoodsListNULLException("暂时还未收录这把武器");
            }
            //     throw  new
        } else {
            int valueToFind = numsRandom.NumsRandom(price);
            Map<String, String> valueToKeyMap = new HashMap<>();
            for (Map.Entry<String, ArrayList<Object>> entry : hashMap.entrySet()) {
                String key = entry.getKey();
                ArrayList<Object> dataList = entry.getValue();
                if (dataList.contains(valueToFind)) {
                    System.out.println("Value " + valueToFind + " found in account: " + key);
                }
            }

            //    throw new CardNumberIsNull("没有卡片可以抢了");}

        }
    }

}
