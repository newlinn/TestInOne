package cn.testinone.listView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.testinone.R;

/**
 * Created by LingChen on 15/3/29.
 */
public class FruitAdapter extends ArrayAdapter<Fruit> {

    private Context context;
    private int resource;
    List<Fruit> fruits;
    ListView lstView;

    public FruitAdapter(Context context, int resource, List<Fruit> objects, ListView lstView) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.lstView = lstView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(resource, null);
            holder = new ViewHolder();
            holder.tvFruit = (TextView) view.findViewById(R.id.tvFruit);
            holder.ivFruit = (ImageView) view.findViewById(R.id.ivFruit);
            holder.btnFruit = (Button) view.findViewById(R.id.btnFruit);
            // 增加了Button，要修改layout文件
            holder.btnFruit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, holder.tvFruit.getText(), Toast.LENGTH_SHORT).show();
                }
            });

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Fruit fruit = getItem(position);
        holder.tvFruit.setText(fruit.getName());
        holder.ivFruit.setImageResource(fruit.getImgID());
        if (lstView.isItemChecked(position)) {
            view.setBackgroundColor(Color.LTGRAY);
            holder.tvFruit.setTextColor(Color.RED);
        } else {
            view.setBackgroundColor(Color.WHITE);
            holder.tvFruit.setTextColor(Color.BLACK);
        }

        return view;
    }

    static class ViewHolder {
        TextView tvFruit;
        ImageView ivFruit;
        Button btnFruit;
    }
}
