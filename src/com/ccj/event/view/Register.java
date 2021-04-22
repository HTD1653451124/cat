package com.ccj.event.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class Register extends Start {

    public void regieter(Button b_register){
        //点击注册按钮
        b_register.setOnMouseClicked(event->{
            Label l_vir = new Label("昵称:");
            Label l_name1 = new Label("账号:");
            Label l_password1 = new Label("密码:");
            Label l_password2 =new Label("确认密码");
            Label l_payPassword1 = new Label("支付密码");
            Label l_payPassword2 = new Label("确认支付密码");
            TextField t_name1 = new TextField();
            TextField t_name2 = new TextField();
            PasswordField p_password1 = new PasswordField();
            PasswordField p_password2 = new PasswordField();
            PasswordField p_password3 = new PasswordField();
            PasswordField p_password4 = new PasswordField();
            Button b_determine = new Button("确认");
            Button b_return = new Button("返回");


            //设置位置
            GridPane gr1 = new GridPane();
            gr1.add(l_vir,0,0);
            gr1.add(t_name2,1,0);
            gr1.add(l_name1,0,1);
            gr1.add(l_password1,0,2);
            gr1.add(l_password2,0,3);
            gr1.add(t_name1,1,1);
            gr1.add(p_password1,1,2);
            gr1.add(p_password2,1,3);
            gr1.add(l_payPassword1,0,4);
            gr1.add(p_password3,1,4);
            gr1.add(l_payPassword2,0,5);
            gr1.add(p_password4,1,5);
            gr1.add(b_determine,0,6);
            gr1.add(b_return,1,6);
            gr1.setAlignment(Pos.CENTER);


            //加载场景
            Stage register = new Stage();
            register.setTitle("注册");
            Scene scene1 = new Scene(gr1);
            register.setScene(scene1);
            register.setWidth(500);
            register.setHeight(500);
            register.show();

            //点击返回按钮
            RegisterClose registerClose = new RegisterClose();
            registerClose.close(register,b_return);

            //点击确定按钮
            RegisterDetermine registerDetermine = new RegisterDetermine();
            registerDetermine.determine(b_determine,t_name1,t_name2,p_password1,p_password2,p_password3,p_password4);


        });
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
