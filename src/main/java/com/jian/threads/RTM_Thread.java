package com.jian.threads;

import com.jian.globalDatas.Global_Datas;
import com.jian.service.DealData_Service;
import com.jian.utils.HttpClientUtil;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.SneakyThrows;
import org.apache.shiro.codec.Base64;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class RTM_Thread extends Thread {
    private ImageView resultImgView;
    private boolean run = true;
    private boolean ready = true;

    public RTM_Thread(ImageView resultImgView) {
        this.resultImgView = resultImgView;
    }

    @SneakyThrows
    @Override
    public void run() {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);//0表示本机摄像头  当然这里也可以换成网络摄像头地址
        grabber.start();   //开始获取摄像头数据
        String resultImageBase64;
        String imgData;
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi;
        HashMap<String, String> hashMap = new HashMap<>();
        while (run) {
//            System.out.println("RUN");
//            if (ready) {
//                ready = false;
            bi = converter.getBufferedImage(grabber.grabFrame());

//            Integer width = bi.getWidth();
//            Integer height = bi.getHeight();
//            System.out.println("宽：" + width + " 高:" + height);

            //输出流
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", stream);
            imgData = new String(Base64.encode(stream.toByteArray()), "utf-8");
//            System.out.println(imgData);
            stream.flush();
            stream.close();

            //上传服务器进行处理
//            String resultImageBase64 = DealData_Service.dealImgByData(imgData);
            //      将图片Base64数据传至Python内处理，接收处理后的数据。
            hashMap.put("imgData", imgData);
            //向Python服务器传送用户名，图片类型，图片数据，接收处理后的图片数据Base64编码
            resultImageBase64 = HttpClientUtil.doPost(Global_Datas.dealPythonRTM_url, hashMap);
            if ("".equals(resultImageBase64) || resultImageBase64 == null) {
                continue;
            }

//                System.out.println(resultImageBase64);

            java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
            // Base64解码
            byte[] b = decoder.decode(resultImageBase64);
//                byte[] b = resultImageBase64.getBytes();
//            for (int i = 0; i < b.length; ++i) {
//                if (b[i] < 0) {// 调整异常数据
//                    b[i] += 256;
//                }
//            }
            InputStream in = new ByteArrayInputStream(b);
            Image image = new Image(in);
            in.close();
            Platform.runLater(() -> {
                resultImgView.setImage(image);
                resultImgView.setFitHeight(350);
                resultImgView.setFitWidth(400);
//                    ready = true;
            });
//                Thread.sleep(50);//17毫秒刷新一次图像
//            }
        }
        grabber.stop();
    }

    public void stopRTM() {
        this.run = false;
//        System.out.println("STOP");
    }
}