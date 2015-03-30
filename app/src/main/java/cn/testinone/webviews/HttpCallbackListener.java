package cn.testinone.webviews;

/**
 * Created by LingChen on 15/3/30.
 * 线程内工作，更新UI时候，请注意使用handler
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception ex);
}
