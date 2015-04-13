package cn.testinone.listView;

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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import cn.testinone.R;

public class RecycleFruitActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_fruit);
        initToolbar();
        initRecyclerView();
        btnFab = (ImageButton)findViewById(R.id.btnFab);
    }

    private List<Fruit> createFruits() {
        List<Fruit> fruits= new ArrayList<Fruit>();

        Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
        fruits.add(apple);
        Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
        fruits.add(banana);
        Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
        fruits.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
        fruits.add(watermelon);
        Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
        fruits.add(pear);
        Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
        fruits.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
        fruits.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
        fruits.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
        fruits.add(cherry);
        Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
        fruits.add(mango);

        return fruits;
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

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createFruits());
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

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<Fruit> mItemList;

        public RecyclerAdapter(List<Fruit> itemList) {
            mItemList = itemList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(RecycleFruitActivity.this)
                    .inflate(R.layout.item_recycler, parent, false);
            return RecyclerItemViewHolder.newInstance(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
            Fruit fruit = mItemList.get(position);

            holder.setItemText(fruit.getName());
            holder.setItemImg(fruit.getImgID());
        }
        @Override
        public int getItemCount() {
            return mItemList == null ? 0 : mItemList.size();
        }
    }



}
