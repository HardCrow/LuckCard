package com.huang.luck.mapper;

import com.huang.luck.entity.Goods;
import com.huang.luck.entity.LuckRecode;
import com.huang.luck.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
//没有小鸟图标就去配置文件添加mybatis的路径
public interface UserMapper {
     Integer insert(User user);
     User findByAccount(String Account);
     void AddGoods(Goods goods);
     Goods findByGoodsName(String GoodsName);
     Integer AddCard(LuckRecode luckRecode);
     LuckRecode CheckLuckCode(int cardrecode);
}
