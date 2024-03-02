package com.huang.luck.service.UserServiceImpl;

import com.huang.luck.mapper.AdminMapper;
import com.huang.luck.service.UserServiceRedis;
import com.huang.luck.service.ex.CardNameException;
import com.huang.luck.service.ex.CardNumberIsNull;
import com.huang.luck.service.ex.GoodsListNULLException;
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

    public void UserGetCard(String name,String userAccount,int price){
        //利用传递过来的名字和价格去抢卡片数字
        //版本1.0  名字还没有用上
         // String Name=name;
          if (name.equals(null)) {
              throw new GoodsListNULLException("出错了");
    }
          else if (stringRedisTemplate.opsForSet().size("Name")==0) {
                if (name.equals(adminMapper.AdminGoodsNameCheck(name))) {
                Map<String, ArrayList<Object>> Map = new HashMap<String, ArrayList<Object>>();
                ArrayList<Object> objects = new ArrayList<Object>();
                objects.add(stringRedisTemplate.opsForSet().pop(name, price));
                Map.put(userAccount, objects);
               // return Map;
               System.out.println(Map);
            }
            //     throw  new
         throw new CardNumberIsNull("没有卡片可以抢了");
       }


             else{throw new  GoodsListNULLException("暂时还未收录这把武器");}

    }

}
