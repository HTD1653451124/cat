package com.ccj.event.service;

import com.ccj.event.dao.IInsertLog;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertLog implements IInsertLog<Boolean> {
    /*
     * 购票时日志增加记录
     * */
    @Override
    public Boolean insertLog(int sid,String time){
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;

        try{
            conn = JDBCUtil.getConnection();

            //向log表中添加记录
            String sql1 = "insert into log (scenic_info_id,log_date) values (?,?)";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1,sid);
            pstmt1.setString(2,time);
            pstmt1.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            JDBCUtil.close(rs,pstmt1,conn);
        }
        return true;
    }
}
