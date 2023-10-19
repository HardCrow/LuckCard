package com.huang.luck.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Goods {
    private Integer sid;
    private float price;
    private String goodspic;
    private String goodsName;


    public volatile static ArrayList arraylist=new ArrayList();   //设置属性共享的方法   多线程调用同一个list  一定要new


    public Goods() {

    }

    public Goods(Integer sid, float price, String goodspic, String goodsName) {
        this.sid = sid;
        this.price = price;
        this.goodspic = goodspic;
        this.goodsName = goodsName;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getGoodspic() {
        return goodspic;
    }

    public void setGoodspic(String goodspic) {
        this.goodspic = goodspic;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Float.compare(goods.price, price) == 0 &&
                Objects.equals(sid, goods.sid) &&
                Objects.equals(goodspic, goods.goodspic) &&
                Objects.equals(goodsName, goods.goodsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, price, goodspic, goodsName);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "sid=" + sid +
                ", price=" + price +
                ", goodspic='" + goodspic + '\'' +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }
}
