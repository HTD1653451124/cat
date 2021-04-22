package com.ccj.event.service;

import com.ccj.event.dao.IGetlog;
import com.ccj.event.entity.LogTable;
import com.ccj.event.entity.ScenicInfoTable;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Getlog implements IGetlog<Map<LogTable, ScenicInfoTable>> {
    @Override
    public Map<LogTable, ScenicInfoTable> getlog(Integer uid, Integer sid){
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        LogTable logTable ;
        ScenicInfoTable scenicInfoTable ;
        Map<LogTable,ScenicInfoTable> result = new LinkedHashMap<>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from log where log_id = ?";
            String sql1 = "select location from scenic_info where scenic_info_id = ?";
            String sql2 = "select * from user_and_log where user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1,uid);
            rs2 = pstmt2.executeQuery();
            while (rs2.next()){
                logTable = new LogTable();
                scenicInfoTable = new ScenicInfoTable();
                //获取user_and_log中的log_id
                Integer log_id = rs2.getInt("log_id");

                //将log_id赋值到sql语句中，查询log表
                pstmt.setInt(1,log_id);
                rs = pstmt.executeQuery();
                rs.next();

                //获取log表中的日期时间
                java.sql.Date log_date = rs.getDate("log_date");
                logTable.setLogDate(log_date);

                //获取scenic_info_id，赋值到sql1语句中，执行查询
                int scenic_info_id = rs.getInt("scenic_info_id");
                pstmt1.setInt(1,scenic_info_id);
                rs1 = pstmt1.executeQuery();
                rs1.next();

                //得到location
                String location = rs1.getString("location");
                scenicInfoTable.setLocation(location);
                result.put(logTable,scenicInfoTable);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
            JDBCUtil.close(rs1,pstmt1,conn);
            JDBCUtil.close(rs2,pstmt2,conn);
        }
        return result;
    }
}
