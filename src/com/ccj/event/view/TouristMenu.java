package com.ccj.event.view;

import com.ccj.event.controller.Comment;
import com.ccj.event.controller.Search;
import com.ccj.event.dao.Sql;
import com.ccj.event.entity.ScenicInfoTable;
import com.ccj.event.entity.TicketTable;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Map;

public class TouristMenu extends Application {
    public void touristMenu(Button button){
        button.setOnMouseClicked(event -> {
            //设置搜素框
            TextField t_serach = new TextField();
            Button b_serach = new Button("搜索");
            t_serach.setLayoutX(150);
            t_serach.setLayoutY(30);
            t_serach.setPrefWidth(700);
            b_serach.setLayoutY(30);
            b_serach.setLayoutX(900);



            //设置分页
            Pagination pagination = new Pagination();
            pagination.setLayoutX(100);
            pagination.setLayoutY(100);
            pagination.setMinSize(800,850);
            pagination.setStyle("-fx-border-color:red;");
            pagination.setPageFactory((Integer pageIndex) ->createPage(pageIndex));

            //加载场景
            Stage stage = new Stage();
            Group group = new Group(t_serach,b_serach,pagination);
            Scene scene = new Scene(group);
            stage.setScene(scene);
            stage.setHeight(1000);
            stage.setWidth(1000);
            stage.setResizable(false);
            stage.show();
            Search search =new Search();
            search.search(b_serach,t_serach,stage,"游客");
        });

    }

    private VBox createPage(Integer pageIndex) {
        Sql sql = new Sql();
        Map<ScenicInfoTable, TicketTable> result = sql.getScenicInfo();
        VBox box = new VBox(5);
        int page = pageIndex * 5;
        int i = page;
        for (ScenicInfoTable sc : result.keySet()) {
            int sid = i+1;
            if (i>= result.size())break;;
            VBox element = new VBox();
            TicketTable tt = result.get(sc);
            String location = sc.getLocation();
            Integer last = sc.getLast();
            Integer comment_num = sc.getComment_num();
            Date date = tt.getDate();
            Double price = tt.getPrice();
            Integer ticketNumber = tt.getTicketNumber();

            Label text = new Label("这是去往"+location+
                    "的旅程，从"+date+"开始，" +
                    "持续"+last+"天---剩余"+
                    ticketNumber+"张票，票价 = "+price);
            //设置字体大小
            text.setStyle("-fx-font-size: 21");
            Hyperlink link = new Hyperlink("评论数:"+comment_num);
            link.setStyle("-fx-font-size: 30");
            link.setVisited(true);


            //点击评论数显示评论
            link.setOnMouseClicked(event -> {
                Comment comment = new Comment();
                comment.visitComment(link,sid);
            });
            //加载场景
            element.getChildren().addAll(text,link);
            box.getChildren().add(element);
            i++;

        }
        return box;
    }


    @Override
    public void start(Stage primaryStage) {

    }
}
