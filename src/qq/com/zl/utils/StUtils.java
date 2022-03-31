package qq.com.zl.utils;

import java.io.*;

/**
 * @author zhaolun
 * @version 1.0
 * @item xxx
 */
public class StUtils {
    /**
     * 字节输入流读取内容
     *
     * @param in 输入流
     */
    public static void inputStream(InputStream in) throws IOException {
        int end = 0;
        byte[] bytes = new byte[1024];
        while ((end = in.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, end));
        }

    }

    /**
     * 拷贝文件
     *
     * @param in  文件的读取流
     * @param out 文件存储流
     */
    public static void copyFile(InputStream in, OutputStream out) throws IOException {
        int end = 0;
        byte[] bytes = new byte[1024];
        while ((end = in.read(bytes)) != -1) {
            out.write(bytes);
        }


    }

    /**
     * 字节流写入内容
     * @param out 输出流
     * @param content 写入的内容
     */

    public static void outStream(OutputStream out , String content) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
        bufferedOutputStream.write(content.getBytes());

    }

    /**
     * 字符流读取数据
     *
     * @param in 字符流以及字符流的子类
     * @throws IOException 异常
     */
    public static String readerStream(InputStream in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        line = bufferedReader.readLine();
        return line;

    }

    /**
     * 字符流写入内容
     * @param in 字符输入流
     * @param content 你要写入的内容
     */

    public static void writer(Writer in,String content) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(in);
        bufferedWriter.write(content);

        bufferedWriter.newLine();
        bufferedWriter.flush();


    }

}
