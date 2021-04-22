package com.ccj.event.service;

import com.ccj.event.dao.IGetScenicInfo;
import com.ccj.event.entity.ScenicInfoTable;
import com.ccj.event.entity.TicketTable;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetScenicInfo implements IGetScenicInfo<Map<ScenicInfoTable, TicketTable>> {
    /*
     * 获取景点信息
     * */
    @Override
    public Map<ScenicInfoTable, TicketTable> getScenicInfo(){
        ScenicInfoTable sc ;
        TicketTable tt;

        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;

        String location = null;
        Integer last = 0;
        Integer comment_num = 0;
        Integer ticket_num = 0;
        Date date ;
        double price = 0;
        Map<ScenicInfoTable,TicketTable> result = new LinkedHashMap<>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select location,last,comment_num from scenic_info order by comment_num DESC";
            String sql1 = "select ticket_num,date,price from ticket,scenic_info where scenic_id = scenic_info_id";
            pstmt = conn.prepareStatement(sql);
            pstmt1 = conn.prepareStatement(sql1);
            rs = pstmt.executeQuery();
            rs1 = pstmt1.executeQuery();
            while (rs.next()){
                location = rs.getString("location");
                last = rs.getInt("last");
                comment_num = rs.getInt("comment_num");
                rs1.next();
                ticket_num = rs1.getInt("ticket_num");
                date = rs1.getDate("date");
                price = rs1.getDouble("price");
                sc =  new ScenicInfoTable();
                sc.setLocation(location);
                sc.setLast(last);
                sc.setComment_num(comment_num);
                tt = new TicketTable();
                tt.setDate(date);
                tt.setPrice(price);
                tt.setTicketNumber(ticket_num);
                result.put(sc,tt);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
            JDBCUtil.close(rs1,pstmt1,conn);
        }
        return result;
    }
}
