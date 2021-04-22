package com.ccj.event.service;

import com.ccj.event.dao.ISetScenicInfo;
import com.ccj.event.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetScenicInfo implements ISetScenicInfo<Boolean> {
    /*
     * 管理员添加景点信息
     * */
    @Override
    public Boolean setScenicInfo(String location,String last,String time,String ticketNum,String price){
        if ( location==null || last == null || time == null || ticketNum == null || price == null ){
            return false;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        Integer sid = 0;
        try{
            conn = JDBCUtil.getConnection();
            //两个表要么都插入成功，要么都失败，所以要开启事务
            conn.setAutoCommit(false);
            //向scenic_info表中插入数据
            String sql = "insert into scenic_info (scenic_info_id,location,last,comment_num) values(null,?,?,0)";
            //向ticket表中插入数据
            String sql1 = "insert into ticket (ticket_id,ticket_num,date,price,scenic_id) values(null,?,?,?,?)";
            //查询scenic_info表，目的是获得主键来赋值到ticket表中
            String sql2 = "select * from scenic_info ";
            pstmt = conn.prepareStatement(sql);
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            pstmt.setString(1,location);
            pstmt.setString(2,last);


            //获取scenic_info的id
            pstmt.executeUpdate();
            rs = pstmt2.executeQuery();
            while (rs.next()){
                sid = rs.getInt("scenic_info_id");
            }

            pstmt1.setString(1,ticketNum);
            pstmt1.setString(2,time);
            pstmt1.setString(3,price);
            pstmt1.setInt(4,sid);
            pstmt1.executeUpdate();
            //提交事务
            conn.commit();
        } catch (Exception throwables) {
            try {
                //回滚事务
                if (conn != null)
                    conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
            JDBCUtil.close(rs,pstmt1,conn);
            JDBCUtil.close(rs,pstmt2,conn);
        }
        return true;
    }
}
