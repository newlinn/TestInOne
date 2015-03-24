package cn.testinone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;

import cn.testinone.ntfy.NotificationActivity;
import cn.testinone.webviews.WebViewWithProgressActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(Process.myPid());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("确定退出系统吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        } else
            return super.onKeyDown(keyCode, event);

        return true;
    }

    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.btnNotification:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            default:
                break;
        }
    }

    public void webView(View view){
        startActivity(new Intent(this, WebViewWithProgressActivity.class));
    }
}
