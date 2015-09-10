package cn.testinone.HoveringScroll;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.testinone.R;

public class HoveringScrollActivity extends Activity implements HoveringScrollview.onScrollListener {

    HoveringScrollview hoveringScrollview;
    LinearLayout containerHidden;
    LinearLayout containerShow;
    LinearLayout llTop;
    LinearLayout llTopHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovering_scroll);
        hoveringScrollview = (HoveringScrollview) findViewById(R.id.hoveringScrollview);
        hoveringScrollview.setOnScrollListener(this);
        containerHidden = (LinearLayout) findViewById(R.id.containerHidden);
        containerShow = (LinearLayout) findViewById(R.id.containerShow);
        llTop = (LinearLayout) findViewById(R.id.llTop);
        llTopHide = (LinearLayout) findViewById(R.id.llTopHide);
    }

    int containerBottom = 0;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 每次启动，设置顶部布局的高度
        if (hasFocus) {
            containerBottom = llTopHide.getBottom();
        }
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btnRushBug:
                Toast.makeText(HoveringScrollActivity.this, "抢购成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public void onScroll(int scrollY) {
        Log.d("scrollY", String.valueOf(scrollY) + "   " + containerBottom);
        if (scrollY >= containerBottom) {
            if (llTop.getParent() != containerHidden) {
                containerShow.removeView(llTop);
                containerHidden.addView(llTop);
                containerHidden.setVisibility(View.VISIBLE);
            }
        } else {
            if (llTop.getParent() != containerShow) {
                containerHidden.removeView(llTop);
                containerShow.addView(llTop);
                containerHidden.setVisibility(View.INVISIBLE);
            }
        }
    }
}
