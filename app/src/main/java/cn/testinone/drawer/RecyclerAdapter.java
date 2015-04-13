package cn.testinone.drawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.testinone.R;
import cn.testinone.listView.Fruit;

/**
 * Created by LingChen on 15/4/13.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<Fruit> mItemList;
    protected Context context;
    protected RecyclerItemViewHolder.ViewHoldItemClick viewHoldItemClick;

    public RecyclerAdapter(List<Fruit> itemList, Context context, RecyclerItemViewHolder.ViewHoldItemClick viewHoldItemClick) {
        mItemList = itemList;
        this.context = context;
        this.viewHoldItemClick = viewHoldItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(this.context)
                .inflate(R.layout.item_recycler, parent, false);
        RecyclerItemViewHolder holder = RecyclerItemViewHolder.newInstance(view);
        holder.setViewHoldItemClick(viewHoldItemClick);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
        Fruit fruit = mItemList.get(position);

        holder.setItemText(fruit.getName());
        holder.setItemImg(fruit.getImgID());
        holder.itemView.setTag(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

}

