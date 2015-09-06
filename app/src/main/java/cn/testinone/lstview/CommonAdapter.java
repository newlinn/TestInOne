package cn.testinone.lstview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.testinone.R;

/**
 * Created by LingChen on 15/7/22.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    public CommonAdapter(Context context, int itemLayout, List<T> data) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.data = data;
    }

    Context context;
    int itemLayout;
    List<T> data;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(itemLayout, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        setValue(viewHolder, position);
        return view;
    }

    public abstract void setValue(ViewHolder viewHolder, int position);
}
