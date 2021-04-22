package com.ccj.event.service;

import com.ccj.event.dao.IIsRepeat;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsRepeat implements IIsRepeat<Boolean> {

    /*
     * 注册时检测账号是否存在
     * */
    @Override
    public Boolean isRepeat(String account){
        if (account == null){
            return  false;
        }
        Connection conn = null;
        PreparedStatement pstmt= null;
        ResultSet rs = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "select * from user ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                if (account.equals(rs.getString("account"))){
                    return false;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }

        return true;

    }
}
