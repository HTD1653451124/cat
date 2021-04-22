package com.ccj.event.view;

import com.ccj.event.dao.LoginCheck;
import com.ccj.event.dao.Sql;
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
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

import com.ccj.event.dao.LoginCheck;
import com.ccj.event.dao.Sql;
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

public class RegisterClose extends Application {


    public void close(Stage clo, Button back){
        back.setOnMouseClicked(mouseEvent -> {
            clo.hide();
        });
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
