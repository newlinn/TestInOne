package cn.testinone;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by LingChen on 15/8/24.
 */
public class EmptyView extends RelativeLayout {
    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        tvStatus = new TextView(getContext());
        tvStatus.setId(R.id.id_tvStatus);
        tvStatus.setText("加载中...");
        LayoutParams tvParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        tvParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        tvParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(tvStatus, tvParams);

        btnRefresh = new Button(getContext());
        btnRefresh.setId(R.id.id_btnRefresh);
        btnRefresh.setText("刷新");
        LayoutParams btnParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        btnParams.addRule(RelativeLayout.BELOW, R.id.id_tvStatus);
        btnParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnParams.addRule(VISIBLE, INVISIBLE);
        addView(btnRefresh, btnParams);
    }

    private TextView tvStatus;
    private Button btnRefresh;

    public void success() {

    }

    public void empty() {

    }
}
