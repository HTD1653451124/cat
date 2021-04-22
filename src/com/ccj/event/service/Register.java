package com.ccj.event.service;

import com.ccj.event.dao.IRegister;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Register implements IRegister<Boolean> {
    /*
     * 注册,向user表中添加数据
     * @param 昵称，用户名，密码
     * @return boolean
     * */
    @Override
    public Boolean register(String virtualName,String account,String password,String payPassword){
        if (virtualName==null||password==null||account==null||payPassword==null){
            return false;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql1 = "insert into user (user_id,virtual_name,account,password,pay_password,money) values (null,?,?,?,?,0)";
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1,virtualName);
            pstmt.setString(2,account);
            pstmt.setString(3,password);
            pstmt.setString(4,payPassword);
            pstmt.executeUpdate();//不用传sql语句！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！气死我了
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(pstmt,conn);
        }
        return true;
    }
}
