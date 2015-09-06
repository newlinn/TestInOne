package cn.testinone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Method;

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

    private View parentView;

    public void setParentView(View view) {
        this.parentView = view;
    }

    public void loading() {
        if (parentView != null)
            parentView.setVisibility(View.GONE);
        btnRefresh.setVisibility(View.INVISIBLE);
        tvStatus.setText("加载中...");
        setVisibility(View.VISIBLE);
    }

    public void success() {
        if (parentView != null)
            parentView.setVisibility(View.VISIBLE);
        setVisibility(View.GONE);
    }

    public void empty() {
        if (parentView != null)
            parentView.setVisibility(View.GONE);
        setVisibility(View.VISIBLE);
        btnRefresh.setVisibility(View.VISIBLE);
        tvStatus.setText("暂无数据！");
    }

    public void bindBtnClick(final Object parent, final String method, final Object... params) {
        btnRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Class[] paramsTypes = new Class[params.length];
                for (int i = 0; i < params.length; i++) {
                    paramsTypes[i] = params[i].getClass();
                }
                try {
                    Method m = parent.getClass().getDeclaredMethod(method, paramsTypes);
                    m.setAccessible(true);
                    m.invoke(parent, params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
