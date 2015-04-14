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
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import cn.testinone.R;
import cn.testinone.listView.Fruit;

public class DrawerTabsActivity extends ActionBarActivity
implements  RecyclerItemViewHolder.ViewHoldItemClick{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_drawer);

        initToolbar();
        initRecycleView();
    }

    LinearLayout toolbarContainer;
    Toolbar toolbar;

    private void initToolbar() {
        toolbarContainer = (LinearLayout) findViewById(R.id.toolbarContainer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("这里是tilte");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.holo_green_light));
    }

    RecyclerView recyclerView;
    List<Fruit> fruits;

    private void initRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        /*padding设置成Toolbar和Tab高度之和*/
        int paddingTop = getToolbarHeight(this) + getTabsHeight(this);
        recyclerView.setPadding(recyclerView.getPaddingLeft(), paddingTop,
                recyclerView.getPaddingRight(), recyclerView.getPaddingBottom());

        //创建默认的线性LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        fruits = Fruit.createFruits();
        RecyclerAdapter adapter = new RecyclerAdapter(fruits, DrawerTabsActivity.this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new HidingToolbarScrollListener(paddingTop) {
            /*
            下面都是通过动画效果实现的
             */
            @Override
            public void onMoved(int distance) {
                toolbarContainer.setTranslationY(-distance);
            }

            @Override
            public void onHide() {
                toolbarContainer.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
            }

            @Override
            public void onShow() {
                toolbarContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));

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

    @Override
    public void onClick(View v, int postion) {
        String txt = fruits.get(postion).getName();
        Toast.makeText(this, txt + " be clicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v, int postion) {
        String txt = fruits.get(postion).getName();
        Toast.makeText(this, txt + " be long clicked!", Toast.LENGTH_SHORT).show();
        return true;
    }


    public abstract class HidingToolbarScrollListener extends RecyclerView.OnScrollListener {
        private int mToolbarOffset = 0;
        private int mToolbarHeight;
        private Context context;

        public HidingToolbarScrollListener(int toolbarHeight) {
            mToolbarHeight = toolbarHeight;
        }

        private static final float HIDE_THRESHOLD = 10;
        private static final float SHOW_THRESHOLD = 70;
        private boolean mControlsVisible = true;

        private void setVisible() {
            if (mToolbarOffset > 0) {
                onShow();
                mToolbarOffset = 0;
            }
            mControlsVisible = true;
        }

        private void setInvisible() {
            if (mToolbarOffset < mToolbarHeight) {
                onHide();
                mToolbarOffset = mToolbarHeight;
            }
            mControlsVisible = false;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            /*
            检查列表是否处于RecyclerView.SCROLL_STATE_IDLE状态，这个状态下列表没有滚动
             */
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (totalScrolledDistance < mToolbarHeight) {
                    setVisible();
                } else {
                /*
                如果我们放开了手指并且列表停止滚动（这是就是RecyclerView.SCROLL_STATE_IDLE状态），
                我们需要检查当前Toolbar是否可见，如果是可见的，意味着在mToolbarOffset大于HIDE_THRESHOLD的时候隐藏它，
                而在mToolbarOffset小于SHOW_THRESHOLD的时候显示它。
                 */
                    if (mControlsVisible) {
                        if (mToolbarOffset > HIDE_THRESHOLD) {
                            setInvisible();
                        } else {
                            setVisible();
                        }
                    }
                 /*
                    如果Toolbar是不可见的，我们要做相反的事情-当mToolbarOffset
                    （现在是从顶部计算所以是mToolbarHeight - mToolbarOffset）大于SHOW_THRESHOLD显示，
                    当小于IDE_THRESHOLD再次隐藏
                     */
                    else {
                        if ((mToolbarHeight - mToolbarOffset) > SHOW_THRESHOLD) {
                            setVisible();
                        } else {
                            setInvisible();
                        }
                    }
                }
            }
        }

        /*
        mTotalScrolledDistance，当它小于Toolbar高度的时候，Toolbar总是显示
         */
        private int totalScrolledDistance =0;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            clipToolbarOffset();
            onMoved(mToolbarOffset);

            if ((mToolbarOffset < mToolbarHeight && dy > 0) || (mToolbarOffset > 0 && dy < 0)) {
                mToolbarOffset += dy;
            }

            totalScrolledDistance += dy;
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
