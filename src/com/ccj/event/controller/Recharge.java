package com.ccj.event.controller;

import com.ccj.event.service.GetPayPassword;
import com.ccj.event.view.UserMenu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Recharge extends UserMenu {

    public void raiseMoneyMenu(Button button,String account){
        button.setOnMouseClicked(event -> {
            //显示充值界面
            Label label = new Label("充值金额");
            TextField textField = new TextField();
            Label label1 = new Label("密码");
            PasswordField passwordField = new PasswordField();
            Button determin = new Button("确认");
            GridPane gr = new GridPane();
            gr.add(label,0,0);
            gr.add(textField,1,0);
            gr.add(label1,0,1);
            gr.add(passwordField,1,1);
            gr.add(determin,0,2);
            gr.setAlignment(Pos.CENTER);
            Stage stage = new Stage();
            Scene scene = new Scene(gr);
            stage.setWidth(600);
            stage.setHeight(700);
            stage.setScene(scene);
            stage.show();

            //调用充值方法
            raiseMoney(determin,account,passwordField,textField);

        });
    }
    public void raiseMoney(Button determin,String account,PasswordField pf,TextField tf){
        determin.setOnMouseClicked(event -> {
            com.ccj.event.service.Recharge recharge1 = new com.ccj.event.service.Recharge();
            GetPayPassword getPayPassword1 = new GetPayPassword();
            //首先检查密码是否正确，再添加到表中
            if (pf.getText().equals(getPayPassword1.getPayPassword(account))){
                if (recharge1.recharge(account,tf.getText())){
                    tips("充值成功");
                }else {
                    tips("充值失败");
                }
            }else {
                tips("密码错误");
            }


        });
    }


    @Override
    public void start(Stage primaryStage) {

    }
}
