package cn.testinone.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.testinone.R;

/**
 * Created by LingChen on 15/4/12.
 */
public class RecyclerItemViewHolder extends RecyclerView.ViewHolder{
    private final TextView tvFruit;
    private final ImageView ivFruit;

    private ViewHoldItemClick viewHoldItemClick = null;
    public RecyclerItemViewHolder(final View parent, ViewHoldItemClick viewHoldItemClicker,
                                  TextView tvFruit, ImageView ivFruit) {
        super(parent);
        this.viewHoldItemClick = viewHoldItemClicker;
        this.tvFruit = tvFruit;
        this.ivFruit = ivFruit;
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHoldItemClick != null)
                    viewHoldItemClick.onClick(v, getPosition());
            }
        });
        parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (viewHoldItemClick != null)
                    viewHoldItemClick.onLongClick(v, getPosition());
                return true;
            }
        });
    }

    public static RecyclerItemViewHolder newInstance(View parent,ViewHoldItemClick viewHoldItemClicker) {
        TextView tvFruit = (TextView) parent.findViewById(R.id.tvFruit);
        ImageView ivFruit = (ImageView) parent.findViewById(R.id.ivFruit);
        return new RecyclerItemViewHolder(parent, viewHoldItemClicker, tvFruit, ivFruit);
    }

    public void setItemText(CharSequence text) {
        tvFruit.setText(text);
    }

    public String getItemText(){
        return tvFruit.getText().toString();
    }

    public void setItemImg(int resId) {
        this.ivFruit.setImageResource(resId);
    }

    public interface ViewHoldItemClick{
        void onClick(View v, int postion);
        boolean onLongClick(View v, int postion);
    }
}
