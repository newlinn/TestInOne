package cn.testinone.tv;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.testinone.R;

public class AdjustTextViewActivity extends AppCompatActivity {

    final String content = "时候我们会遇到这样的情况，为了让布局显得更为精简，会对大段的文本（一般用于人物介绍等地方）进行折叠，用户点击展开。通常都带有一个小图标，随着折叠展开来进行翻转。这种效果是怎么展现的呢，老规矩，先上效果图。用的是genymotion模拟器，确实快了很多，只是电脑太渣，占用很多内存。";
    final int maxLine = 5;
    TextView tvContent;
    ImageView igAdjust;

    boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_text_view);
        igAdjust = (ImageView) findViewById(R.id.igAdjust);
        tvContent = (TextView) findViewById(R.id.tvContent);

        igAdjust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int heightDiff;
                int durationMillis = 200;
                RotateAnimation igAnimation;
                if (isExpanded) {
                    heightDiff = tvContent.getLineHeight() * maxLine - tvContent.getHeight();
                    igAnimation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                } else {
                    heightDiff = tvContent.getLineHeight() * tvContent.getLineCount() - tvContent.getHeight();
                    //翻转icon的180度旋转动画
                    igAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                }
                igAnimation.setDuration(durationMillis);
                igAnimation.setFillAfter(true);
                igAdjust.startAnimation(igAnimation);

                // set tvContent animation
                tvContent.clearAnimation();
                final int startHeight = tvContent.getHeight();
                Animation tvAnimation = new Animation() {
                    //interpolatedTime 为当前动画帧对应的相对时间，值总在0-1之间
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        tvContent.setHeight((int) (startHeight + heightDiff * interpolatedTime));
                    }
                };
                tvAnimation.setDuration(durationMillis);
                tvContent.startAnimation(tvAnimation);

                isExpanded = !isExpanded;
            }
        });

        tvContent.setText(content);
        tvContent.setHeight(tvContent.getLineHeight() * maxLine);
        //使用post会在其绘制完成后来对ImageView进行显示控制
        tvContent.post(new Runnable() {
            @Override
            public void run() {
                igAdjust.setVisibility(tvContent.getLineCount() > maxLine ? View.VISIBLE : View.GONE);
            }
        });
        tvContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tvContent.setTextIsSelectable(true);
                Toast.makeText(AdjustTextViewActivity.this, "longclick", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adjust_text_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
