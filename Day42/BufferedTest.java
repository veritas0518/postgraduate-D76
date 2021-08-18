package Day42;

import jdk.jshell.spi.ExecutionEnv;
import org.junit.Test;

import java.io.*;

/**
 * @ClassName: BufferedTest
 * @Description: 处理流之一：缓冲流的使用
 * @Author: TianXing.Xue
 * @Date: 2021/8/7 15:33
 *                             InputStreamReader:父类是Reader  (看后缀！看后缀！看后缀！)
 *                              异常：XxxException  (异常)
 *                                   XxxError      (错误)
 *
 *  1.缓冲流
 *  BufferedInputStream
 *  BufferedOutputStream
 *  BufferedReader
 *  BufferWriter
 *
 *  2.作用：提高流的读取、写入速度
 *     提高读写速度的原因：内部提供了一个缓冲区
 *
 *  3.处理流，就是“套接”在已有的流的基础上。
 *
 *
 **/

public class BufferedTest {

    /*
    实现非文本文件的复制

     */

    @Test
    public void BufferedStreamTest() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //1.造文件
            File srcFile = new File("友情与爱情.jpg");
            File destFile = new File("友情与爱情3.jpg");

            //2.造流
            //2.1 造节点流
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.复制的细节
            byte[] buffer = new byte[64];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);

//                bos.flush(); //刷新缓冲区

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源关闭
            //要求：先关闭外层的流，在关闭内层的流
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的关闭，我们可以省略
//        fos.close();
//        fis.close();
    }

    //实现文件复制的方法
    public void copyFileWithBuffered(String srcPath,String destPath){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //1.造文件
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            //2.造流
            //2.1 造节点流
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.复制的细节
            byte[] buffer = new byte[64];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源关闭
            //要求：先关闭外层的流，在关闭内层的流
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testCopyFileWithBuffered(){
        long start = System.currentTimeMillis();
        String srcPath = "D:\\JavaCode\\友情与爱情.jpg";
        String destPath = "D:\\JavaCode\\友情与爱情03.jpg";

        copyFileWithBuffered(srcPath,destPath);
        long end = System.currentTimeMillis();
        System.out.println("复制操作花费的时间为"+(end-start));
    }

    /*
    使用BufferedReader 和 BufferedWriter实现文本文件的复制

     */

    @Test
    public void testBufferedReaderBufferedWriter() {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            //创建文件和相应的流
            br = new BufferedReader(new FileReader(new File("hello.txt")));
            bw = new BufferedWriter(new FileWriter(new File("hello01.txt")));

            //读写操作
            //方式一：使用char[]数组
//            char[] cbuf = new char[1024];
//            int len;
//            while ((len  = br.read(cbuf))!=-1){
//                bw.write(cbuf,0,len);
//    //            bw.flush();
//            }

            //方式二：
            String data;
            while ((data = br.readLine()) != null) {
                //方法一
//                bw.write(data + "\n");  //data中不包含换行符
                //方法二：
                bw.write(data);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

