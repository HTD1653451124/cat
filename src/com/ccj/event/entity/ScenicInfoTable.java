package com.ccj.event.entity;

public class ScenicInfoTable {
    private Integer scenicInfoId;
    private String location;
    private Integer last;
    private Integer comment_num;

    public ScenicInfoTable() {
    }

    public ScenicInfoTable(Integer scenicInfoId, String location, Integer last, Integer comment_num) {
        this.scenicInfoId = scenicInfoId;
        this.location = location;
        this.last = last;
        this.comment_num = comment_num;
    }

    public Integer getScenicInfoId() {
        return scenicInfoId;
    }

    public void setScenicInfoId(Integer scenicInfoId) {
        this.scenicInfoId = scenicInfoId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    public Integer getComment_num() {
        return comment_num;
    }

    public void setComment_num(Integer comment_num) {
        this.comment_num = comment_num;
    }

    @Override
    public String toString() {
        return "ScenicInfoTable{" +
                "scenicInfoId=" + scenicInfoId +
                ", location='" + location + '\'' +
                ", last=" + last +
                ", comment_num=" + comment_num +
                '}';
    }
}
