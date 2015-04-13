package cn.testinone.listView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.testinone.R;

/**
 * Created by LingChen on 15/4/12.
 */
public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvFruit;
    private final ImageView ivFruit;

    public RecyclerItemViewHolder(final View parent, TextView tvFruit, ImageView ivFruit) {
        super(parent);
        this.tvFruit = tvFruit;
        this.ivFruit = ivFruit;
    }

    public static RecyclerItemViewHolder newInstance(View parent) {
        TextView tvFruit = (TextView) parent.findViewById(R.id.tvFruit);
        ImageView ivFruit = (ImageView) parent.findViewById(R.id.ivFruit);
        return new RecyclerItemViewHolder(parent, tvFruit, ivFruit);
    }

    public void setItemText(CharSequence text) {
        tvFruit.setText(text);
    }

    public void setItemImg(int resId) {
        this.ivFruit.setImageResource(resId);
    }

}
