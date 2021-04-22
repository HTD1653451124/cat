package com.ccj.event.service;

import com.ccj.event.dao.IGetLogId;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetLogId implements IGetLogId<Integer> {
    /*
     * 获取log表中的最后一行（执行完插入语句的）id
     * */
    @Override
    public Integer getLogId(){
        Connection conn = null;
        PreparedStatement pstmt4 = null;
        ResultSet rs = null;
        int lid = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql4 = "select * from log";
            pstmt4 = conn.prepareStatement(sql4);
            rs = pstmt4.executeQuery();
            while (rs.next()){
                lid = rs.getInt("log_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt4,conn);
        }

        return lid;
    }
}
