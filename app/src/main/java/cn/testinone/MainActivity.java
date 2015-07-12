package cn.testinone;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.testinone.drawer.DrawerActivity;
import cn.testinone.drawer.DrawerTabsActivity;
import cn.testinone.listView.FruitActivity;
import cn.testinone.drawer.RecycleFruitActivity;
import cn.testinone.drawer.DrawerActionBarActivity;
import cn.testinone.ntfy.NotificationActivity;
import cn.testinone.webviews.HttpActivity;
import cn.testinone.webviews.MyWebViewActivity;
import cn.testinone.webviews.WebDataActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        dlgLoading = createLoadingDialog(MainActivity.this);
        initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:

                return true;

            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Process.killProcess(Process.myPid());
    }

    Toolbar toolbar;
    ActionBarDrawerToggle btnToggle;
    DrawerLayout drawerLayout;

    private void findViews()
    {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void initToolbar() {

        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.app_name, R.string.hello_world);
        btnToggle.syncState();
        drawerLayout.setDrawerListener(btnToggle);

        /* 菜单的监听可以在toolbar里设置，也可以像ActionBar那样，通过Activity的onOptionsItemSelected回调方法来处理
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
        }
        */
    }

    private Dialog dlgLoading;
    private Dialog createLoadingDialog(Context context) {

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);

        LinearLayout llProgress = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.progress_bar, null).findViewById(R.id.llProgress);
        loadingDialog.setContentView(llProgress,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        loadingDialog.setCancelable(false);
        return loadingDialog;
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

    public void menuActivity(View view){
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case showDlgLoading:
                    toolbar.setTitle("Loading...");
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    dlgLoading.show();
                    break;
                case dismissDlgLoading:
                    toolbar.setTitle(R.string.app_name);
                    dlgLoading.dismiss();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    final int showDlgLoading = 0x0001;
    final int dismissDlgLoading = 0x0002;

    public void showDlgLoading(View view){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = uiHandler.obtainMessage();
                msg.what = showDlgLoading;
                uiHandler.sendMessage(msg);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                msg = uiHandler.obtainMessage();
                msg.what = dismissDlgLoading;
                uiHandler.sendMessage(msg);
            }
        });

        thread.start();
    }
}
