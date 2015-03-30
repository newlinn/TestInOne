package cn.testinone.listView;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.testinone.R;

public class FruitActivity extends Activity {
    ListView lvFruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        List<Fruit> fruits = new ArrayList<Fruit>();
        initFruits(fruits);

        lvFruit = (ListView)findViewById(R.id.lvFruit);
        FruitAdapter fruitAdapter = new FruitAdapter(FruitActivity.this, R.layout.item_fruit, fruits, lvFruit);
        lvFruit.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvFruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean flagChecked = lvFruit.isItemChecked(position);
                lvFruit.setItemChecked(position, flagChecked);
            }
        });
        lvFruit.setAdapter(fruitAdapter);
    }

    private void initFruits(List<Fruit> fruits) {
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
    }
}
