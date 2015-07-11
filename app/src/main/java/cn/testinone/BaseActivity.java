package cn.testinone;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.zip.Inflater;

/**
 * Created by LingChen on 15/7/10.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dlgLoading = createLoadingDialog(BaseActivity.this);
    }

    protected Dialog dlgLoading;
    private Dialog createLoadingDialog(Context context) {

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.progress_bar, null);
        LinearLayout llProgress = (LinearLayout) view.findViewById(R.id.llProgress);
        loadingDialog.setContentView(llProgress,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        loadingDialog.setCancelable(false);
        return loadingDialog;
    }
}
