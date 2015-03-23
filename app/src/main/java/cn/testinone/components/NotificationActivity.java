package cn.testinone.components;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import cn.testinone.R;

public class NotificationActivity extends Activity {

    NotificationManager manager;
    Bitmap icon;

    static int FLAG_NORMAL = 1001;
    static int FLAG_BIG_VIEW_TEXT = 1002;
    static int FLAG_BIG_VIEW_PIC = 1003;
    static int FLAG_INBOX = 1004;
    static int FLAG_CUSTOM = 1005;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notification);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }

    public void dismiss(View view) {
        manager.cancelAll();
    }

    public void normalNotification(View view) {
        Notification notification = new NotificationCompat.Builder(this)
                .setLargeIcon(icon)
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("showNormal")
                .setContentInfo("contentInfo")
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setNumber(FLAG_NORMAL)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        manager.notify(FLAG_NORMAL, notification);
    }

    public void bigViewTextNotification(View view) {
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("BigTextStyle")
                .setSummaryText("BigTextStyleSummaryText")
                .bigText("I am Big Texttttttttttttttttttttttttttttttttttttttttttt!!!!!!!!!!!!!!!!!!!......");

        Notification notification = new NotificationCompat.Builder(this)
                .setStyle(bigTextStyle)
                .setLargeIcon(icon)
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("showBigView_Text")
                .setContentInfo("contentInfo")
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();

        manager.notify(FLAG_BIG_VIEW_TEXT, notification);
    }

    public void bigViewPicNotification(View view) {
        NotificationCompat.BigPictureStyle bigPicStyle = new NotificationCompat.BigPictureStyle();
        bigPicStyle.setBigContentTitle("BigPictureStyle")
                .setSummaryText("BigPictureStyle")
                .bigPicture(icon);

        Notification notification = new NotificationCompat.Builder(this)
                .setStyle(bigPicStyle)
                .setLargeIcon(icon)
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("showBigView_Text")
                .setContentInfo("contentInfo")
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        manager.notify(FLAG_BIG_VIEW_PIC, notification);
    }

    public void inboxNotification(View view) {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("inboxStyle")
                .setSummaryText("inboxStyle");
        for (int i = 0; i < 10; i++)
            inboxStyle.addLine("line:" + i);

        Notification notification = new NotificationCompat.Builder(this)
                .setStyle(inboxStyle)
                .setLargeIcon(icon)
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("showBigView_Text")
                .setContentInfo("contentInfo")
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();

        manager.notify(FLAG_INBOX, notification);
    }

    public void customNotification(View view) {
        Notification notification = new NotificationCompat.Builder(this)
                .setContent(remoteViews)
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(icon)
                .setOngoing(true)
                .setTicker("music is playing")
                .build();
        manager.notify(FLAG_CUSTOM, notification);
    }
}
