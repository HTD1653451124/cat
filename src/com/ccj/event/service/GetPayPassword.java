package com.ccj.event.service;

import com.ccj.event.dao.IGetPayPassword;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetPayPassword implements IGetPayPassword<String> {
    /**
     * 获取支付密码
     * */
    @Override
    public String getPayPassword(String account){
        String payPassword = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from user where account = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,account);
            rs = pstmt.executeQuery();
            while(rs.next()){
                payPassword = rs.getString("pay_password");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }
        return payPassword;
    }

}
