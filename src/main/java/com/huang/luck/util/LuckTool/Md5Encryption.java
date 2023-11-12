package com.huang.luck.util.LuckTool;

import org.springframework.util.DigestUtils;

public  class Md5Encryption {
   public String getMD5Password(String password,String salt){
        //md5加密算法  用的是stream类型  记住转成大写
        //加密三次  增加加密系数
        for(int i=0;i<=3;i++){
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
