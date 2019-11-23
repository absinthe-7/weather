package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    /**
     * 定义成员方法
     */

    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        new Thread(new Runnable() {                                                 /**创建一个线程*/
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);                                     /**创建一个新的网址链接，address为链接地址*/
                    connection = (HttpURLConnection) url.openConnection();          /**url对象用openconnection()打开连接；获得URLConnection类对象，再用URLConnection类对象的connect（）方法进行连接*/
                    connection.setRequestMethod("GET");                             /**设置请求方法为Get，Get请求不用带包体*/
                    connection.setConnectTimeout(8000);                             /** 8秒 连接主机的超时时间（单位：毫秒）*/
                    connection.setReadTimeout(8000);                                /** 8秒 从主机读取数据的超时时间（单位：毫秒）*/
                    InputStream in = connection.getInputStream();                   /**从网络下载资源时来获取InputStream实列*/
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));/**构造一个BufferedReader，里面存放在控制台输入的字节转换后成的字符*/
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);                                      /**读取line的内容*/
                    }
                    if (listener != null) {

                        listener.onFinish(response.toString());                     /**关闭页面*/
                    }
                } catch (Exception e) {
                    if (listener != null) {

                        listener.onError(e);                                        /**在装载文档的过程中如果发生了错误，就会调用该事件句柄*/
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();                                  /**执行了输入流的关闭操作*/
                    }
                }
            }
        }).start();
    }
}