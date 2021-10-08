package com.jian.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jian.service.DealData_Service;
import com.jian.utils.Base64Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * @author Jian Qi
 * @Date 2021/10/6 10:27 上午
 * @Description 图片处理界面控制器
 * @Version 1
 */
public class DealImg_View_Controller {

//    待处理图片路径
    private String dealImagePath = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView dealImgView;

    @FXML
    private ImageView resultImgView;

    @FXML
    private Label progressLabel;

    @FXML
    private ProgressBar dealProgress;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    void clickDealImg(MouseEvent event) {

    }

    @FXML
    void clickStart(MouseEvent event) {
        if (this.dealImagePath == null) {
            return;
        }
        String resultImgDir = DealData_Service.dealImg(dealImagePath);
        changeResultImg(resultImgDir);
    }

    @FXML
    void clickStop(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert dealImgView != null : "fx:id=\"dealImgView\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
        assert resultImgView != null : "fx:id=\"resultImgView\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
        assert progressLabel != null : "fx:id=\"progressLabel\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
        assert dealProgress != null : "fx:id=\"dealProgress\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";
        assert stopButton != null : "fx:id=\"stopButton\" was not injected: check your FXML file 'DealImg_View_FXML.fxml'.";

    }

    public void changeDealImg(String dealImagePath) {
        this.dealImagePath = dealImagePath;
        Image image = new Image("file:" + dealImagePath);
        dealImgView.setImage(image);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                dealImgView.setFitHeight(image.getHeight() > 350 ? 350 : image.getHeight());
//                dealImgView.setFitWidth(image.getWidth() > 400 ? 400 : image.getWidth());
                dealImgView.setFitHeight(350);
                dealImgView.setFitWidth(400);
            }
        });
    }

    public void changeResultImg(String resultImagePath) {
        Image image = new Image("file:" + resultImagePath);
        resultImgView.setImage(image);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                resultImgView.setFitHeight(350);
                resultImgView.setFitWidth(400);
            }
        });
    }
}

