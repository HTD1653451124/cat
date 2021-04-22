package com.ccj.event.controller;

import com.ccj.event.dao.Sql;
import com.ccj.event.entity.ScenicInfoTable;
import com.ccj.event.entity.TicketTable;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Search extends Application {

   public void search (Button button, TextField input,Stage stage,String account){
       button.setOnMouseClicked(event -> {
           Sql sql = new Sql();
           //添加场景
           //设置横线
           Line lineX = new Line(100,0,100,1000);
           //设置竖线，两条线分割整个视窗
           Line lineY = new Line(0,100,1000,100);

           //设置搜索框
           TextField t_serach = new TextField();
           Button b_serach = new Button("搜索");
           t_serach.setLayoutX(150);
           t_serach.setLayoutY(30);
           t_serach.setPrefWidth(700);
           b_serach.setLayoutY(30);
           b_serach.setLayoutX(900);

           //查看日志
           Button seeLog = new Button("购票历史");

           seeLog.setLayoutX(10);
           seeLog.setLayoutY(120);

           //提升作用域
           final String clue = input.getText();
           final Map<ScenicInfoTable, TicketTable> result  = sql.fuzzyQuery(clue);
           Label vName = new Label();

           //充值
           Button recharge = new Button("充值");
           recharge.setLayoutX(20);
           recharge.setLayoutY(180);
           Recharge re = new Recharge();
           re.raiseMoneyMenu(recharge,account);
           String virName = "游客";
           Integer uid = 0;
           //不是游客的情况下才获取昵称
           if (!account.equals("游客")){
               //获取昵称
               Map<Integer,String> map = sql.getVirName(account);
               Set<Integer> set = map.keySet();
               Iterator<Integer> it = set.iterator();
               uid = it.next();
               virName = map.get(uid);

               //显示昵称
               vName = new Label(virName);
               vName.setLayoutY(20);
               vName.setLayoutX(20);
           }

           Pagination pagination = new Pagination();
           pagination.setLayoutX(150);
           pagination.setLayoutY(100);
           pagination.setMinSize(800,850);
           pagination.setStyle("-fx-border-color:red;");
           String finalVirName = virName;
           Integer finalUid = uid;
           pagination.setPageFactory((Integer pageIndex) ->createPage(result, finalVirName, finalUid,pageIndex,sql,seeLog));

           Group group = new Group(lineX,lineY,vName,t_serach,b_serach,pagination,seeLog,recharge);
           Scene scene = new Scene(group);
           stage.setScene(scene);
           stage.setHeight(1000);
           stage.setWidth(1000);
           stage.setResizable(false);
           stage.show();
           search(b_serach,t_serach,stage,account);


       });
   }
        /*
        * 根据模糊查询结果创建page
        * */
    private VBox createPage(Map<ScenicInfoTable, TicketTable> result, String vName, Integer uid, int pageIndex, Sql sql, Button seeLog) {
        VBox box = new VBox(5);
        String payPassword = sql.getPayPassword(vName);
        int size = result.size();
        int page = pageIndex * 5;
        int i = page;

        ViewLog viewLog = new ViewLog();

        for (ScenicInfoTable sc : result.keySet()) {
            int sid = i+1;
            if (i>=size) break;

            //查看日志
            //点击查看日志
            seeLog.setOnMouseClicked(event -> {
                viewLog.viewLog(seeLog,sid,uid);
            });

            VBox elem = new VBox();
            TicketTable tt = result.get(sc);
            String location = sc.getLocation();
            Integer last = sc.getLast();
            Integer comment_num = sc.getComment_num();
            Date date = tt.getDate();
            Double price = tt.getPrice();
            Integer ticketNumber = tt.getTicketNumber();


            javafx.scene.control.Label text = new Label("这是去往"+location+
                    "的旅程，从"+date+"开始，" +
                    "持续"+last+"天---剩余"+
                    ticketNumber+"张票，票价 = "+price);
            //设置字体大小
            text.setStyle("-fx-font-size: 21");
            Hyperlink link = new Hyperlink("评论数:"+comment_num);
            link.setStyle("-fx-font-size: 30");
            link.setVisited(true);


            //点击评论数显示评论
            link.setOnMouseClicked(event -> {
                Comment comment = new Comment();
                comment.visitComment(link,sid);
            });

            //购票按钮
            Button buy = new Button("购票");
            double layoutX = text.getLayoutX();
            double layoutY = text.getLayoutY();
            buy.setLayoutX(layoutX);
            buy.setLayoutY(layoutY);


            Buy buy1 = new Buy();
            buy1.buy(buy,payPassword,uid,price,sid,ticketNumber);
            i++;
            elem.getChildren().addAll(text,link,buy);
            box.getChildren().add(elem);
        }
        return box;
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
