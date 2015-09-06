package cn.testinone.lstview;

import android.content.Context;
import android.media.Image;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.testinone.R;

/**
 * Created by LingChen on 15/7/22.
 */
public class ViewHolder {

    public ViewHolder(View convertView){
        this.convertView = convertView;
    }

    View convertView;
    SparseArray<View> items = new SparseArray<>();

    public <T extends View> T getItem(int itemId) {
        View item = items.get(itemId);
        if (item == null) {
            item = convertView.findViewById(itemId);
            items.put(itemId, item);
        }
        return (T) item;
    }

}
