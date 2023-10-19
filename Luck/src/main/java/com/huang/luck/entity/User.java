package com.huang.luck.entity;

import java.util.Objects;

public class User {
 private Integer id;
 private String userName;
 private String userpsd;
 private String salt;
 private String userAccount;
 private Integer state;
 private float balance;
 private String avatar;

    public User() {
    }

    public User(Integer id, String userName, String userpsd, String salt, String userAccount, Integer state, float balance, String avatar) {
        this.id = id;
        this.userName = userName;
        this.userpsd = userpsd;
        this.salt = salt;
        this.userAccount = userAccount;
        this.state = state;
        this.balance = balance;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserpsd() {
        return userpsd;
    }

    public void setUserpsd(String userpsd) {
        this.userpsd = userpsd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userpsd='" + userpsd + '\'' +
                ", salt='" + salt + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", state=" + state +
                ", balance=" + balance +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Float.compare(user.balance, balance) == 0 &&
                Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userpsd, user.userpsd) &&
                Objects.equals(salt, user.salt) &&
                Objects.equals(userAccount, user.userAccount) &&
                Objects.equals(state, user.state) &&
                Objects.equals(avatar, user.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userpsd, salt, userAccount, state, balance, avatar);
    }
}
