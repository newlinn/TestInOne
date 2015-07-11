package cn.testinone;

import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.InputMismatchException;

import cn.testinone.drawer.DrawerActivity;
import cn.testinone.drawer.DrawerTabsActivity;
import cn.testinone.listView.FruitActivity;
import cn.testinone.drawer.RecycleFruitActivity;
import cn.testinone.drawer.DrawerActionBarActivity;
import cn.testinone.ntfy.NotificationActivity;
import cn.testinone.webviews.HttpActivity;
import cn.testinone.webviews.MyWebViewActivity;
import cn.testinone.webviews.WebDataActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Process.killProcess(Process.myPid());
    }

    long waitTime = 2000;
    long touchTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
            long curTime = System.currentTimeMillis();
            if ((curTime - touchTime) >= waitTime) {
                Toast.makeText(MainActivity.this, "再按一次退出程序！", Toast.LENGTH_SHORT).show();
                touchTime = curTime;
            } else
                finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.btnNotification:
                startActivity(new Intent(this, NotificationActivity.class));
                break;

            default:
                break;
        }
    }

    public void webView(View view) {
        startActivity(new Intent(this, MyWebViewActivity.class));
    }

    public void fruitLstView(View view) {
        startActivity(new Intent(this, FruitActivity.class));
    }

    public void httpRequest(View view) {
        startActivity(new Intent(MainActivity.this, HttpActivity.class));
    }

    public void parseWebData(View view) {
        startActivity(new Intent(MainActivity.this, WebDataActivity.class));
    }

    public void btnDrawerLayout(View view) {
        startActivity(new Intent(MainActivity.this, DrawerActivity.class));
    }

    public void btnRecyclerView(View view) {
        startActivity(new Intent(MainActivity.this, RecycleFruitActivity.class));
    }

    public void btnDrawerLayoutActionBar(View view){
        startActivity(new Intent(MainActivity.this, DrawerActionBarActivity.class));
    }

    public void btnDrawerTabsActivity(View view){
        startActivity(new Intent(MainActivity.this, DrawerTabsActivity.class));
    }

    public void showDlgProgressbar(View view){
        Toast.makeText(MainActivity.this, "showDlgProgressbar", Toast.LENGTH_SHORT).show();

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain(uiHandler);
                msg.what = 1;
                uiHandler.sendMessage(msg);
                try {
                    Thread.sleep(5000);
                } catch (Exception iex) {
                    iex.printStackTrace();
                }
                msg = Message.obtain(uiHandler);
                msg.what = 0;
                uiHandler.sendMessage(msg);

            }
        });
        thread.start();
    }

    Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    dlgLoading.dismiss();
                    break;
                case 1:
                    dlgLoading.show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
