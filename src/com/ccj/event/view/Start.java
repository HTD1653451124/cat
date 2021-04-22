package com.ccj.event.view;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Start extends Application{

    @Override
    public void start(Stage primarystage) throws Exception {
        /*-----------创建对象----------------------*/
        Label l_name = new Label("账号:");
        Label l_password = new Label("密码:");
        TextField t_name = new TextField();
        PasswordField p_password = new PasswordField();

        //选择记住密码时显示密码setText
        //p_password.setText("sadsa");

        Button b_login = new Button("登录");
        Button b_register = new Button("注册");
        Button b_adm = new Button("管理员登录");
        Button b_tour = new Button("游客登陆");
        CheckBox ck2 = new CheckBox("记住密码");


        /*------------设置位置---------------------*/
        GridPane gr = new GridPane();
        gr.add(l_name,0,0);
        gr.add(l_password,0,1);
        gr.add(t_name,1,0);
        gr.add(p_password,1,1 );
        gr.add(b_login,0,2);
        gr.add(b_register,1,2 );
        gr.add(b_adm,2,2);
        gr.add(b_tour,1,4);
        gr.add(ck2,2,4);

        gr.setHgap(5);
        gr.setVgap(10);
        gr.setAlignment(Pos.CENTER);

        //加载场景
        Scene scene = new Scene(gr);
        primarystage.setTitle("登录");
        primarystage.setScene(scene);
        primarystage.setWidth(500);
        primarystage.setHeight(300);
        primarystage.setResizable(false);//设置不可拉伸
        primarystage.show();
        String account = t_name.getText();
        //t_name.textProperty().addListener();
        String password = p_password.getText();



        //点击注册按钮
        Register register = new Register();
        register.regieter(b_register);

        //点击登录按钮,点击管理员按钮(管理员不能注册，已提前设置管理员，只能登录，账号：11110000，密码：00001111)

        Login click_login = new Login();
        click_login.login(b_login,b_adm,t_name,p_password);

        //点击游客登陆
        TouristMenu touristMenu = new TouristMenu();
        touristMenu.touristMenu(b_tour);

    }

    /*
    * 显示提示小弹框
    * */
    public void tips(String string){
        Label success = new Label(string);
        GridPane gr2 = new GridPane();
        gr2.add(success,0,0);
        success.setAlignment(Pos.CENTER);
        Stage stageSuccess = new Stage();
        Scene scene2 = new Scene(gr2);
        stageSuccess.setScene(scene2);
        stageSuccess.setHeight(200);
        stageSuccess.setWidth(200);
        stageSuccess.show();
    }


}
