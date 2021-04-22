package com.ccj.event.service;

import com.ccj.event.dao.ISetTicket;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SetTicket implements ISetTicket<Boolean> {
    /*
     * 更改ticket表中的数据，增加user_and_log表中的数据
     * */
    @Override
    public Boolean setTicket(int sid,Integer uid,int lid){
        Connection conn = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            //ticket表中票的数量-1
            String sql2 = "update ticket set ticket_num = ticket_num-1 where ticket_id = ?";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1,sid);
            pstmt2.executeUpdate();

            //向user_and_log中添加记录
            String sql3 = "insert into user_and_log (user_id,log_id) values (?,?)";
            pstmt3 = conn.prepareCall(sql3);
            pstmt3.setInt(1,uid);
            pstmt3.setInt(2,lid);
            pstmt3.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            JDBCUtil.close(rs,pstmt2,conn);
            JDBCUtil.close(rs,pstmt3,conn);
        }
        return true;
    }
}
