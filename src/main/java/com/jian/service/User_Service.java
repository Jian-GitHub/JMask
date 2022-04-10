package com.jian.service;

import com.jian.entity.Result;
import com.jian.globalDatas.Global_Datas;
import com.jian.utils.Base64Util;
import com.jian.utils.HttpClientUtil;
import com.jian.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Jian Qi
 * @Date 2022/4/1 10:24 上午
 * @Description 用户相关
 * @Version 1
 */
public class User_Service {
    /**
     * 获取用户信息
     * @return
     */
    public static Map<String, String> getUserInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userName", Base64Util.encode(Global_Datas.userName));
        String userInfoStr = HttpClientUtil.doPost(Global_Datas.GET_USER_INFO_URL, hashMap);
        if ("".equals(userInfoStr) || userInfoStr == null) {
            return null;
        }
//        System.out.println(userInfoStr);
        Result result = JsonUtils.parse(userInfoStr, Result.class);
//        System.out.println(result);
        if (Objects.equals(result.getCode(), Global_Datas.SUCCESS)) {
            return (Map<String, String>) result.getData();
        }
        return null;
    }

    /**
     * 修改用户名
     * @param newUserName 新用户名
     * @return
     */
    public static boolean updateUserNameByToken(String newUserName) {
        HashMap<String, String> data = new HashMap<>();
        data.put("token", Global_Datas.token);
        data.put("newUserName", newUserName);
        String resultStr = HttpClientUtil.doPost(Global_Datas.UPDATE_USERNAME_URL, data);
        Result result = JsonUtils.parse(resultStr, Result.class);
        if (Objects.equals(result.getCode(), Global_Datas.SUCCESS)) {
            return true;
        }
        return false;
    }

    /**
     * 获取用户密码
     * @return
     */
    public static Map<String, String> getPassWordByToken() {
        HashMap<String, String> data = new HashMap<>();
        data.put("token", Global_Datas.token);
        String resultStr = HttpClientUtil.doPost(Global_Datas.GET_PASSWORD_BY_TOKEN_URL, data);
        Result result = JsonUtils.parse(resultStr, Result.class);
        if (Objects.equals(result.getCode(), Global_Datas.SUCCESS)) {
            return (Map<String, String>) result.getData();
        }
        return null;
    }

    /**
     * 修改用户密码
     * @param newPassWord 新密码
     * @return
     */
    public static boolean updateUserPassWordByToken(String newPassWord) {
        HashMap<String, String> data = new HashMap<>();
        data.put("token", Global_Datas.token);
        data.put("newPassWord", newPassWord);
        String resultStr = HttpClientUtil.doPost(Global_Datas.UPDATE_USER_PASSWORD_URL, data);
        Result result = JsonUtils.parse(resultStr, Result.class);
        if (Objects.equals(result.getCode(), Global_Datas.SUCCESS)) {
            return true;
        }
        return false;
    }

    /**
     * 注销账号
     * @param passWord 用户密码
     * @param encodePassWord 加密后的用户密码
     * @return
     */
    public static boolean removeAccount(String passWord, String encodePassWord) {
        HashMap<String, String> data = new HashMap<>();
        data.put("token", Global_Datas.token);
        data.put("userName", Global_Datas.userName);
        data.put("passWord", passWord);
        data.put("encodePassWord", encodePassWord);
        String resultStr = HttpClientUtil.doPost(Global_Datas.REMOVE_ACCOUNT_URL, data);
        Result result = JsonUtils.parse(resultStr, Result.class);
        if (Objects.equals(result.getCode(), Global_Datas.SUCCESS)) {
            return true;
        }
        return false;
    }

    /**
     * 上传头像
     * @param avatarBase64 头像图片的Base64编码
     * @return
     */
    public static boolean updateAvatar(String avatarBase64) {
        HashMap<String, String> data = new HashMap<>();
        data.put("token", Global_Datas.token);
        data.put("avatarBase64", avatarBase64);
        String resultStr = HttpClientUtil.doPost(Global_Datas.UPDATE_AVATAR_URL, data);
        Result result = JsonUtils.parse(resultStr, Result.class);
        if (Objects.equals(result.getCode(), Global_Datas.SUCCESS)) {
            return true;
        }
        return false;
    }
}
