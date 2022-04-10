import com.jian.threads.RTM_Thread;
import org.bytedeco.javacv.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author Jian Qi
 * @Date 2022/1/17 1:37 上午
 * @Description
 * @Version
 */
public class Mytest {
    public static void main(String[] args) throws InterruptedException, IOException {
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
