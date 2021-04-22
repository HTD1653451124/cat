package com.ccj.event.view;

import com.ccj.event.controller.*;
import com.ccj.event.entity.ScenicInfoTable;
import com.ccj.event.entity.TicketTable;
import com.ccj.event.service.GetPayPassword;
import com.ccj.event.service.GetScenicInfo;
import com.ccj.event.service.GetVirName;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.control.Pagination;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.util.*;

public class UserMenu extends Start {
    public void mainMenu(String account){
        GetVirName getVirName1 = new GetVirName();
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

        //充值
        Button recharge = new Button("充值");
        recharge.setLayoutX(20);
        recharge.setLayoutY(180);
        Recharge re = new Recharge();
        re.raiseMoneyMenu(recharge,account);

        //获取昵称

        Map<Integer,String> map = getVirName1.getVirName(account);
        Set<Integer> set = map.keySet();
        Iterator<Integer> it = set.iterator();
        Integer uid = it.next();
        String virName = map.get(uid);

        //显示昵称
        Label vName = new Label(virName);
        vName.setLayoutY(20);
        vName.setLayoutX(20);

        Stage stage = new Stage();
        //显示景点信息和票价
        Pagination pagination1 = new Pagination();
        pagination1.setLayoutX(100);
        pagination1.setLayoutY(100);
        pagination1.setMinSize(800,850);
        pagination1.setStyle("-fx-border-color:red;");
        pagination1.setPageFactory((Integer pageIndex) ->createPage(pageIndex,account,uid,seeLog,b_serach,t_serach,stage));


        //加载场景

        Group group = new Group(lineX,lineY,seeLog,vName,t_serach,b_serach,recharge,pagination1);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setHeight(1000);
        stage.setWidth(1000);
        stage.setResizable(false);
        stage.show();


    }

    /*
    * 创建page显示信息
    * */
    public VBox createPage(int pageIndex,String account,Integer uid,Button seeLog,Button b_serach ,TextField t_serach,Stage stage) {
        GetScenicInfo getScenicInfo1 = new GetScenicInfo();
        GetPayPassword getPayPassword1 = new GetPayPassword();
        Map<ScenicInfoTable, TicketTable> result = getScenicInfo1.getScenicInfo();
        int size = result.size();

        String payPassword = getPayPassword1.getPayPassword(account);
        VBox box = new VBox(5);
        int page = pageIndex * 5;
        int i = page;
        ViewLog viewLog = new ViewLog();
        for (ScenicInfoTable sc : result.keySet()) {
            int sid = i+1;
            //退出循环的条件 ；i>=size;
            if (i>=size) break;
            //点击查看日志
            seeLog.setOnMouseClicked(event -> {
                viewLog.viewLog(seeLog,sid,uid);
            });

            Search search = new Search();
            search.search(b_serach,t_serach,stage,account);

            VBox element = new VBox();
            TicketTable tt = result.get(sc);
            String location = sc.getLocation();
            Integer last = sc.getLast();
            Integer comment_num = sc.getComment_num();
            Date date = tt.getDate();
            Double price = tt.getPrice();
            Integer ticketNumber = tt.getTicketNumber();

            Label text = new Label("这是去往"+location+
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

            //加载场景
            element.getChildren().addAll(text,link,buy);
            box.getChildren().add(element);
            i++;

        }
        return box;
    }



    @Override
    public void start(Stage primaryStage) {

    }
}
