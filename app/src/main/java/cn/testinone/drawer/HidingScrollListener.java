package cn.testinone.drawer;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by LingChen on 15/4/12.
 */
public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {

    private static final int HIDE_THRESHOLD = 20; //滚动多少距离进行隐藏
    private int scrolledDistance = 0;
    private boolean controlsVisible = true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        /*
        第一个item被遮挡之后，才触发隐藏
         */
        int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisibleItemPosition == 0) {
            if (!controlsVisible) {
                onShow();
                controlsVisible = true;
            }
        }
        else {
        /*
        如果总的滚动距离超多了一定值（这个值取决于你自己的设定，越大，需要滑动的距离越长才能显示或者隐藏），
        我们就根据其方向显示或者隐藏Toolbar（dy>0意味着下滚，dy<0意味着上滚）
         */
            if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
                onHide();
                controlsVisible = false;
                scrolledDistance = 0;
            } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
                onShow();
                controlsVisible = true;
                scrolledDistance = 0;
            }
        }
        /*
        计算出滚动的总距离（deltas相加），但是只在Toolbar隐藏且上滚或者Toolbar未隐藏且下滚的时候
         */
        if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
            scrolledDistance += dy;
        }

    }

    public abstract void onHide();

    public abstract void onShow();

}
