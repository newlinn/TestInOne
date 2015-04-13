package cn.testinone.drawer;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import cn.testinone.R;
import cn.testinone.listView.Fruit;

public class DrawerTabsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_drawer);

        initToolbar();
        initRecycleView();
    }

    Toolbar toolbar;

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("这里是tilte");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.holo_green_light));
    }

    RecyclerView recyclerView;
    private void initRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //创建默认的线性LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        RecyclerAdapter adapter = new RecyclerAdapter(Fruit.createFruits(), DrawerTabsActivity.this, null);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new HidingToolbarScrollListener(getToolbarHeight(this)) {
            @Override
            public void onMoved(int distance) {
                toolbar.setTranslationY(-distance);
            }

            @Override
            public void onHide() {
                toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
            }

            @Override
            public void onShow() {
                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));

            }
        });
    }

    public int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public int getTabsHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.tabsHeight);
    }


    public abstract class HidingToolbarScrollListener extends RecyclerView.OnScrollListener {
        private int mToolbarOffset = 0;
        private int mToolbarHeight;
        private Context context;

        public HidingToolbarScrollListener(int toolbarHeight){
            mToolbarHeight = toolbarHeight;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            clipToolbarOffset();
            onMoved(mToolbarOffset);

            if ((mToolbarOffset < mToolbarHeight && dy > 0) || (mToolbarOffset > 0 && dy < 0)) {
                mToolbarOffset += dy;
            }
        }

        private void clipToolbarOffset() {
            if (mToolbarOffset > mToolbarHeight) {
                mToolbarOffset = mToolbarHeight;
            } else if (mToolbarOffset < 0) {
                mToolbarOffset = 0;
            }
        }

        public abstract void onMoved(int distance);

        public abstract void onShow();

        public abstract void onHide();
    }
}
