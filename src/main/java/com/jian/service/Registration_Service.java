package com.jian.service;

import com.jian.entity.Result;
import com.jian.globalDatas.Global_Datas;
import com.jian.utils.Base64Util;
import com.jian.utils.HmacSHA512_Util;
import com.jian.utils.HttpClientUtil;
import com.jian.utils.JsonUtils;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author qi
 * 注册 服务
 */
public class Registration_Service {

    /**
     * @param userName 用户名
     * @param password 密码
     * @return 是否成功（是/否） boolean
     */
    public static boolean registration(String userName, String password) {
//        String url = "http://localhost:8081/Yi/Registration/registration";
        Result result;
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userName", Base64Util.encode(userName));
            String time = String.valueOf(System.currentTimeMillis() / 1000);
            hashMap.put("password", Base64Util.encode(HmacSHA512_Util.HmacSHA512(password, time)));
            hashMap.put("time", Base64Util.encode(time));
            result = JsonUtils.parse(HttpClientUtil.doPost(Global_Datas.REGISTRATION_URL, hashMap),Result.class);
            if (Objects.equals(result.getCode(), Global_Datas.SUCCESS)) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param userName 要查询是否可用的用户名
     * @return 返回是否可用（是/否） boolean
     */
    public static boolean canUseUserName(String userName) {
//        String url = "http://localhost:8081/Yi/Registration/canUseUserName";
        boolean result;
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userName", Base64Util.encode(userName));
            result = Boolean.parseBoolean(HttpClientUtil.doPost(Global_Datas.CAN_USE_USERNAME_URL, hashMap));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
