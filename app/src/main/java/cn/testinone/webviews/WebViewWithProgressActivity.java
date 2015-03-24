package cn.testinone.webviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import cn.testinone.R;

public class WebViewWithProgressActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view_progress);
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.webViewProgress);
        webView = (WebView)findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient(){
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
        });
    }

    WebView webView;

    public void loadUrl(View view){
        final String url = "http://www.cnbeta.com/articles/379819.htm";
        webView.loadUrl(url);
    }
}
