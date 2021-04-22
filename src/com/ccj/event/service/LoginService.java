package com.ccj.event.service;

import com.ccj.event.dao.ILoginService;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService implements ILoginService<Boolean> {
    /*
     * 将用户输入的账号和密码与数据库中的表进行对比，若相同则登录成功，反之登录失败
     * */
    @Override
    public Boolean login(String name ,String account,String password){
        if ( name == null || account == null || password == null){
            return false;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql1 = "select * from user where account = ? and password = ?";
            String sql2 = "select * from administrators where account = ? and password = ?";
            if (name.equals("user")){
                pstmt = conn.prepareStatement(sql1);
            }
            if (name.equals("administrators")){
                pstmt = conn.prepareStatement(sql2);
            }
            pstmt.setString(1,account);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }
        return false;
    }
}
