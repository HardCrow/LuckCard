package com.huang.luck.util;

import java.io.Serializable;

//Json格式的数据相应
public class JsonResult<E> implements Serializable {  //serializable就是把对象转成json字符串  这就是序列化
                                                   //反序列化就是将json字符串转成java对象
                                                   //一个类只有实现了 Serializable 接口，它的对象才是可序列化的。
                                                   // 因此如果要序列化某些类的对象，这些类就必须实现 Serializable 接口。
                                                   // 而实际上，Serializable 的源码是一个空接口，没有什么具体内容，
                                                   // 它的目的只是简单的标识一个类的对象可以被序列化
    private Integer state;
    private String message;
    private E data;
    /*T、E、K、V、？本质都是通配符。用于定义泛型类、泛型方法、泛型接口…换成其他字母也行，只是这几个字母是一种编码约定。
      T，即type，表示一个具体的Java类型
      E，即element，常用于集合，如List<E>、Set<E>
      K V 即key . value，常用于Map的键值对
      ? 表示不确定的Java类型（详细看后面*/

    //以下是构造函数 4种全部写了
    public JsonResult(){}
    public JsonResult(Throwable e){
        this.message=e.getMessage();
    }
    public JsonResult(Integer state){
        this.state=state;
    }
    public JsonResult(Integer state, E data){
        this.state=state;
        this.data=data;
    }

    //以下是封装
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
