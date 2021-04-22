package com.ccj.event.entity;

import java.util.Date;

public class LogTable {
    private Integer logId;
    private Integer scenicInfoId;
    private Date logDate;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getScenicInfoId() {
        return scenicInfoId;
    }

    public void setScenicInfoId(Integer scenicInfoId) {
        this.scenicInfoId = scenicInfoId;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public LogTable() {
    }

    public LogTable(Integer logId, Integer scenicInfoId, Date logDate) {
        this.logId = logId;
        this.scenicInfoId = scenicInfoId;
        this.logDate = logDate;
    }

    @Override
    public String toString() {
        return "LogTable{" +
                "logId=" + logId +
                ", scenicInfoId=" + scenicInfoId +
                ", logDate=" + logDate +
                '}';
    }
}
