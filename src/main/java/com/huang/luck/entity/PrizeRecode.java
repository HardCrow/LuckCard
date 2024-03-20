package com.huang.luck.entity;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.stereotype.Component;

@Component
@JsonDeserialize
public class PrizeRecode {
    private String Prizer;
    private int Price;
    private String PrizeName;
    private String PrizeNum;

    public PrizeRecode() {
    }

    public PrizeRecode(String prizer, int price, String prizeName, String prizeNum) {
        PrizeName = prizeName;
        Price = price;
        Prizer = prizer;
        PrizeNum = prizeNum;
    }

    public String getPrizeName() {
        return PrizeName;
    }

    public void setPrizeName(String prizeName) {
        PrizeName = prizeName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getPrizer() {
        return Prizer;
    }

    public void setPrizer(String prizer) {
        Prizer = prizer;
    }

    public String getPrizeNum() {
        return PrizeNum;
    }

    public void setPrizeNum(String prizeNum) {
        PrizeNum = prizeNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrizeRecode that = (PrizeRecode) o;
        return Price == that.Price &&
                PrizeNum == that.PrizeNum &&
                Objects.equals(PrizeName, that.PrizeName) &&
                Objects.equals(Prizer, that.Prizer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PrizeName, Price, Prizer, PrizeNum);
    }

    public static PrizeRecode fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, PrizeRecode.class);
    }
}
