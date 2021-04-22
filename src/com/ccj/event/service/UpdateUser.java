package com.ccj.event.service;

import com.ccj.event.dao.IUpdateUser;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateUser implements IUpdateUser<Integer> {
    /*
     *  修改数据（用户只能修改账号，密码，昵称）
     * */
    @Override
    public Integer updateUser(String account,String password,String virName ,int id){
        if (account == null || password==null || virName == null){
            return 0;
        }
        int num = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql= "update user set account = ?,password = ?,virtual_name = ? where user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,account);
            pstmt.setString(2,password);
            pstmt.setString(3,virName);
            pstmt.setInt(4,id);
            num = pstmt.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }
        return num;

    }
}
