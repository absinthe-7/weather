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
                    connection.setRequestMethod("GET");                             /***/
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {

                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {

                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}