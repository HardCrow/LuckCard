package com.huang.luck.util.LuckTool;

import org.springframework.stereotype.Component;

//随机数的版本二
@Component
public class NumsRandom {
    public  Integer NumsRandom(Integer num){
        int max = num+1;
        int min = 1;
        long randomNum = System.currentTimeMillis();
        int nums = (int) (randomNum % (max - min) + min);
        //循环同一时间会产生相同的数
        System.out.println("这是util里面的随机数"+nums);
        return nums;
    }

}
