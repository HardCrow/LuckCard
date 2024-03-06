package com.huang.luck.mapper;

import com.huang.luck.entity.Admin;
import com.huang.luck.entity.Goods;
import com.huang.luck.entity.LuckRecode;
import com.huang.luck.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;


@Mapper
//没有小鸟图标就去配置文件添加mybatis的路径
public interface UserMapper {
     Integer insert(User user);   //插入user  也就是用户注册的命令
     User findByAccount(String Account);   //登入要用
    String findSaltAccount(String Account);
     void AddGoods(Goods goods);   //添加货物
     Goods findByGoodsName(String GoodsName);  //登入查询货物是否存在
     //以下是另外一种方式
     void AddCardGoods(LuckRecode luckRecode);   //Succeeded  添加
     //查询该货物创建了多少个卡片数
     //其实好像可以不能sql 直接在admin创建的时候去传入
     List<LuckRecode> CheckRecodeNums(@Param("goodsname") String GoodsName,
                                      @Param("listname")String ListName); //查询
    void UpdataGetAccount(@Param("cardrecode")int CardRecode,
                          @Param("useraccount") String UserAccount,
                          @Param("goodsname") String GoodsName,
                          @Param("listname")String ListName); //Succeeded 更新数据
     List<LuckRecode> CheckUserAccountIsNULL(@Param("goodsname") String GoodsName,
                                      @Param("listname")String ListName,
                                     @Param("cardrecode")Integer CardRecode );  //查询数据中的useraccount是否为空
    Integer CountUserAccountNum(@Param("useraccount") String UserAccount);  //利用list的名字去查询有这个名字的数据条目数
    String CheckUserAccount(@Param("cardrecode")Integer CardRecode);   //利用数字去查询中奖人的名字


}
