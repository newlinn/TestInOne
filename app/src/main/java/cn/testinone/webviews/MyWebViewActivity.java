package cn.testinone.webviews;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.testinone.R;

public class MyWebViewActivity extends Activity {

    ProgressBar progressBar;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);
        progressBar = (ProgressBar) findViewById(R.id.webViewProgress);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (100 == newProgress) {
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == progressBar.getVisibility())
                        progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                tvTitle.setText(title);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //todo 路径不对
                //view.loadUrl("file:///main/java/cn/testinone/webviews/error.html");
                Toast.makeText(MyWebViewActivity.this, description, Toast.LENGTH_SHORT).show();
            }
        });

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(final String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

                final String downUrl = url;
                final String fileName = downUrl.substring(downUrl.lastIndexOf("/") + 1, downUrl.length());
                if (downUrl.toLowerCase().endsWith(".apk")) {
                    AlertDialog alert = new AlertDialog.Builder(MyWebViewActivity.this)
                            .setTitle("下载" + fileName)
                            .setMessage("使用默认下载？")
                            .setIcon(R.drawable.ic_launcher)
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                HttpURLConnection conn = (HttpURLConnection) (new URL(downUrl).openConnection());
                                                conn.setDoInput(true);
                                                conn.setDoOutput(true);

                                                File downDir = Environment.getDownloadCacheDirectory();
                                                File downFile = new File(downDir, fileName);

                                                InputStream inputStream = conn.getInputStream();
                                                OutputStream outputStream = new FileOutputStream(downFile);
                                                byte[] bytes = new byte[6 * 1024];
                                                int len;
                                                while ((len = inputStream.read(bytes)) > -1) {
                                                    outputStream.write(bytes, 0, len);
                                                }

                                                if (inputStream != null) inputStream.close();
                                                if (outputStream != null) outputStream.close();
                                            } catch (Exception ex) {
                                            }

                                        }
                                    }).start();
                                }
                            })
                            .setNegativeButton("使用系统下载", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(downUrl));
                                    startActivity(intent);
                                }
                            }).show();
                }
            }

        });
    }

    WebView webView;

    @JavascriptInterface
    public void loadUrl(View view) {
        //final String url = "http://shouji.baidu.com";
        String url = "http://192.168.1.100/index.html";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebHost(MyWebViewActivity.this), "WebHost");
        webView.loadUrl(url);
    }

    public class WebHost {
        private Context context;

        public WebHost(Context context) {
            this.context = context;
        }

        public void JSCall(){
            Toast.makeText(context, "call from JS", Toast.LENGTH_SHORT).show();
        }
    }
}
