package com.ccj.event.view;

import javafx.application.Application;
import javafx.scene.control.Button;
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
