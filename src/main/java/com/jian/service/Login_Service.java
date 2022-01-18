package com.jian.service;

import com.jian.entity.User;
import com.jian.globalDatas.Global_Datas;
import com.jian.utils.Base64Util;
import com.jian.utils.HmacSHA512_Util;
import com.jian.utils.HttpClientUtil;
import com.jian.utils.JsonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author qi
 * 登录服务
 */
public class Login_Service {
    public static boolean login(String userName, String passWord) {
        if ("".equals(userName) || "".equals(passWord)) {
            return false;
        }
//        String url = "http://localhost:8081/Yi/Login/getPassword";
        boolean result;
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userName", Base64Util.encode(userName));
            String s_user = HttpClientUtil.doPost(Global_Datas.login_url, hashMap);
            if ("".equals(s_user) || s_user == null) {
                return false;
            }
//            System.out.println(s_user);
            User user = JsonUtils.jsonToList("[" + Base64Util.decode(s_user) + "]", User.class).get(0);
            if (user == null) {
                return false;
            }
            user.setCreateDate(Base64Util.decode(user.getCreateDate()));
            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = timeFormat.parse(user.getCreateDate());

            String createTime = String.valueOf(date.getTime() / 1000); // 时间戳转换日期
            result = Base64Util.decode(user.getPassword()).equals(HmacSHA512_Util.HmacSHA512(passWord, createTime));
            if (result) {
                Global_Datas.userName = userName;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
