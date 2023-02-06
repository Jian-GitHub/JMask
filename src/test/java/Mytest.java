import com.jian.threads.RTM_Thread;
import org.bytedeco.javacv.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jian Qi
 * @Date 2022/1/17 1:37 上午
 * @Description
 * @Version
 */
public class Mytest {
    public static void main(String[] args) throws InterruptedException, IOException {
/*
一个轮班可能需要多名同学参加
一名同学可能会参加多次轮班
一位同学的多次轮班之间不能有时间间隔，也不能有重叠

1 1 1 5
1 3 2 6 8
2 3 3 5 7
3 3 4 9
4 4 4
4 6 10
4 7 9
5 6 11
6 6 12
 */
        int n = 9;

        List<Integer>[] input = new List[n];
        input[0] = new ArrayList<Integer>();
        input[0].add(1);
        input[0].add(1);
        input[0].add(1);
        input[0].add(5);
        input[1] = new ArrayList<Integer>();
        input[1].add(1);
        input[1].add(3);
        input[1].add(2);
        input[1].add(6);
        input[1].add(8);
        input[2] = new ArrayList<Integer>();//2 3 3 5 7
        input[2].add(2);
        input[2].add(3);
        input[2].add(3);
        input[2].add(5);
        input[2].add(7);
        input[3] = new ArrayList<Integer>();//3 3 4 9
        input[3].add(3);
        input[3].add(3);
        input[3].add(4);
        input[3].add(9);
        input[4] = new ArrayList<Integer>();//4 4 4
        input[4].add(4);
        input[4].add(4);
        input[4].add(4);
        input[5] = new ArrayList<Integer>();//4 6 10
        input[5].add(4);
        input[5].add(6);
        input[5].add(10);
        input[6] = new ArrayList<Integer>();//4 7 9
        input[6].add(4);
        input[6].add(7);
        input[6].add(9);
        input[7] = new ArrayList<Integer>();//5 6 11
        input[7].add(5);
        input[7].add(6);
        input[7].add(11);
        input[8] = new ArrayList<Integer>();//6 6 12
        input[8].add(6);
        input[8].add(6);
        input[8].add(12);
        int num = 0;
        int min = 0;
        int value = 4;
//        boolean first = false, second = false, third = false, fourh = false;
        boolean[] weeks = {false,false,false};
        boolean result = true;
        List<Integer> rlt = new ArrayList<>();
        // 1 ~ 4
        for (int i = 0 ; i < n ; i ++) {
            for(int j = 0 ; j < n ; j++){
                int start = input[i].get(0);
                int end = input[i].get(1);
                int mid = end - start + 1;
                value -= mid;
                for(int ii = start-1 ; ii < mid ; ii++){
                    weeks[ii] = true;
                }
                for (boolean week : weeks) {
                    if (week) {
                        continue;
                    } else {
                        result = week;
                    }
                }
                if (result) {
                    break;
                }
                rlt.add(i);
//                switch (start) {
//                    case 1: first = true;break;
//                    case 2: second = true;break;
//                    case 3: third = true;break;
//                    case 4: fourh = true;break;
//                }
//                switch (end) {
//                    case 1: first = true;break;
//                    case 2: second = true;break;
//                    case 3: third = true;break;
//                    case 4: fourh = true;break;
//                }
            }
            if (result) {
                break;
            }
        }

//        RTM_Thread r = new RTM_Thread();

//        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);//0表示本机摄像头 当然这里也可以换成网络摄像头地址
//        grabber.start();   //开始获取摄像头数据
//        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
//        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭时程序运行结束
//        canvas.setAlwaysOnTop(true);
//        while (true) {
//            if (!canvas.isDisplayable()) {//窗口是否关闭
//                System.out.println("已关闭");
//                grabber.stop();//停止抓取
//                System.exit(2);//退出
//            }
//            canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame         frame=grabber.grab(); frame是一帧视频图像
//            Thread.sleep(50);//50毫秒刷新一次图像
//        }

//        Javacv.go();
    }
}

class Javacv {
//    public static void go() throws IOException, InterruptedException {
//        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);//0表示本机摄像头  当然这里也可以换成网络摄像头地址
//        grabber.start();   //开始获取摄像头数据
////        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
////        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭时程序运行结束
////        canvas.setAlwaysOnTop(true);
//        int i = 0;
//        while (true) {
////            if (!canvas.isDisplayable()) {//窗口是否关闭
////                System.out.println("已关闭");
////                grabber.stop();//停止抓取
////                System.exit(2);//退出
////            }
////            canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame表示一帧视频图像
//            //调用doExecuteFrame()方法，将截取的图片保存在本地
////            doExecuteFrame(grabber.grabFrame(), "/Users/qi/Downloads/test/" + i + ".jpg");
//
//            Java2DFrameConverter converter = new Java2DFrameConverter();
//            BufferedImage bi = converter.getBufferedImage(grabber.grabFrame());
//
//            byte[] base64 = null;
//            Integer width = bi.getWidth();
//            Integer height = bi.getHeight();
//            System.out.println("宽：" + width + " 高:"+height);
//
//            //输出流
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            ImageIO.write(bi, "png", stream);
//            base64 = Base64.encode(stream.toByteArray());
//            System.out.println(new String(base64));
//            stream.flush();
//            stream.close();
//
//
//            Thread.sleep(17);//17毫秒刷新一次图像
//            i++;
//
////            Frame grabframe =grabber.grab();
////            //抓取一帧视频并将其转换为图像，至于用这个图像用来做什么？加水印，人脸识别等等自行添加
////            IplImage grabbedImage =null;
////            if(grabframe != null){
////                System.out.println("获得第一帧");
////                grabbedImage = converter.convert(grabframe);
////                System.out.println(grabbedImage);
////                //保存图片到本地
////                opencv_imgcodecs.cvSaveImage("F:\\hello12.jpg",grabbedImage);
////                // 将IplImage转为Frame
////                Frame convertFrame2 = converter.convert(grabbedImage);
////                //将图片指针转为二进制byte[]  start
////                Java2DFrameConverter java2dFrameConverter = new Java2DFrameConverter();
////                BufferedImage bufferedImage= java2dFrameConverter.convert(convertFrame2);
////                ByteArrayOutputStream out = new ByteArrayOutputStream();
////                ImageIO.write(bufferedImage, "jpg", out);
////                byte[] readFile =  out.toByteArray();
////                //将图片指针转为二进制byte[]  end
//////            byte[] readFile = FileUtils.readFileToByteArray(new File("F:\\hello112.jpg"));  //读取本地图片
////                //发送图片到图片服务器
////                Client client = new Client();
////                WebResource resource = client.resource("http://localhost:8088/imgWeb/upload/hello112.jpg");
////                resource.put(String.class, readFile);
////                //图片资源释放释放资源
////                grabbedImage.release();
//        }
//    }

    /**
     * @param f              表示帧
     * @param targetFileName 存储路径
     */
    public static void doExecuteFrame(Frame f, String targetFileName) {
        if (null == f || null == f.image) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi = converter.getBufferedImage(f);
        File output = new File(targetFileName);
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
