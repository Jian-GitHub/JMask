package com.jian.controller;

import java.io.*;
import java.net.URL;
import java.util.Base64;
//import java.util.ResourceBundle;

import com.jian.service.DealData_Service;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Jian Qi
 * @Date 2021/10/6 10:27 上午
 * @Description 图片处理界面控制器
 * @Version 1
 */
public class DealImg_View_Controller {

    //    待处理图片路径
    private String dealImagePath = null;

//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView dealImgView;

    @FXML
    private ImageView resultImgView;

    @FXML
    private Label progressLabel;

    @FXML
    private ProgressIndicator dealProgressdealProgress;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    /**
     * 点击被处理图片
     *
     * @param event 点击事件
     */
    @FXML
    void clickDealImg(MouseEvent event) {
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
                    borderPane.setCenter(root);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 点击开始按钮
     *
     * @param event 点击事件
     */
    @FXML
    void clickStart(MouseEvent event) {
        if (this.dealImagePath == null) {
            return;
        }
        String resultImageBase64 = DealData_Service.dealImgByDir(dealImagePath);//接收处理后的图片数据Base64编码
        changeResultImg(resultImageBase64);
    }

    @FXML
    void clickStop(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
        assert dealImgView != null : "fx:id=\"dealImgView\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
        assert resultImgView != null : "fx:id=\"resultImgView\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
        assert dealProgressdealProgress != null : "fx:id=\"dealProgressdealProgress\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
        assert progressLabel != null : "fx:id=\"progressLabel\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
        assert stopButton != null : "fx:id=\"stopButton\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
    }

    public void changeDealImg(String dealImagePath) {
        this.dealImagePath = dealImagePath;
        Image image = new Image("file:" + dealImagePath);
        dealImgView.setImage(image);
        Platform.runLater(() -> {
//                dealImgView.setFitHeight(image.getHeight() > 350 ? 350 : image.getHeight());
//                dealImgView.setFitWidth(image.getWidth() > 400 ? 400 : image.getWidth());
            dealImgView.setFitHeight(350);
            dealImgView.setFitWidth(400);
        });
    }

    /**
     * 展示处理后的图片
     *
     * @param resultImageBase64 处理后的图片Base64编码
     */
    public void changeResultImg(String resultImageBase64) {
        if ("".equals(resultImageBase64)) {
            return;
        }
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] b = decoder.decode(resultImageBase64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            InputStream in = new ByteArrayInputStream(b);
            Image image = new Image(in);
            in.close();
            resultImgView.setImage(image);
            Platform.runLater(() -> {
                resultImgView.setFitHeight(350);
                resultImgView.setFitWidth(400);
            });
        } catch (Exception e) {
        }

//        Image image = new Image("file:" + resultImage);本地路径图片
    }
}

