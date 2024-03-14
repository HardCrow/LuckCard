package com.huang.luck.util.LuckTool;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//随机数的版本二
@Component
public class NumsRandom {
    private static int maxNum;
    private static Random random;
    public static Integer generateRandomNumber(int num) {
        maxNum = num;
        random = new Random();
        // 创建定时任务，每秒生成一次随机数
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            int randomNumber = random.nextInt(maxNum + 1);
        }, 0, 1, TimeUnit.SECONDS);
        return random.nextInt(maxNum + 1);
    }

}
