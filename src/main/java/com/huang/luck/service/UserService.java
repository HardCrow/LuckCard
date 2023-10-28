package com.huang.luck.service;

import com.huang.luck.entity.Admin;
import com.huang.luck.entity.Goods;
import com.huang.luck.entity.User;

import java.util.List;

public interface UserService {
    void insert(User user);
    User login(String account, String password);

    void AddGoods(Goods goods);
 //   List luck(int CardNum);
    List LUCK(User user,Goods goods,int CardNum);
    Integer LuckCard(int Price);     //随机生成中奖卡片
    String CheckPrizeCard(int PrizeCard);
    //以下全部是另一种方法
    void AddCard(Admin admin,int Price, Goods goods, String ListName);  //这里应该多创建一个adminService层去专门用于管理admin的操作
     void  GetCard(User user,Integer Money,Integer num,Goods goods,String ListName);
}
