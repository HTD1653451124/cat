package com.ccj.event.entity;

public class UserAndLogTable {
    private Integer userAndLogTable;
    private Integer userId;
    private Integer logId;

    public Integer getUserAndLogTable() {
        return userAndLogTable;
    }

    public void setUserAndLogTable(Integer userAndLogTable) {
        this.userAndLogTable = userAndLogTable;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public UserAndLogTable() {
    }

    public UserAndLogTable(Integer userAndLogTable, Integer userId, Integer logId) {
        this.userAndLogTable = userAndLogTable;
        this.userId = userId;
        this.logId = logId;
    }

    @Override
    public String toString() {
        return "UserAndLogTable{" +
                "userAndLogTable=" + userAndLogTable +
                ", userId=" + userId +
                ", logId=" + logId +
                '}';
    }
}
