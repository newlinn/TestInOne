package cn.testinone;

import android.app.Application;
import android.content.Context;

/**
 * Created by LingChen on 15/3/23.
 */
public class MyApplication extends Application {
    public  static Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
