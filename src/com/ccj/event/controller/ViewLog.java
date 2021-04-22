package com.ccj.event.controller;

import com.ccj.event.entity.LogTable;
import com.ccj.event.entity.ScenicInfoTable;
import com.ccj.event.service.Getlog;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class ViewLog extends Application {

    public void viewLog(Button button,Integer sid,Integer uid){
        Pagination pagination = new Pagination();
        pagination.setLayoutX(50);
        pagination.setLayoutY(50);
        pagination.setMinSize(400,400);
        pagination.setStyle("-fx-border-color:red;");
        pagination.setPageFactory((Integer pageIndex) ->createPage(pageIndex,sid,uid));
        Stage stage = new Stage();
        Group group = new Group(pagination);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(600);
        stage.show();

    }
    public VBox createPage(Integer pageIndex,Integer sid,Integer uid){
        VBox box = new VBox(5);

            //Sql sql = new Sql();
            Getlog getlog1 = new Getlog();
            Map<LogTable, ScenicInfoTable> getlog = getlog1.getlog(uid,sid);
            int page = pageIndex * 10;
            int i = page;
            int size = getlog.size();

            for (LogTable lt:getlog.keySet()) {
                if (i>=size) break;
                VBox elem = new VBox();
                ScenicInfoTable st = getlog.get(lt);
                String location = st.getLocation();
                Date date = lt.getLogDate();
                Label label = new Label("您在"+date+"买了去往"+location+"的票");
                elem.getChildren().addAll(label);
                box.getChildren().add(elem);
                i++;
            }


        return box;
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
