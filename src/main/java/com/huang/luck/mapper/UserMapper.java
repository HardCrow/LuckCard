package com.huang.luck.mapper;

import com.huang.luck.entity.Goods;
import com.huang.luck.entity.LuckRecode;
import com.huang.luck.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
//没有小鸟图标就去配置文件添加mybatis的路径
public interface UserMapper {
     Integer insert(User user);
     User findByAccount(String Account);
     void AddGoods(Goods goods);
     Goods findByGoodsName(String GoodsName);
     Integer AddCard(LuckRecode luckRecode);
     LuckRecode CheckLuckCode(int cardrecode);
     //以下是另外一种方式
     void AddCardGoods(LuckRecode luckRecode);   //Succeeded  添加
     //查询该货物创建了多少个卡片数
     //其实好像可以不能sql 直接在admin创建的时候去传入
     List<LuckRecode> CheckRecodeNums(@Param("goodsname") String GoodsName,@Param("listname")String ListName); //查询
    void UpdataGetAccount(@Param("cardrecode")int CardRecode,
                          @Param("useraccount") String UserAccount,
                          @Param("goodsname") String GoodsName,
                          @Param("listname")String ListName); //Succeeded 更新数据

}
