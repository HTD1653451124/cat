package com.ccj.event.entity;

public class UserTable {
    private Integer userId;
    private String phoneNum;
    private String password;
    private String payPassword;
    private Integer money;
    private String virtualName;

    public String getVirtualName() {
        return virtualName;
    }

    public void setVirtualName(String virtualName) {
        this.virtualName = virtualName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }



    public UserTable() {
    }

    public UserTable(Integer userId, String phoneNum, String password, String payPassword, Integer money, String virtualName) {
        this.userId = userId;
        this.phoneNum = phoneNum;
        this.password = password;
        this.payPassword = payPassword;
        this.money = money;
        this.virtualName = virtualName;
    }

    @Override
    public String toString() {
        return "UserTable{" +
                "userId=" + userId +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", payPassword='" + payPassword + '\'' +
                ", money=" + money +
                ", virtualName='" + virtualName + '\'' +
                '}';
    }
}
