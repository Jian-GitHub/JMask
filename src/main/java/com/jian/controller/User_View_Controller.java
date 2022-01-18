package com.jian.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jian.globalDatas.Global_Datas;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class User_View_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane basePane;

    @FXML
    private Label userName;

    @FXML
    private ImageView userAvatar;

    @FXML
    private BorderPane borderPane;

    @FXML
    void clickUserAvatar(MouseEvent event) {

    }

    @FXML
    void RealTimeMonitoring(ActionEvent event) {
        BorderPane root;
        String fxml = "fxmls/RTM_View_FXML.fxml";
        URL url = getClass().getClassLoader().getResource(fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        try {
//            result.getPath();
            root = fxmlLoader.load();
            RTM_View_Controller controller = fxmlLoader.getController();
            root.setPrefWidth(1200);
            root.setPrefHeight(670);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    borderPane.setBottom(root);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @FXML
    void openFile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        // 设置窗口标题
        fileChooser.setTitle("请选择需处理的图片");
        // 初始打开的位置
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("image", "*.jpg", "*.png", "*.jpeg")
        );
         /*
         显示文件选择窗口并接收其返回值如果没有选择任何文件则返回NULL，有则返回一个File类型的值。showOpenDialog这个方法要设定一个FileChooser的所有者窗口，如果设置了FileChooser的所有者窗口，则在显示文件对话框时，FileChooser所有者中所有窗口的输入都会被阻塞。
         */
        File result = fileChooser.showOpenDialog(new Stage());

        if (result == null) {
            return;
        }

        BorderPane root;
        String fxml = "fxmls/DealImg_View_FXML.fxml";
        URL url = getClass().getClassLoader().getResource(fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        try {
//            result.getPath();
            root = fxmlLoader.load();
            DealImg_View_Controller controller = fxmlLoader.getController();
            controller.changeDealImg(result.getPath());
//            root.setBackground(null);
//            root.prefWidthProperty().bind(borderPane.widthProperty());
//            root.prefHeightProperty().bind(borderPane.heightProperty());
            root.setPrefWidth(1200);
            root.setPrefHeight(670);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    borderPane.setBottom(root);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @FXML
    void quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize() {
        assert basePane != null : "fx:id=\"basePane\" was not injected: check your FXML file 'User_View_FXML.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'User_View_FXML.fxml'.";
        assert userAvatar != null : "fx:id=\"userAvatar\" was not injected: check your FXML file 'User_View_FXML.fxml'.";
        assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'User_View_FXML.fxml'.";

        userName.setText(Global_Datas.userName);
    }
}
