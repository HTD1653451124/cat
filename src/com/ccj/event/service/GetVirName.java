package com.ccj.event.service;

import com.ccj.event.dao.IGetVirName;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GetVirName implements IGetVirName<Map> {
    /*
     * 通过账号查找到昵称
     * */
    @Override
    public Map getVirName(String account){
        if ( account == null ){
            return null;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map<Integer,String> user = new HashMap();

        String virtual_name = null;
        Integer user_id = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from user where account = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,account);
            rs = pstmt.executeQuery();
            while (rs.next()){
                virtual_name = rs.getString("virtual_name");
                user_id = rs.getInt("user_id");
                user.put(user_id,virtual_name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }
        return user;
    }
}
