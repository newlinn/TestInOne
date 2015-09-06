package cn.testinone.lstview;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.testinone.MainActivity;
import cn.testinone.R;

public class LstViewFragment extends Fragment
implements MainActivity.BackToListTop {

    public LstViewFragment() {
        // Required empty public constructor
    }

    public void toTop() {
        lvFruit.scrollTo(0, 0);
    }

    ///重写onCreateView决定Fragemnt的布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("LstViewFragment", "onActivityCreated");
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_lst_view, container, false);
        lvFruit = (ListView) view.findViewById(R.id.lvFruit);

        CommonAdapter<Fruit> adapter = new CommonAdapter(getActivity(),
                R.layout.layout_fruit_item, iniFruits()) {
            @Override
            public void setValue(ViewHolder viewHolder, int position) {
                final Fruit fruit = (Fruit) getItem(position);
                ImageView iv = (ImageView) viewHolder.getItem(R.id.ivFruit);
                iv.setImageResource(fruit.getResId());
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), fruit.getResId(), Toast.LENGTH_SHORT).show();
                    }
                });

                ((TextView) viewHolder.getItem(R.id.tvFruit)).setText(fruit.getName());
                ((Button) viewHolder.getItem(R.id.btnFruit)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), fruit.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        lvFruit.setAdapter(adapter);
        lvFruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String txt = ((TextView) (((ViewHolder) view.getTag()).getItem(R.id.tvFruit))).getText().toString();
                Toast.makeText(getActivity(), txt, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("LstViewFragment", "onCreateView");
        super.onActivityCreated(savedInstanceState);

    }

    ListView lvFruit;

    private ArrayList<Fruit> iniFruits() {
        ArrayList<Fruit> fruits = new ArrayList<>(20);
        fruits.add(0, new Fruit("apple", R.drawable.apple_pic));
        fruits.add(1, new Fruit("banana", R.drawable.banana_pic));
        fruits.add(2, new Fruit("cherry", R.drawable.cherry_pic));
        fruits.add(3, new Fruit("grape", R.drawable.grape_pic));
        fruits.add(4, new Fruit("mango", R.drawable.mango_pic));
        fruits.add(5, new Fruit("orange", R.drawable.orange_pic));
        fruits.add(6, new Fruit("pear", R.drawable.pear_pic));
        fruits.add(7, new Fruit("pineapple", R.drawable.pineapple_pic));
        fruits.add(8, new Fruit("strawberry", R.drawable.strawberry_pic));
        fruits.add(9, new Fruit("watermelon", R.drawable.watermelon_pic));
        fruits.add(10, new Fruit("apple", R.drawable.apple_pic));
        fruits.add(11, new Fruit("banana", R.drawable.banana_pic));
        fruits.add(12, new Fruit("cherry", R.drawable.cherry_pic));
        fruits.add(13, new Fruit("grape", R.drawable.grape_pic));
        fruits.add(14, new Fruit("mango", R.drawable.mango_pic));
        fruits.add(15, new Fruit("orange", R.drawable.orange_pic));
        fruits.add(16, new Fruit("pear", R.drawable.pear_pic));
        fruits.add(17, new Fruit("pineapple", R.drawable.pineapple_pic));
        fruits.add(18, new Fruit("strawberry", R.drawable.strawberry_pic));
        fruits.add(19, new Fruit("watermelon", R.drawable.watermelon_pic));
        return fruits;
    }


    class Fruit {
        public Fruit(String name, int resId) {
            this.name = name;
            this.resId = resId;
        }

        public String getName() {
            return name;
        }

        public int getResId() {
            return resId;
        }

        String name;
        int resId;
    }
}
