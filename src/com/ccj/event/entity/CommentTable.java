package com.ccj.event.entity;

public class CommentTable {
    private Integer commentId;
    private Integer scenicInfoId;
    private Integer userId;
    private String content;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getScenicInfoId() {
        return scenicInfoId;
    }

    public void setScenicInfoId(Integer scenicInfoId) {
        this.scenicInfoId = scenicInfoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentTable() {
    }

    public CommentTable(Integer commentId, Integer scenicInfoId, Integer userId, String content) {
        this.commentId = commentId;
        this.scenicInfoId = scenicInfoId;
        this.userId = userId;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentTable{" +
                "commentId=" + commentId +
                ", scenicInfoId=" + scenicInfoId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                '}';
    }
}
