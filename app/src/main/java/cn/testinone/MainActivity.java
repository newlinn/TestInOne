package cn.testinone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import cn.testinone.components.NotificationActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.btnNotification:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            default:
                break;
        }
    }
}
