package cn.testinone.drawer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.testinone.R;
import cn.testinone.listView.Fruit;

public class RecycleFruitActivity extends ActionBarActivity
        implements RecyclerItemViewHolder.ViewHoldItemClick{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_fruit);
        initToolbar();
        initRecyclerView();
        btnFab = (ImageButton)findViewById(R.id.btnFab);
    }

    Toolbar toolbar;
    RecyclerView recyclerView;
    ImageButton btnFab;

    private void initToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Toolbar hide on scroll!");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    List<Fruit> fruits;

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //创建默认的线性LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        fruits = Fruit.createFruits();
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(fruits, RecycleFruitActivity.this, this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });

    }


    private void hideViews(){
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) btnFab.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        btnFab.animate().translationY(btnFab.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();

    }

    private void showViews(){
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        btnFab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }


    @Override
    public void onClick(View v, int postion) {
        Fruit fruit = fruits.get(postion);
        Toast.makeText(RecycleFruitActivity.this, fruit.getName() + " onClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v, int postion) {
        Fruit fruit = fruits.get(postion);
        Toast.makeText(RecycleFruitActivity.this, fruit.getName() + " onLongClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
