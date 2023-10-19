package com.huang.luck.util.LuckTool;
//随机数的版本二
public class NumsRandom {
    public Integer NumsRandom(Integer price){
        int max = price;
        int min = 1;
        long randomNum = System.currentTimeMillis();
        int nums = (int) (randomNum % (max - min) + min);
        //循环同一时间会产生相同的数
        System.out.print(nums);
        return nums;
    }

}
