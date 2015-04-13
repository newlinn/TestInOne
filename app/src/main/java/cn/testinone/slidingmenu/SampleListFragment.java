package cn.testinone.slidingmenu;

import android.app.ListFragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.testinone.R;

/**
 * 列表Fragment，用来显示列表视图
 */
public class SampleListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SampleAdapter sampleAdapter = new SampleAdapter(getActivity());
        for (int i = 0; i < 20; i++) {
            sampleAdapter.add(new SampleItem("SampleItem"+i, R.drawable.ic_launcher));
        }
        setListAdapter(sampleAdapter);
    }

    public class SampleItem {
        public String tag;
        public int iconRes;

        public SampleItem(String tag, int iconRes) {
            this.tag = tag;
            this.iconRes = iconRes;
        }
    }

    static class RowView {
        public ImageView imageView;
        public TextView textView;
    }

    public class SampleAdapter extends ArrayAdapter<SampleItem> {
        public SampleAdapter(Context context) {
            super(context, 0);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final RowView rowView;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
                rowView = new RowView();
                rowView.imageView = (ImageView) convertView.findViewById(R.id.row_icon);
                rowView.textView = (TextView) convertView.findViewById(R.id.row_title);
                convertView.setTag(rowView);
            } else {
                rowView = (RowView) convertView.getTag();
            }
            final SampleItem sampleItem = getItem(position);
            rowView.imageView.setImageResource(sampleItem.iconRes);
            rowView.textView.setText(sampleItem.tag);

            return convertView;
        }
    }
}
