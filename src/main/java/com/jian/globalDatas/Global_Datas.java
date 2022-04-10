package com.jian.globalDatas;


import com.jian.controller.User_View_Controller;
import com.jian.view.User_View;

/**
 * 全局数据
 *
 * @author Qi
 * @Date 2021/10/4 6:45 下午
 */

public class Global_Datas {
    public static String userName = null;
    public static String token = null;
    public static String id = null;
    public static String avatarURL = null;
    public static User_View_Controller user_view_controller = null;
    public static User_View user_view = null;

    private final static String server_port = "8081";//服务器端口

    public final static Integer SUCCESS = 20000;
    public final static Integer ERROR = 50008;

    /* 本地 */
//    private final static String SERVER_IP = "https://localhost";//服务器IP地址
//    private final static String PYTHON_SERVER_IP = "https://localhost";//服务器IP地址

    /* 服务器 */
    private final static String SERVER_IP = "https://www.jian-internet.com";//服务器IP地址
    private final static String PYTHON_SERVER_IP = "http://www.jian-internet.com";//python服务器IP地址

    private final static String SERVER_ROOTDIR = "JMask";//服务器根目录

    /* Python 服务器URL */
    public final static String DEAL_PYTHON_RTM_URL = PYTHON_SERVER_IP + ":5000/Mask";

    /* Java 服务器URL */
    public final static String REGISTRATION_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/Registration/registration";
    public final static String LOGIN_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/Login/getPassword";
    public final static String CAN_USE_USERNAME_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/Registration/canUseUserName";
    public final static String DEALIMG_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/DealData/dealImg";
    public final static String DEALRTM_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/DealData/dealRTM";
    //    public final static String dealPythonRTM_url  = "http://127.0.0.1:5000/Mask";
    public final static String GET_USER_INFO_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/User/getUserAvatarByUserName";
//    public final static String getUserInfoByToken_url = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/User/getUserInfoByToken";
    public final static String LOGIN_TOKEN_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/WebLogin";
    public final static String UPDATE_USERNAME_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/User/updateUserNameByToken";
    public final static String GET_PASSWORD_BY_TOKEN_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/User/getPassWordByToken";
    public final static String UPDATE_USER_PASSWORD_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/User/updateUserPassWord";
    public final static String REMOVE_ACCOUNT_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/User/removeAccount";
    public final static String UPDATE_AVATAR_URL = SERVER_IP + ":" + server_port + "/" + SERVER_ROOTDIR + "/User/uploadAvatarBase64";
}
