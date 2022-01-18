package com.jian;

import com.jian.service.DealData_Service;
import com.jian.utils.HttpClientUtil;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.Base64;

/**
 * @author Qi
 * @Date 2021/9/28 8:11 下午
 */


public class testing {

//    @Override
//    public void start(Stage primaryStage) {
//
//        BorderPane root = new BorderPane();
//
////        String Image = HttpClientUtil.doPost("http://127.0.0.1:5000/index");
//        String img_data = "b'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'";
//        GenerateImage(img_data);
//        javafx.scene.image.Image image = new Image("file:/Users/qi/Downloads/images-javafx.jpg");
//
//        root.setCenter(new ImageView(image));
//        root.setLeft(new Label("III"));
//        Scene scene = new Scene(root, 500, 800);
//
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    public static void main(String[] args) {
        String result = DealData_Service.dealImgByDir("/Users/qi/IdeaProjects/JianMask_Server/AppDatas/JianTest/1633436676.jpg");
        System.out.println(result);
        //        String img_data = "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";
//        GenerateImage(img_data);
//        String Image = HttpClientUtil.doPost("http://127.0.0.1:9000/Mask?mode=img&imgdir=L1VzZXJzL3FpL0lkZWFQcm9qZWN0cy9KaWFuTWFza19TZXJ2ZXIvQXBwRGF0YXMvSmlhblRlc3QvMTYzMzQzNjY3Ni5qcGc=");
//        System.out.println(Image);
//        String fname = "/Users/qi/Downloads/images-javafx.jpg";
//        int pos = fname.lastIndexOf('.');
//        if (pos > -1)
//            fname.substring(0, pos);
////        System.out.println(fname.substring(0, pos));
//        System.out.println(fname.substring(++pos));
//        System.out.println(Base64Util.ImageToString("/Users/qi/Downloads/images-2.jpeg"));
    }

    // 对字节数组字符串进行Base64解码并生成图片
    public static boolean GenerateImage(String imgStr) {
        if (imgStr == null || imgStr.equals("")) {
            // 图像数据为空
            return false;
        }
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            String imgFilePath = "/Users/qi/Downloads/images-javafx.png";// 新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public javafx.scene.image.Image createImage() {

        String Image = HttpClientUtil.doPost("http://127.0.0.1:5000/index");
//        Image = Base64Util.encode(Image);
//        byte[] Image_data = Base64.getDecoder().decode(Image);
        System.out.println(Image.getClass());
        System.out.println(Image);

//        GenerateImage(Image);

        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] b = decoder.decode(Image);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
//            java.awt.Image image = Toolkit.getDefaultToolkit().createImage(b);

            InputStream input = new ByteArrayInputStream(b);
            InputStream in = Base64.getDecoder().wrap(input);
            javafx.scene.image.Image image3 = new javafx.scene.image.Image(in);

            return image3;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

class ttt extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        BorderPane root = new BorderPane();
//        Scene scene = new Scene(root, 300, 500);
//        stage.setScene(scene);

        stage.show();


//            ImageView imageView = new ImageView(image3);
//            root.setCenter(imageView);

//        String imgFilePath = "/Users/qi/Downloads/images-10011.jpeg";// 新生成的图片
//        OutputStream out = new FileOutputStream(imgFilePath);
//        out.write(Image_data);
//        out.flush();
//        out.close();


//        Process proc;
//        try {
////            System.out.println("GO");
////            Runtime.getRuntime().exec("cd FaceMaskDetection-master");
////            proc = Runtime.getRuntime().exec("/Users/qi/Downloads/MaskPython.sh");
//            proc = Runtime.getRuntime().exec("python ./FaceMaskDetection-master/pytorch_infer.py --img-mode 0 --video-path 0");
//            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            String line = null;
//            String pid = null;
//            pid = in.readLine();
//            System.out.println(pid);
////            Runtime.getRuntime().exec("kill " + pid);
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();
//            proc.waitFor();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String exe = "python";
////        String command = "FaceMaskDetection-master/pytorch_infer.py";
//        String command = "testing.py";
////        String num1 = "--img-mode 0";
////        String num2 = "--video-path 0";
////        String[] cmdArr = new String[] {exe, command,num1,num2};
//        String[] cmdArr = new String[] {exe, command};
//        Process process = Runtime.getRuntime().exec(cmdArr);
//        InputStream is = process.getInputStream();
//        BufferedReader dis = new BufferedReader(new InputStreamReader(is));
//        String str = dis.readLine();
//        process.waitFor();
//        System.out.println(str);

//        System.out.println();
//        Runtime.getRuntime().exec("ls");
//        String [] cmd = {"cd ./FaceMaskDetection-master"};
//        Runtime.getRuntime().exec(cmd);
//        String [] cmd2 = {"python pytorch_infer.py --img-mode 0 --video-path 0"};
//        Runtime.getRuntime().exec(cmd2);
    }
}

