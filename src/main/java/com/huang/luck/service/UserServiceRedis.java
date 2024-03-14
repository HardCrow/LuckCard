package com.huang.luck.service;

public interface UserServiceRedis {
    void AdminRedisSet(String name,int price);
    String UserGetCard(String name,String userAccount,int price);
}
