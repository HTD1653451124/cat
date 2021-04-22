package com.ccj.event.view;


import com.ccj.event.controller.SetScenicInfo;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class AdminMenu extends UserMenu {
    public void adInterface(){
        //设置标签和文本框和按钮
        Label l_location = new Label("设置位置:");
        Label l_last = new Label("设置旅程持续时间:");
        Label l_time = new Label("设置出发时间:");
        Label l_ticketNum = new Label("设置票数:");
        Label l_price = new Label("设置价格:");

        TextField t_location = new TextField();
        TextField t_last = new TextField();
        DatePicker datePicker = new DatePicker();
        TextField t_ticketNum = new TextField();
        TextField t_price = new TextField();

        Button b_confirm = new Button("确认");


        //添加到布局中
        GridPane gr = new GridPane();
        gr.add(l_location,0,0);
        gr.add(l_last,0,1);
        gr.add(l_time,0,2);
        gr.add(l_ticketNum,0,3);
        gr.add(l_price,0,4);
        gr.add(t_location,1,0);
        gr.add(t_last,1,1);
        gr.add(datePicker,1,2);
        gr.add(t_ticketNum,1,3);
        gr.add(t_price,1,4);
        gr.add(b_confirm,0,5);

        //设置位置为中心
        gr.setHgap(5);
        gr.setVgap(10);
        gr.setAlignment(Pos.CENTER);

        //添加场景
        Stage stage = new Stage();
        Scene scene = new Scene(gr);
        stage.setScene(scene);
        stage.setHeight(700);
        stage.setWidth(600);
        stage.show();

        //点击确认按钮
        SetScenicInfo setScenicInfo = new SetScenicInfo();
        setScenicInfo.setScenic(b_confirm,t_location,t_last,datePicker,t_ticketNum,t_price);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
