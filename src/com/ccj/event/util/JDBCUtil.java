package com.ccj.event.util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    static {


            url = "jdbc:mysql://localhost:3306/cat";
            user = "root";
            password = "wocabushiba22";


    }

    /*
    * 获取连接
    * @return 连接对象
    * */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    /*
    * 释放资源
    * @param rs
    * @param st
    * @param conn
    * */
    public static void close(ResultSet rs,PreparedStatement pstmt,Connection conn){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }



    public static void close(PreparedStatement pstmt,Connection conn){
        if (pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }




}
