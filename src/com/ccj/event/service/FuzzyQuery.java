package com.ccj.event.service;

import com.ccj.event.dao.IFuzzyQuery;
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

public class FuzzyQuery implements IFuzzyQuery<Map<ScenicInfoTable,TicketTable>> {

    /*
     * 模糊查询
     * */
    @Override
    public Map<ScenicInfoTable, TicketTable> fuzzyQuery(String clue ){
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        Map<ScenicInfoTable,TicketTable> result = new LinkedHashMap<>();
        //创建要用的两张表
        ScenicInfoTable scenicInfoTable;
        TicketTable ticketTable;

        //创建对应的变量
        String location = null;
        Integer last = 0;
        Integer ticketNum = 0;
        Date date = null;
        double price = 0;
        String clew = "%"+clue+"%";
        Integer scenic_id = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql1 = "select * from scenic_info where location like ? order by comment_num DESC";
            String sql2 = "select * from ticket where scenic_id = ?";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            pstmt1.setString(1,clew);
            rs = pstmt1.executeQuery();
            while (rs.next()){
                //封装scenicInfoTable
                scenic_id = rs.getInt("scenic_info_id");
                location = rs.getString("location");
                last = rs.getInt("last");
                Integer comment_num = rs.getInt("comment_num");
                scenicInfoTable = new ScenicInfoTable();
                scenicInfoTable.setLocation(location);
                scenicInfoTable.setLast(last);
                scenicInfoTable.setComment_num(comment_num);

                //查询并封装ticketTable
                pstmt2.setInt(1,scenic_id);
                rs2 = pstmt2.executeQuery();
                rs2.next();
                ticketNum = rs2.getInt("ticket_num");
                date = rs2.getDate("date");
                price = rs2.getDouble("price");
                ticketTable = new TicketTable();
                ticketTable.setTicketNumber(ticketNum);
                ticketTable.setPrice(price);
                ticketTable.setDate(date);
                result.put(scenicInfoTable,ticketTable);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt1,conn);
            JDBCUtil.close(rs2,pstmt2,conn);
        }
        return result;
    }




}
