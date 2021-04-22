package com.ccj.event.view;

import com.ccj.event.dao.LoginCheck;
import com.ccj.event.dao.Sql;
import com.ccj.event.entity.UserTable;
import com.ccj.event.service.Check;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.awt.*;

public class RegisterDetermine extends Start{
    UserTable user = new UserTable();
    public void determine(Button button,TextField inputName ,TextField intputVirtualName,PasswordField inputPassword1,PasswordField inputPassword2,PasswordField inputPassword3,PasswordField inputPassword4){

        //点击注册
        button.setOnMouseClicked(mouseEvent -> {
            //获取文本框和密码框的输入
            String username = inputName.getText();
            String virtualName = intputVirtualName.getText();
            String password1 = inputPassword1.getText();
            String password2 = inputPassword2.getText();
            String password3 = inputPassword3.getText();
            String password4 = inputPassword4.getText();



            //先检查数据的合法性，再用sql语句传输进数据库，最后加载提示弹窗
            Check check = new Check();
            if (check.checkRegister(username,virtualName,password1,password2,password3,password4)){
                Sql sql = new Sql();
                if (sql.isRepeat(username)){
                    if (sql.register(virtualName,username,password1,password3)){
                        tips("注册成功");
                    }else {
                        tips("注册失败");
                    }
                }else {
                    tips("该用户已存在");
                }

            }else {
                tips("注册失败");
            }
            //创建一个user对象，设置值
            user.setVirtualName(virtualName);
            user.setPhoneNum(username);
            user.setPassword(password1);
            user.setPayPassword(password4);
            user.setMoney(0);
        });
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
