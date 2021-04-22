package com.ccj.event.view;

import com.ccj.event.dao.Sql;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login extends Start {

    /*
    * 点击登录按钮
    * */
    public void login(Button b_login, Button b_adm, TextField tf, PasswordField pf){
        b_login.setOnMouseClicked(event->{
            String account = tf.getText();
            String password = pf.getText();
            if (account == null || password == null){
                return;
            }

                Sql sqlLogin = new Sql();
                if (sqlLogin.login("user",account,password)){
                    //登录成功
                    UserMenu menu  = new UserMenu();
                    menu.mainMenu(account);

                }else{
                    //登录失败，跳出弹框提示
                    tips("登录失败");
                }
        });

        /*
        * 点击管理员登录按钮
        * */
        b_adm.setOnMouseClicked(mouseEvent -> {
            String account = tf.getText();
            String password = pf.getText();
            String tableName = "administrators";
            Sql sqlLogin = new Sql();
            if (sqlLogin.login(tableName,account,password)){
                //登录成功，进入管理员界面
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.adInterface();
            }else{
                //登录失败，跳出弹框提示
                tips("登陆失败");
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
