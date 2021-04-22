package com.ccj.event.service;

import com.ccj.event.dao.IRecharge;
import com.ccj.event.dao.IRegister;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Recharge implements IRecharge<Boolean> {
    /**
     * 充值
     * */
    @Override
    public Boolean recharge(String account,String money){
        if (account==null||money==null){
            return false;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "update user set money = money + ? where account = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,money);
            pstmt.setString(2,account);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }finally {
            JDBCUtil.close(pstmt,conn);
        }
        return true;
    }
}
