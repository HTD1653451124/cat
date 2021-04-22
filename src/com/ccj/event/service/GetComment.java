package com.ccj.event.service;

import com.ccj.event.dao.IGetComment;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GetComment implements IGetComment< Map<String,String>> {
    /*
     * 获取评论和昵称
     * */
    @Override
    public Map<String,String> getComment(Integer sid){
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        Map<String,String> comment = new HashMap<>();
        String content = null;
        Integer uid = 0;
        String vName = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql1 = "select * from comment where scenic_info_id = ?";
            String sql2 = "select * from user where user_id = ?";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            pstmt1.setInt(1,sid);
            rs = pstmt1.executeQuery();
            while (rs.next()){
                //获取comment表中的user_id，在user表中找到对应的昵称
                content = rs.getString("content");
                uid = rs.getInt("user_id");
                pstmt2.setInt(1,uid);

                rs2 = pstmt2.executeQuery();
                rs2.next();
                vName = rs2.getString("virtual_name");
                comment.put(vName,content);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt1,conn);
            JDBCUtil.close(rs2,pstmt2,conn);
        }
        return comment;
    }
}
