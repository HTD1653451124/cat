package com.ccj.event.controller;

import com.ccj.event.controller.Buy;
import com.ccj.event.dao.Sql;
import com.ccj.event.entity.ScenicInfoTable;
import com.ccj.event.entity.TicketTable;
import com.ccj.event.view.UserMenu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.control.Pagination;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Buy extends UserMenu {

    public void buy (Button buyTicket, String payPassword, Integer uid, Double price, Integer sid , int ticketNumber){
        Sql sql = new Sql();
        //点击购票按钮
        buyTicket.setOnMouseClicked(event -> {

            //显示支付界面
            javafx.scene.control.Label label = new javafx.scene.control.Label("请输入支付密码");
            PasswordField pay = new PasswordField();
            javafx.scene.control.Button determine = new javafx.scene.control.Button("确认");
            label.setLayoutX(40);
            label.setLayoutY(40);
            pay.setLayoutX(60);
            pay.setLayoutY(60);
            determine.setLayoutY(90);
            determine.setLayoutX(50);
            Stage stage = new Stage();
            Group group = new Group(label,pay,determine);
            Scene scene = new Scene(group);
            stage.setScene(scene);
            stage.setHeight(400);
            stage.setWidth(400);
            stage.setResizable(false);
            stage.show();

            //点击确认按钮
            determine.setOnMouseClicked(event1 -> {

                //获取当前时间
                SimpleDateFormat df = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                Date date  = new Date();
                String now = df.format(date);
                //判断密码是否正确
                if (pay.getText().equals(payPassword)){
                    //判断余额是否足够
                    if (sql.pay(uid, price)){
                        //判断票数是否有剩余
                        if ( ticketNumber > 0 ){
                            //向日志中添加记录
                            boolean insertLog = sql.insertLog(sid,now);
                            //获取最新的日志id
                            Integer lid = sql.getLogId();
                            //更改ticket表中数据
                            boolean setTicket = sql.setTicket(sid,uid,lid);
                            if (insertLog && setTicket){
                                //显示成功窗口提示
                                tips("购票成功");
                                //关闭输入支付密码的界面
                                stage.hide();
                            }else {//sql语句返回false
                                tips("购票失败");
                            }
                        }else {//票数小于0
                            tips("不好意思，已无票");
                        }
                    }else {//余额不足
                        tips("余额不足");
                    }

                }else {//密码错误
                    tips("密码错误");
                }

            });

        });

    }

    @Override
    public void start(Stage primaryStage) {

    }
}
