package cn.testinone.menu;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.testinone.R;

public class DrawerLayoutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        initToolbar();
        initDrawlayout();
    }

    DrawerLayout drawerlayout;
    ActionBarDrawerToggle drawerToggle;
    private void initDrawlayout(){
        drawerlayout = (DrawerLayout)findViewById(R.id.custom_drawerlayout);
        //创建返回键，并实现打开关/闭监听
        drawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.OPEN, R.string.CLOSE) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerToggle.syncState();
        drawerlayout.setDrawerListener(drawerToggle);
    }

    Toolbar toolbar;
    private void initToolbar(){
        toolbar= (Toolbar)findViewById(R.id.custom_toolbar);
        toolbar.setTitle("Toolbar");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
