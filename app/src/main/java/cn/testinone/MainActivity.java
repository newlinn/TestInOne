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
import android.widget.Toast;

import cn.testinone.listView.FruitActivity;
import cn.testinone.listView.RecycleFruitActivity;
import cn.testinone.menu.DrawerLayoutActivity;
import cn.testinone.ntfy.NotificationActivity;
import cn.testinone.webviews.HttpActivity;
import cn.testinone.webviews.MyWebViewActivity;
import cn.testinone.webviews.WebDataActivity;


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
        startActivity(new Intent(MainActivity.this, DrawerLayoutActivity.class));
    }
}
