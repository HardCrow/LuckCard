package com.huang.luck.entity;

public class LuckRecode {
    private int cid;
    private int CardRecode;
    private String UserAccount;
    private String GoodsName;
    private String CreateName;
    private String ListName;

    public String getCreateName() {
        return CreateName;
    }

    public void setCreateName(String createName) {
        CreateName = createName;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public LuckRecode() {
    }

    public LuckRecode(int cid, int cardRecode, String userAccount, String goodsName) {
        this.cid = cid;
        CardRecode = cardRecode;
        UserAccount = userAccount;
        GoodsName = goodsName;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCardRecode() {
        return CardRecode;
    }

    public void setCardRecode(int cardRecode) {
        CardRecode = cardRecode;
    }

    public String getUserAccount() {
        return UserAccount;
    }

    public void setUserAccount(String userAccount) {
        UserAccount = userAccount;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    @Override
    public String toString() {
        return "LuckRecode{" +
                "cid=" + cid +
                ", CardRecode=" + CardRecode +
                ", UserAccount='" + UserAccount + '\'' +
                ", GoodsName='" + GoodsName + '\'' +
                '}';
    }

}
