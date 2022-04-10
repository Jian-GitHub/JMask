package com.jian.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jian.threads.RTM_Thread;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class RTM_View_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ProgressIndicator dealProgressdealProgress;

    @FXML
    private Label progressLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private ImageView resultImgView;

    private RTM_Thread rtm_Thread;

    @FXML
    void clickStart(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            rtm_Thread = new RTM_Thread(resultImgView);
            rtm_Thread.start();
        }
    }

    @FXML
    void clickStop(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (rtm_Thread != null && rtm_Thread.isAlive()) {
                rtm_Thread.stopRTM();
            }
        }
    }

    @FXML
    void initialize() {
        assert dealProgressdealProgress != null : "fx:id=\"dealProgressdealProgress\" was not injected: check your FXML file 'RTM_View_FXML.fxml'.";
        assert progressLabel != null : "fx:id=\"progressLabel\" was not injected: check your FXML file 'RTM_View_FXML.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'RTM_View_FXML.fxml'.";
        assert stopButton != null : "fx:id=\"stopButton\" was not injected: check your FXML file 'RTM_View_FXML.fxml'.";
        assert resultImgView != null : "fx:id=\"resultImgView\" was not injected: check your FXML file 'RTM_View_FXML.fxml'.";

    }
}
