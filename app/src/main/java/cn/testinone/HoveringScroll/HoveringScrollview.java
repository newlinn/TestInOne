package cn.testinone.HoveringScroll;

import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by LingChen on 15/9/9.
 */
public class HoveringScrollview extends ScrollView  {
    public HoveringScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    int lastScrollY = 0;
    android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (MotionEvent.ACTION_UP == msg.what) {
                int scrollY = msg.arg1;
                if (lastScrollY != scrollY) {
                    lastScrollY = scrollY;
                    handler.sendMessageDelayed(handler.obtainMessage(), 6);
                }
                if (onScrollListener != null) {
                    onScrollListener.onScroll(scrollY);
                }
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (onScrollListener != null) {
            lastScrollY = this.getScrollY();
            onScrollListener.onScroll(lastScrollY);
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP: //抬起手指
                Message msg = handler.obtainMessage(MotionEvent.ACTION_UP);
                msg.arg1 = this.getScrollY();
                handler.sendMessageDelayed(handler.obtainMessage(), 6);
                break;
        }
        return super.onTouchEvent(ev);
    }

    onScrollListener onScrollListener;
    public void setOnScrollListener(onScrollListener onscrollListener)
    {
        this.onScrollListener = onscrollListener;

    }

    public interface onScrollListener {
        void onScroll(int scrollY); // 滚动条Y轴的值
    }
}
