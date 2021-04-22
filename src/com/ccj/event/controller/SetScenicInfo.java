package com.ccj.event.controller;

import com.ccj.event.service.Check;
import com.ccj.event.view.AdminMenu;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SetScenicInfo extends AdminMenu {

    public void setScenic(Button button, TextField t_location, TextField t_last, DatePicker datePicker,TextField t_num,TextField t_price){
        //点击确认按钮，将输入写入数据库
        button.setOnMouseClicked(event -> {
            com.ccj.event.service.SetScenicInfo setScenicInfo1 = new com.ccj.event.service.SetScenicInfo();
            Check check = new Check();

            //获取输入的信息
            String location = t_location.getText();
            String last = t_last.getText();

            //获取时间
            LocalDate value = datePicker.getValue();
            DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String time = value.format(format1);

            //获取输入的信息
            String ticketNum = t_num.getText();
            String price = t_price.getText();

            //判断输入是否合法
            if (check.checkAdm(last,ticketNum,price)){
                if (setScenicInfo1.setScenicInfo(location,last,time,ticketNum,price)){
                    tips("添加成功");
                }else {
                    tips("添加失败");
                }
            }else {//输入有误
                tips("输入有误");
            }
        });

    }


    @Override
    public void start(Stage primaryStage) {

    }
}
