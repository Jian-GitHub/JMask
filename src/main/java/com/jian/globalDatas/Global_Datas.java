package com.jian.globalDatas;


/**
 * 全局数据
 *
 * @author Qi
 * @Date 2021/10/4 6:45 下午
 */

public class Global_Datas {
    public static String userName = null;
    private final static String server_port = "8081";//服务器端口
//    private final static String server_ip = "https://localhost";//服务器IP地址
//    private final static String python_server_ip = "https://localhost";//服务器IP地址

        private final static String server_ip = "https://www.jian-internet.com";//服务器IP地址
    private final static String python_server_ip = "http://www.jian-internet.com";//python服务器IP地址

    private final static String server_rootdir = "JMask";//服务器根目录
    public final static String registration_url = server_ip + ":" + server_port + "/" + server_rootdir + "/Registration/registration";
    public final static String login_url = server_ip + ":" + server_port + "/" + server_rootdir + "/Login/getPassword";
    public final static String canUseUserName_url = server_ip + ":" + server_port + "/" + server_rootdir + "/Registration/canUseUserName";
    public final static String dealImg_url = server_ip + ":" + server_port + "/" + server_rootdir + "/DealData/dealImg";
    public final static String dealRTM_url = server_ip + ":" + server_port + "/" + server_rootdir + "/DealData/dealRTM";
    //    public final static String dealPythonRTM_url  = "http://127.0.0.1:5000/Mask";
    public final static String dealPythonRTM_url = python_server_ip + ":5000/Mask";
}
