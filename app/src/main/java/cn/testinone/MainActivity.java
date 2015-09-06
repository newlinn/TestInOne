package cn.testinone;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import cn.testinone.lstview.LstViewFragment;
import cn.testinone.srv.SrvFragment;
import cn.testinone.threads.ThreadActivity;
import cn.testinone.tv.AdjustTextViewActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        drawerLayout.setScrimColor(Color.TRANSPARENT);
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
        switch (id) {
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
    TextView tvContainer;

    private void findViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvContainer = (TextView) findViewById(R.id.tvContainer);
        tvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getFragmentManager().findFragmentById(R.id.llcontainer);
                if (fragment instanceof BackToListTop) {
                    ((BackToListTop) fragment).toTop();
                }
            }
        });
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

    final long waitTime = 2000;
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

    Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                default:
                    super.handleMessage(msg);
            }
        }
    };

    public void leftMenuClick(View view) {
        drawerLayout.closeDrawer(Gravity.LEFT);
        switch (view.getId()) {
            case R.id.btnSrv:
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.llcontainer, new SrvFragment()).commit();
                break;
            case R.id.btnLstView:
                LstViewFragment lstViewFragment = new LstViewFragment();
                getFragmentManager().beginTransaction().replace(R.id.llcontainer, lstViewFragment).commit();
                break;
            case R.id.btnThread:
                Intent intent = new Intent(MainActivity.this, ThreadActivity.class);
                startActivity(intent);
                break;
            case R.id.btnEditor:
                startActivity(new Intent(MainActivity.this, EdtActivity.class));
                break;
            case R.id.btnTextView:
                startActivity(new Intent(MainActivity.this, AdjustTextViewActivity.class));
                break;
            default:
                break;
        }

    }

    public interface BackToListTop
    {
        void toTop();
    }
}
