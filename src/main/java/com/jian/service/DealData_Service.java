package com.jian.service;

import com.jian.entity.User;
import com.jian.globalDatas.Global_Datas;
import com.jian.utils.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Jian Qi
 * @Date 2021/10/5 9:01 下午
 * @Description 图像数据处理
 * @Version 1
 */
public class DealData_Service {

    /**
     * @param imgDir 上传图片的地址
     * @return String 返回保存图片的地址，失败返回空字符串
     */
    public static String dealImg(String imgDir) {
        if (imgDir == null || "".equals(imgDir)) {
            return "";
        }
//        String url = "http://localhost:8081/Yi/Login/getPassword";

        String imgData = Base64Util.ImageToBase64String(imgDir);
        int pos = imgDir.lastIndexOf('.');
        String imgType = imgDir.substring(++pos);
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userName", Base64Util.encode(Global_Datas.userName));
            hashMap.put("imgType", imgType);
            hashMap.put("imgData", Base64Util.encode(imgData));
            String result_imgData = HttpClientUtil.doPost(Global_Datas.dealImg_url, hashMap);
            if ("".equals(result_imgData)) {
                return "";
            }

            File directory = new File("");
            String saveImgDir = directory.getAbsolutePath() + "/.AppData/" + Base64Util.decode(Global_Datas.userName);
//            String result_imgData = Base64Util.decode(r_imgData);
            File file = new File(saveImgDir);
            if (!file.exists()) {
                file.mkdirs();
            }

            String imgName = String.valueOf(System.currentTimeMillis() / 1000);
            saveImgDir += "/" + imgName + "." + imgType;

            Base64Util.StringToSaveImage(result_imgData, saveImgDir);
            return saveImgDir;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
