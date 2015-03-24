package cn.testinone.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyNotificationReceiver extends BroadcastReceiver {
    public MyNotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle= intent.getExtras();
        String value = bundle.getString("do");
        Toast.makeText(context, value, Toast.LENGTH_LONG).show();
    }
}
