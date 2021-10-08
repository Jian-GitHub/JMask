package com.jian.globalDatas;


/**
 * 全局数据
 * @author Qi
 * @Date 2021/10/4 6:45 下午
 */

public class Global_Datas {
    public static String userName = null;
    private final static String server_port = "8081";//服务器端口
    private final static String server_ip = "localhost";//服务器IP地址
    private final static String server_rootdir = "JMask";//服务器根目录
    public final static String registration_url = "http://" + server_ip + ":" + server_port + "/" + server_rootdir + "/Registration/registration";
    public final static String login_url  = "http://" + server_ip + ":" + server_port + "/" + server_rootdir + "/Login/getPassword";
    public final static String canUseUserName_url  = "http://" + server_ip + ":" + server_port + "/" + server_rootdir + "/Registration/canUseUserName";
    public final static String dealImg_url  = "http://" + server_ip + ":" + server_port + "/" + server_rootdir + "/DealData/dealImg";
}
