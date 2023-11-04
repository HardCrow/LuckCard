package com.huang.luck.service;

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
}