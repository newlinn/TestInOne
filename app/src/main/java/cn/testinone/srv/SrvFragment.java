package cn.testinone.srv;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.testinone.R;

public class SrvFragment extends Fragment {

    public SrvFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("SrvFragment", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("SrvFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_srv, container, false);
    }


}
