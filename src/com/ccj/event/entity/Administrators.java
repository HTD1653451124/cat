package com.ccj.event.entity;

public class Administrators {
    private Integer administratorsId;
    private String account;
    private String password;

    public Integer getAdministratorsId() {
        return administratorsId;
    }

    public void setAdministratorsId(Integer administratorsId) {
        this.administratorsId = administratorsId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Administrators(Integer administratorsId, String account, String password) {
        this.administratorsId = administratorsId;
        this.account = account;
        this.password = password;
    }

    public Administrators() {
    }

    @Override
    public String toString() {
        return "Administrators{" +
                "administratorsId=" + administratorsId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
