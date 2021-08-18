package Day42;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName: InputStreamReaderTest
 * @Description: 处理流之二：转换流的使用
 * @Author: TianXing.Xue
 * @Date: 2021/8/7 18:21
 *  1.转换流：属于字符流
 *    InputStreamReader：将一个字节的输入流转换为字符的输入流
 *    OutputStreamWriter：将一个字符的输出流转换为字节的输出流
 *
 *  2.作用：提供字节流与字符流之间的转换
 *
 *  3.解码：字节、字节数组 ---> 字符数组、字符串
 *    编码：字符数组、字符串 ---> 字节、字节数组
 *
 *  4.字符集
 *  ASCII:美国标准信息交换码，用一个字节的7位可以表示
 *  GBK：中国的中文编码升级
 *  Unicode：国际
 *  UTF-8：变长的编码方式
 *
 **/

public class InputStreamReaderTest {

    @Test
    public void test1() {

        InputStreamReader isr = null;
        try {
            FileInputStream fis = new FileInputStream("hello.txt");

//        InputStreamReader isr= new InputStreamReader(fis); //使用系统默认的字符集
            //参数2指明了字符集，具体使用哪个字符集，取决于文件hello.txt保存时使用的字符集
            isr = new InputStreamReader(fis, "UTF-8");

            char[] cbuf = new char[1024];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    综合使用InputStreamReader 和 OutputStreamWriter

     */

    @Test
    public void test2() {
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;

        try {
            //1.造文件、造流
            File file1 = new File("hello.txt");
            File file2 = new File("hello_gbk.txt");

            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);

            isr = new InputStreamReader(fis,"UTF-8");
            osw = new OutputStreamWriter(fos,"gbk");

            //2.读写过程
            char[] cbuf = new char[20];
            int len;
            while ((len = isr.read(cbuf))!=-1){
                osw.write(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(osw !=null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(isr!=null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
