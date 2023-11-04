package com.huang.luck.entity;

public class Admin {
    private Integer aid;
    private String account;
    private String psd;
    private  Integer power;

    public Admin() {
    }

    public Admin(Integer aid, String account, String psd, Integer power) {
        this.aid = aid;
        this.account = account;
        this.psd = psd;
        this.power = power;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "admin{" +
                "aid=" + aid +
                ", account='" + account + '\'' +
                ", psd='" + psd + '\'' +
                ", power=" + power +
                '}';
    }
}
