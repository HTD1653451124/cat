package com.ccj.event.dao;

import com.ccj.event.entity.LogTable;
import com.ccj.event.entity.ScenicInfoTable;
import com.ccj.event.entity.TicketTable;
import com.ccj.event.util.JDBCUtil;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Sql {
    public boolean isRepeat(String account){
        if (account == null){
            return  false;
        }
        Connection conn = null;
        PreparedStatement pstmt= null;
        ResultSet rs = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "select * from user ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                if (account.equals(rs.getString("account"))){
                    return false;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }

        return true;

    }
    /*
    * 注册,向user表中添加数据
    * @param 昵称，用户名，密码
    * @return boolean
    * */
    public boolean register(String virtualName,String account,String password,String payPassword){
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

    /*
    * 删除数据
    * @param account
    * @return
    * */
    public int insert(String account){
        if ( account == null ){
            return 0;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        int num = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "delete from user where account = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,account);
            num = pstmt.executeUpdate();
        } catch (Exception throwables) {
            System.out.println("删除失败,出现异常");
            return 0;
        }finally {
            JDBCUtil.close(pstmt,conn);
        }
        return num;

    }

    /*
    *  修改数据（用户只能修改账号，密码，昵称）
    * */
    public int updateUser(String account,String password,String virName ,int id){
        if (account == null || password==null || virName == null){
            return 0;
        }
        int num = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql= "update user set account = ?,password = ?,virtual_name = ? where user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,account);
            pstmt.setString(2,password);
            pstmt.setString(3,virName);
            pstmt.setInt(4,id);
            num = pstmt.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }
        return num;

    }

    /*
    * 将用户输入的账号和密码与数据库中的表进行对比，若相同则登录成功，反之登录失败
    * */
    public boolean login(String name ,String account,String password){
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

    /*
     * 通过账号查找到昵称
     * */
    public Map getVirName(String account){
        if ( account == null ){
            return null;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map<Integer,String> user = new HashMap();

        String virtual_name = null;
        Integer user_id = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from user where account = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,account);
            rs = pstmt.executeQuery();
            while (rs.next()){
                virtual_name = rs.getString("virtual_name");
                user_id = rs.getInt("user_id");
                user.put(user_id,virtual_name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }
        return user;
    }

    /**
     * 获取支付密码
     * */
    public String getPayPassword(String account){
        String payPassword = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from user where account = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,account);
            rs = pstmt.executeQuery();
            while(rs.next()){
                payPassword = rs.getString("pay_password");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }
        return payPassword;
    }

    /*
     * 获取景点信息
     * */
    public Map<ScenicInfoTable,TicketTable> getScenicInfo(){
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


    /*
     * 购票时日志增加记录
     * */
    public boolean pay( Integer uid,double price){
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        double money = 0;
        try {
            conn = JDBCUtil.getConnection();
            //获取用户的余额并判断
            String sql1 = "select money from user where user_id = ?";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1,uid);
            rs = pstmt1.executeQuery();
            while (rs.next()){
                money = rs.getInt("money");
            }
            if (money<price){
                return false;
            }

            //用户余额-票价
            String sql2 = "update user set money = ? where user_id = ? ";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setDouble(1,money-price);
            pstmt2.setInt(2,uid);
            pstmt2.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt1,conn);
            JDBCUtil.close(rs,pstmt2,conn);
        }
        return true;
    }

    /*
     * 购票时日志增加记录
     * */
    public boolean insertLog(int sid,String time){
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

    /*
     * 获取log表中的最后一行（执行完插入语句的）id
     * */
    public int getLogId(){
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

    /*
    * 更改ticket表中的数据，增加user_and_log表中的数据
    * */
    public boolean setTicket(int sid,Integer uid,int lid){
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

    /*
    * 获取评论和昵称
    * */
    public Map<String,String> getComment(Integer sid){
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        Map<String,String> comment = new HashMap<>();
        String content = null;
        Integer uid = 0;
        String vName = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql1 = "select * from comment where scenic_info_id = ?";
            String sql2 = "select * from user where user_id = ?";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            pstmt1.setInt(1,sid);
            rs = pstmt1.executeQuery();
            while (rs.next()){
                //获取comment表中的user_id，在user表中找到对应的昵称
                content = rs.getString("content");
                uid = rs.getInt("user_id");
                pstmt2.setInt(1,uid);

                rs2 = pstmt2.executeQuery();
                rs2.next();
                vName = rs2.getString("virtual_name");
                comment.put(vName,content);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt1,conn);
            JDBCUtil.close(rs2,pstmt2,conn);
        }
        return comment;
    }

    /*
    * 模糊查询
    * */
    public Map<ScenicInfoTable,TicketTable> fuzzyQuery(String clue ){
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
    public Map<LogTable,ScenicInfoTable> getlog(Integer uid,Integer sid){
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

    /*
    * 管理员添加景点信息
    * */
    public boolean setScenicInfo(String location,String last,String time,String ticketNum,String price){
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

    /**
     * 充值
     * */
    public boolean recharge(String account,String money){
        if (account==null||money==null){
            return false;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "update user set money = money + ? where account = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,money);
            pstmt.setString(2,account);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }finally {
            JDBCUtil.close(pstmt,conn);
        }
        return true;
    }
}
