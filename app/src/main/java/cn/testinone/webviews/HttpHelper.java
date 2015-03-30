package cn.testinone.webviews;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LingChen on 15/3/30.
 */
public class HttpHelper {

    public static void sendHttpPost(final String address, final String params, final HttpCallbackListener callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
    URL url = new URL(address);
                    conn =(HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(params);

                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null)
                        response.append(line);
                    if (callback != null)
                        callback.onFinish(response.toString());
                    
                } catch (Exception ex) {
                    if (callback != null)
                        callback.onError(ex);
                } finally {
                    if (conn != null)
                        conn.disconnect();
                }
            }
        }).start();
    }

    public static void sendHttpGet(final String address, final HttpCallbackListener callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(address);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    if (callback != null)
                        callback.onFinish(response.toString());

                } catch (Exception ex) {
                    if (callback != null)
                        callback.onError(ex);
                } finally {
                    if (conn != null)
                        conn.disconnect();
                    ;
                }
            }
        }).start();

    }
}
