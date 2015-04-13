package cn.testinone.listView;

import java.util.ArrayList;
import java.util.List;

import cn.testinone.R;

/**
 * Created by LingChen on 15/3/29.
 */
public class Fruit {

    private String name;
    private int imgID;

    public String getName() {
        return name;
    }

    public int getImgID() {
        return imgID;
    }

    public Fruit(String name, int imgID) {
        this.name = name;
        this.imgID = imgID;
    }

    public static List<Fruit> createFruits() {
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
}
