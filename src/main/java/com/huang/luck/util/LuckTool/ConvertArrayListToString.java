package com.huang.luck.util.LuckTool;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class ConvertArrayListToString {
    public String convertArrayListToString(ArrayList<Object> list) {
    StringBuilder sb = new StringBuilder(); // 创建一个StringBuilder对象，用于拼接字符串
    for (Object obj : list) { // 使用增强的for循环遍历ArrayList中的元素
        sb.append(obj.toString()).append(","); // 将每个元素转换为字符串，并添加到StringBuilder中，同时添加逗号分隔符
    }
    if (sb.length() > 0) { // 检查StringBuilder是否为空
        sb.deleteCharAt(sb.length() - 1); // 如果不为空，则删除最后一个逗号
    }
    return sb.toString(); // 返回拼接后的字符串
}
}
