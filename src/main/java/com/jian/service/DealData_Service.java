package com.jian.service;

import com.jian.globalDatas.Global_Datas;
import com.jian.utils.*;

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
     * @return String 返回保存图片的Base64编码，失败返回空字符串 -> ""
     */
    public static String dealImgByDir(String imgDir) {
        if (imgDir == null || "".equals(imgDir)) {
            return "";
        }

        String imgData = Base64Util.ImageToBase64String(imgDir);
        int pos = imgDir.lastIndexOf('.');
        String imgType = imgDir.substring(++pos);
        try {
            HashMap<String, String> hashMap = new HashMap<>();
//            hashMap.put("userName", Base64Util.encode(Global_Datas.userName));
            hashMap.put("token", Global_Datas.token);
            hashMap.put("imgType", imgType);
            hashMap.put("imgData", imgData);
            //向服务器传送用户名，图片类型，图片数据，接收处理后的图片数据Base64编码
            String result_imgData = HttpClientUtil.doPost(Global_Datas.DEALIMG_URL, hashMap);
            if ("".equals(result_imgData) || result_imgData == null) {
                return "";
            }
            return result_imgData;

//            //本地创建缓存目录，存储处理后的图片
//            File directory = new File("");
//            String saveImgDir = directory.getAbsolutePath() + "/.AppData/" + Base64Util.decode(Global_Datas.userName);
//            File file = new File(saveImgDir);
//            if (!file.exists()) {
//                if(!file.mkdirs()){
//                    return "";
//                }
//            }
//
//            String imgName = String.valueOf(System.currentTimeMillis() / 1000);
//            saveImgDir += "/" + imgName + "." + imgType;
//
//            Base64Util.StringToSaveImage(result_imgData, saveImgDir);
//            return saveImgDir;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param imgData 上传图片的Base64编码
     * @return String 返回保存图片的Base64编码，失败返回空字符串 -> ""
     */
    public static String dealImgByData(String imgData) {
        if (imgData == null || "".equals(imgData)) {
            return "";
        }
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userName", Base64Util.encode(Global_Datas.userName));
            hashMap.put("imgData", imgData);
            //向服务器传送用户名，图片类型，图片数据，接收处理后的图片数据Base64编码
            String result_imgData = HttpClientUtil.doPost(Global_Datas.DEALRTM_URL, hashMap);
            if ("".equals(result_imgData) || result_imgData == null) {
                return "";
            }
            return result_imgData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}