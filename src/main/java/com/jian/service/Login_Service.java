package com.jian.service;

import com.alibaba.fastjson.JSON;
import com.jian.entity.Result;
import com.jian.entity.User;
import com.jian.globalDatas.Global_Datas;
import com.jian.utils.Base64Util;
import com.jian.utils.HmacSHA512_Util;
import com.jian.utils.HttpClientUtil;
import com.jian.utils.JsonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            String s_user = HttpClientUtil.doPost(Global_Datas.LOGIN_URL, hashMap);
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
//                System.out.println("OK");
                Global_Datas.userName = userName;
                hashMap.clear();
                hashMap.put("username", userName);
                hashMap.put("password", passWord);
                String resultStr = HttpClientUtil.doPost(Global_Datas.LOGIN_TOKEN_URL, hashMap);
                Result tokenResult = JsonUtils.parse(resultStr, Result.class);
                if (Objects.equals(tokenResult.getCode(), Global_Datas.SUCCESS)){
//                    String[] resultStrs = resultStr.split(":");
//                    String token = resultStrs[resultStrs.length-1].replace(":","").replace("}","").replace("\"", "");
                    Map<String, String> resultMap = (Map<String, String>) tokenResult.getData();
                    Global_Datas.token = resultMap.get("token");
//                    Global_Datas.token = token;
//                    Global_Datas.id = resultMap.get("id");
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
