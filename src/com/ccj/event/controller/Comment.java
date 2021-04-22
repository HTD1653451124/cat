package com.ccj.event.controller;

import com.ccj.event.dao.Sql;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.awt.image.ImageWatched;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Comment extends Application {

    public void visitComment(Hyperlink link,Integer sid){
        Sql sql = new Sql();
        VBox box1 = new VBox(5);
        Map<String, String> comment = sql.getComment(sid);
        for (String vname : comment.keySet()) {
            VBox elem = new VBox();
            String content = comment.get(vname);
            Label vir = new Label(vname+":");
            Label con = new Label(content);
            Label label = new Label("\n");
            elem.getChildren().addAll(vir,con,label);
            box1.getChildren().add(elem);
        }
        Stage stage = new Stage();
        Group group = new Group(box1);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setHeight(700);
        stage.setWidth(700);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
