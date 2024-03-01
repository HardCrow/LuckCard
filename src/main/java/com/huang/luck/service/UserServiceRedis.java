package com.huang.luck.service;

public interface UserServiceRedis {
    void AdminRedisSet(String name,int price);
    void UserGetCard(String name,String userAccount,int price);
}
