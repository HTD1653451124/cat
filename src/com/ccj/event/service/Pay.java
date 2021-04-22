package com.ccj.event.service;

import com.ccj.event.dao.IPay;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pay implements IPay<Boolean> {
    /*
     * 购票时日志增加记录
     * */
    @Override
    public Boolean pay( Integer uid,double price){
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        double money = 0;
        try {
            conn = JDBCUtil.getConnection();
            //获取用户的余额并判断
            String sql1 = "select money from user where user_id = ?";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1,uid);
            rs = pstmt1.executeQuery();
            while (rs.next()){
                money = rs.getInt("money");
            }
            if (money<price){
                return false;
            }

            //用户余额-票价
            String sql2 = "update user set money = ? where user_id = ? ";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setDouble(1,money-price);
            pstmt2.setInt(2,uid);
            pstmt2.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt1,conn);
            JDBCUtil.close(rs,pstmt2,conn);
        }
        return true;
    }
}
