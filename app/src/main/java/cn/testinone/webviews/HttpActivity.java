package cn.testinone.webviews;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.testinone.R;

public class HttpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        tvResponse = (TextView) findViewById(R.id.tvResponse);
    }

    private TextView tvResponse;

    static int FLAG_SHOW_RESPONSE = 1001;
    static int FLAG_BUTTON_ENABLE = 1002;

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (FLAG_SHOW_RESPONSE == msg.what) {
                tvResponse.setText((String) msg.obj);
            } else if (FLAG_BUTTON_ENABLE == msg.what) {

            }
        }
    };

    public void httpClientPost(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpResponse response = null;
                try {
                    HttpPost post = new HttpPost("http://www.jandan.net");
                    List<BasicNameValuePair> params = new ArrayList();
                    params.add(new BasicNameValuePair("q", "%E6%B1%BD%E8%BD%A6&s=18250727425702039732"));
                    params.add(new BasicNameValuePair("source", "jandan.net"));
                    HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
                    post.setEntity(entity);
                    response = client.execute(post);
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity httpEntity = response.getEntity();
                        String ret = EntityUtils.toString(httpEntity, "utf-8");
                        Message msg = new Message();
                        msg.what = FLAG_SHOW_RESPONSE;
                        msg.obj = ret;
                        uiHandler.sendMessage(msg);

                    }
                } catch (Exception ex) {
                    Toast.makeText(HttpActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }).start();

    }

    public void httpClientGet(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpResponse response = null;
                try {
                    HttpGet get = new HttpGet("http://www.jandan.net/");
                    response = client.execute(get);
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity entity = response.getEntity();
                        String ret = EntityUtils.toString(entity, "utf-8");
                        Message msg = new Message();
                        msg.what = FLAG_SHOW_RESPONSE;
                        msg.obj = ret;
                        uiHandler.sendMessage(msg);
                    }
                } catch (Exception ex) {
                    Toast.makeText(HttpActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }).start();

    }

    public void httpPost(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL("http://s.jandan.net/cse/search");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes("q=%E6%B1%BD%E8%BD%A6&s=18250727425702039732&source=jandan.net");
                    out.flush();
                    out.close();

                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);

                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Message msg = new Message();
                    msg.what = FLAG_SHOW_RESPONSE;
                    msg.obj = response.toString();
                    uiHandler.sendMessage(msg);

                } catch (Exception ex) {
                } finally {
                    if (conn != null)
                        conn.disconnect();
                }
            }
        }).start();
    }

    public void httpGet(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL("http://faxian.smzdm.com/");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);

                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Message msg = new Message();
                    msg.what = FLAG_SHOW_RESPONSE;
                    msg.obj = response.toString();
                    uiHandler.sendMessage(msg);
                } catch (Exception ex) {
                    uiHandler.sendEmptyMessage(0);
                } finally {
                    if (conn != null)
                        conn.disconnect();
                    uiHandler.sendEmptyMessage(FLAG_BUTTON_ENABLE);
                }

            }
        }).start();
    }
}
