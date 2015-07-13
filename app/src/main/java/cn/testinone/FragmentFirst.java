package cn.testinone;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LingChen on 15/7/13.
 */
public class FragmentFirst extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btnFragment = (Button) getActivity().findViewById(R.id.btnFragment);
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvFragment = (TextView) getActivity().findViewById(R.id.tvFragment);
                //Toast.makeText(getActivity(), tvFragment.getText(), Toast.LENGTH_SHORT).show();
                TextView tvContainer = (TextView) getActivity().findViewById(R.id.tvContainer);
                tvContainer.setText(tvFragment.getText());
            }
        });
    }
}
