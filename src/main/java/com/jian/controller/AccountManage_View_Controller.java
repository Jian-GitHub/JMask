package com.jian.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import com.jian.globalDatas.Global_Datas;
import com.jian.service.Registration_Service;
import com.jian.service.User_Service;
import com.jian.utils.Base64Util;
import com.jian.utils.HmacSHA512_Util;
import com.jian.utils.Image_Util;
import com.jian.view.Login_View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Jian Qi
 * @Date 2022/4/4 11:45 下午
 * @Description 用户中心页面控制器
 * @Version 1
 */

public class AccountManage_View_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView userAvatar;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label userIdLabel;

    @FXML
    private Button updateUserNameButton;

    @FXML
    private Button updatePassWordButton;

    @FXML
    private Button removeAccountButton;

    @FXML
    private Label updateUserNameTipLabel;

    @FXML
    private Label updatePassWordTipLabel;

    @FXML
    private Label removeAccountTipLabel;

    @FXML
    private Button submitUpdateUserNameButton;

    @FXML
    private TextField newUserNameField;

    @FXML
    private Label errorNowPassWordLabel;

    @FXML
    private Label tipTextLabel;

    @FXML
    private Button cancelUpdateUserNameButton;

    @FXML
    private Button submitUpdatePassWordButton;

    @FXML
    private Button cancelUpdatePassWordButton;

    @FXML
    private Label errorNewPassWordLabel;

    @FXML
    private Label errorConfirmPassWordLabel;

    @FXML
    private Label errorNewUserNameLabel;

    @FXML
    private PasswordField nowPassWordField;

    @FXML
    private PasswordField newPassWordField;

    @FXML
    private PasswordField confirmPassWordField;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passWordField;

    @FXML
    private Label errorUserNameLabel;

    @FXML
    private Label errorPassWordLabel;

    @FXML
    private Button submitRemoveAccountButton;

    @FXML
    private Button cancelRemoveAccountButton;

    private boolean canSubmitUserName = false;

    private boolean canSubmitNowPassWord = false;
    private boolean canSubmitNewPassWord = false;
    private boolean canSubmitConfirmPassWord = false;

    @FXML
    void openRemoveAccount(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            userNameField.setText("");
            passWordField.setText("");
            errorUserNameLabel.setVisible(false);
            errorPassWordLabel.setVisible(false);
            displayOrHideButtons(false);
            displayOrHideRemoveAccount(true);
        }
    }

    @FXML
    void openUpdatePassWord(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            nowPassWordField.setText("");
            newPassWordField.setText("");
            confirmPassWordField.setText("");
            errorNowPassWordLabel.setVisible(false);
            errorNewPassWordLabel.setVisible(false);
            errorConfirmPassWordLabel.setVisible(false);
            displayOrHideButtons(false);
            displayOrHideUpdatePassWord(true);
            passwordCheckIn();
            confirmPasswordCheckIn();
        }
    }

    @FXML
    void updateNewUserName(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

        }
    }

    @FXML
    void openUpdateUserName(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            newUserNameField.setText("");
            displayOrHideButtons(false);
            displayOrHideUpdateUserName(true);
            userNameCheckIn();
        }
    }

    @FXML
    void cancel(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            newUserNameField.setText("");
            nowPassWordField.setText("");
            newPassWordField.setText("");
            confirmPassWordField.setText("");
            tipTextLabel.setVisible(false);
            displayOrHideButtons(true);
            displayOrHideUpdateUserName(false);
            displayOrHideUpdatePassWord(false);
            displayOrHideRemoveAccount(false);
        }
    }

    @FXML
    void submitUpdateUserName(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && canSubmitUserName) {
            if (Registration_Service.canUseUserName(newUserNameField.getText())) {
                boolean result = User_Service.updateUserNameByToken(newUserNameField.getText());
                if (result) {
                    Global_Datas.userName = newUserNameField.getText();
                    Global_Datas.user_view_controller.updateUserName();
                    userNameLabel.setText(Global_Datas.userName);
                    displayOrHideUpdateUserName(false);
                    displayOrHideButtons(true);
                } else {
                    errorNewUserNameLabel.setText("更新错误，请稍后再试");
                    errorNewUserNameLabel.setVisible(true);
                }
            } else {
                errorNewUserNameLabel.setText("用户名不可用");
                errorNewUserNameLabel.setVisible(true);
                canSubmitUserName = false;
                submitUpdateUserNameButton.setDisable(true);
            }
        }
    }

    @FXML
    void submitUpdatePassWord(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Map<String, String> passWordMap = User_Service.getPassWordByToken();
            if (passWordMap == null) {
                errorNowPassWordLabel.setText("未知错误");
                errorNowPassWordLabel.setVisible(true);
                return;
            }
            String nowEncodePassWOrd = HmacSHA512_Util.HmacSHA512(nowPassWordField.getText(), passWordMap.get("time"));
            if (!nowEncodePassWOrd.equals(passWordMap.get("passWord"))) {
                errorNowPassWordLabel.setText("密码有误");
                errorNowPassWordLabel.setVisible(true);
                return;
            }
            String newEncodePassWOrd = Base64Util.encode(HmacSHA512_Util.HmacSHA512(newPassWordField.getText(), passWordMap.get("time")));
            if (User_Service.updateUserPassWordByToken(newEncodePassWOrd)){
                displayOrHideUpdatePassWord(false);
                displayOrHideButtons(true);
                nowPassWordField.setText("");
                newPassWordField.setText("");
                confirmPassWordField.setText("");
                errorNowPassWordLabel.setVisible(false);
                errorNewPassWordLabel.setVisible(false);
                errorConfirmPassWordLabel.setVisible(false);
            }else {
                errorNowPassWordLabel.setText("未知错误");
                errorNowPassWordLabel.setVisible(true);
            }
        }
    }

    @FXML
    void submitRemoveAccount(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.PRIMARY && !("".equals(userNameField.getText())) && !("".equals(passWordField.getText()))) {
            if (!Global_Datas.userName.equals(userNameField.getText())){
                errorPassWordLabel.setText("用户名或密码有误");
                errorPassWordLabel.setVisible(true);
            }else {
                Map<String, String> passWordMap = User_Service.getPassWordByToken();
                if (passWordMap == null) {
                    errorPassWordLabel.setText("未知错误");
                    errorPassWordLabel.setVisible(true);
                    return;
                }
                String nowEncodePassWOrd = HmacSHA512_Util.HmacSHA512(passWordField.getText(), passWordMap.get("time"));
                if (!nowEncodePassWOrd.equals(passWordMap.get("passWord"))) {
                    errorPassWordLabel.setText("用户名或密码有误");
                    errorPassWordLabel.setVisible(true);
                    return;
                }
                String encodePassWOrd = Base64Util.encode(HmacSHA512_Util.HmacSHA512(passWordField.getText(), passWordMap.get("time")));
                if (User_Service.removeAccount(passWordField.getText(), encodePassWOrd)){
                    displayOrHideRemoveAccount(false);
                    displayOrHideButtons(true);
                    Global_Datas.user_view.close();
                    new Login_View().start(new Stage());
                }else {
                    errorPassWordLabel.setText("未知错误");
                    errorPassWordLabel.setVisible(true);
                    return;
                }
            }
        }
    }

    @FXML
    void updateUserAvatar(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            FileChooser fileChooser = new FileChooser();
            // 设置窗口标题
            fileChooser.setTitle("请选择头像图片");
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
            String avatarBase64 = Base64Util.ImageToBase64String(result.getPath());
            if (User_Service.updateAvatar(avatarBase64)) {
                Image image = new Image("file:" + result.getPath());
                setAvatar(image);
                Global_Datas.user_view_controller.changeAvatar(image);
            }else {

            }
        }
    }

//    @FXML
//    void initialize() {
//        assert userAvatar != null : "fx:id=\"userAvatar\" was not injected: check your FXML file 'AccountManage_View_FXML.fxml'.";
//        assert userNameLabel != null : "fx:id=\"userNameLabel\" was not injected: check your FXML file 'AccountManage_View_FXML.fxml'.";
//        assert userIdLabel != null : "fx:id=\"userIdLabel\" was not injected: check your FXML file 'AccountManage_View_FXML.fxml'.";
//        assert updateUserNameButton != null : "fx:id=\"updateUserNameButton\" was not injected: check your FXML file 'AccountManage_View_FXML.fxml'.";
//        assert updatePassWord != null : "fx:id=\"updatePassWord\" was not injected: check your FXML file 'AccountManage_View_FXML.fxml'.";
//        assert removeAccountButton != null : "fx:id=\"removeAccountButton\" was not injected: check your FXML file 'AccountManage_View_FXML.fxml'.";
//
//    }

    public void setAvatar(Image image) {
//        System.out.println("setAvatar");
        userAvatar.setImage(Image_Util.crop(image));
        userAvatar.setFitWidth(100);
        userAvatar.setFitHeight(100);
    }

    public void setUserName(String userName) {
//        System.out.println("setUserName");
        userNameLabel.setText(userName);
    }

    public void setUserID(String userID) {
//        System.out.println("setUserID");
        userIdLabel.setText(userID);
    }

    private void displayOrHideButtons(boolean state) {
        updateUserNameButton.setVisible(state);
        updatePassWordButton.setVisible(state);
        removeAccountButton.setVisible(state);
        updateUserNameTipLabel.setVisible(state);
        updatePassWordTipLabel.setVisible(state);
        removeAccountTipLabel.setVisible(state);
    }

    private void displayOrHideUpdateUserName(boolean state) {
        tipTextLabel.setText("更改用户名");
        newUserNameField.setVisible(state);
        tipTextLabel.setVisible(state);
        submitUpdateUserNameButton.setVisible(state);
        cancelUpdateUserNameButton.setVisible(state);
        errorNewUserNameLabel.setVisible(false);
    }

    private void displayOrHideRemoveAccount(boolean state) {
        tipTextLabel.setText("注销账户，提交后无法撤销");
        tipTextLabel.setVisible(state);
        userNameField.setText("");
        userNameField.setVisible(state);
        passWordField.setText("");
        passWordField.setVisible(state);
        errorUserNameLabel.setVisible(false);
        errorPassWordLabel.setVisible(false);
        submitRemoveAccountButton.setVisible(state);
        cancelRemoveAccountButton.setVisible(state);
    }

    private void userNameCheckIn() {
        newUserNameField.textProperty().addListener((observable, oldValue, newValue) -> {
//            Border userNameBorder = null;
            if ("".equals(newUserNameField.getText())) {
//                userNameBorder = new Border(new BorderStroke(no, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1.5)));
//                updateUserNameTipLabel.setText("");
//                isUserNameRight = false;
                errorNewUserNameLabel.setVisible(false);
                errorNewUserNameLabel.setText("用户名需在2位至15位之间");
                canSubmitUserName = false;
                submitUpdateUserNameButton.setDisable(true);
            } else if (!"".equals(newUserNameField.getText()) && newUserNameField.getText().length() > 1 && newUserNameField.getText().length() <= 15) {
//                userNameBorder = new Border(new BorderStroke(right, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1.5)));
//                updateUserNameTipLabel.setText("");
//                isUserNameRight = true;
                errorNewUserNameLabel.setVisible(false);
                canSubmitUserName = true;
                submitUpdateUserNameButton.setDisable(false);
            } else if (!"".equals(newUserNameField.getText()) && (newUserNameField.getText().length() == 1 || newUserNameField.getText().length() > 15)) {
//                userNameBorder = new Border(new BorderStroke(error, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1.5)));
//                updateUserNameTipLabel.setPrefWidth(236);
                errorNewUserNameLabel.setVisible(true);
                errorNewUserNameLabel.setText("用户名需在2位至15位之间");
                canSubmitUserName = false;
                submitUpdateUserNameButton.setDisable(true);
//                isUserNameRight = false;
            }

//            userName.setBorder(userNameBorder);
//            canRegistration();
        });
    }

    private void displayOrHideUpdatePassWord(boolean state) {
        tipTextLabel.setText("更改密码");
        tipTextLabel.setVisible(state);
        nowPassWordField.setVisible(state);
        newPassWordField.setVisible(state);
        confirmPassWordField.setVisible(state);
        submitUpdatePassWordButton.setVisible(state);
        cancelUpdatePassWordButton.setVisible(state);
        errorNowPassWordLabel.setVisible(false);
        errorNewPassWordLabel.setVisible(false);
        errorConfirmPassWordLabel.setVisible(false);
    }

    private void passwordCheckIn() {
        nowPassWordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if ("".equals(nowPassWordField.getText())) {
                errorNowPassWordLabel.setText("密码需在8位与15位之间");
                errorNowPassWordLabel.setVisible(true);
                canSubmitNowPassWord = false;
            } else if (!"".equals(nowPassWordField.getText()) && nowPassWordField.getText().length() > 7 && nowPassWordField.getText().length() <= 15) {
                errorNowPassWordLabel.setVisible(false);
                canSubmitNowPassWord = true;
            } else if (!"".equals(nowPassWordField.getText()) && (nowPassWordField.getText().length() < 8 || nowPassWordField.getText().length() > 15)) {
                errorNowPassWordLabel.setText("密码需在8位与15位之间");
                errorNowPassWordLabel.setVisible(true);
                canSubmitNowPassWord = true;
            }
            if (canSubmitNowPassWord && canSubmitNewPassWord && canSubmitConfirmPassWord) {
                submitUpdatePassWordButton.setDisable(false);
            }else {
                submitUpdatePassWordButton.setDisable(true);
            }
        });
        newPassWordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if ("".equals(newPassWordField.getText())) {
                errorNewPassWordLabel.setText("密码需在8位与15位之间");
                errorNewPassWordLabel.setVisible(true);
                canSubmitNewPassWord = false;
                submitUpdatePassWordButton.setDisable(true);
//                isPasswordRight = false;
            } else if (!"".equals(newPassWordField.getText()) && newPassWordField.getText().length() > 7 && newPassWordField.getText().length() <= 15) {
                errorNewPassWordLabel.setVisible(false);
                canSubmitNewPassWord = true;
                submitUpdatePassWordButton.setDisable(false);
//                isPasswordRight = true;
            } else if (!"".equals(newPassWordField.getText()) && (newPassWordField.getText().length() < 8 || newPassWordField.getText().length() > 15)) {
                errorNewPassWordLabel.setText("密码需在8位与15位之间");
                errorNewPassWordLabel.setVisible(true);
                canSubmitNewPassWord = false;
                submitUpdatePassWordButton.setDisable(true);
            }
            if (canSubmitNowPassWord && canSubmitNewPassWord && canSubmitConfirmPassWord) {
                submitUpdatePassWordButton.setDisable(false);
            }else {
                submitUpdatePassWordButton.setDisable(true);
            }
            i_confirmPasswordCheckIn();
        });
    }

    private void confirmPasswordCheckIn() {
        confirmPassWordField.textProperty().addListener((observable, oldValue, newValue) -> {
            i_confirmPasswordCheckIn();
        });
    }

    private void i_confirmPasswordCheckIn() {
//        Border confirmPasswordBorder;
        if (newPassWordField.getText().equals(confirmPassWordField.getText())) {
//            confirmPasswordBorder = new Border(new BorderStroke(right, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1.5)));
//            isConfirmPasswordRight = true;
            errorConfirmPassWordLabel.setVisible(false);
            canSubmitConfirmPassWord = true;
        } else {
//            confirmPasswordBorder = new Border(new BorderStroke(error, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1.5)));
//            isConfirmPasswordRight = false;
            errorConfirmPassWordLabel.setText("两次密码不一致");
            errorConfirmPassWordLabel.setVisible(true);
            canSubmitConfirmPassWord = false;
        }
        if (canSubmitNowPassWord && canSubmitNewPassWord && canSubmitConfirmPassWord) {
            submitUpdatePassWordButton.setDisable(false);
        }else {
            submitUpdatePassWordButton.setDisable(true);
        }
//        confirmPassword.setBorder(confirmPasswordBorder);
//        canRegistration();
    }
}
