package cn.testinone.webviews;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import cn.testinone.R;

public class WebDataActivity extends Activity {

    String jsonData;

    TextView tvParseContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_data);
        tvParseContent = (TextView) findViewById(R.id.tvParseContent);

        jsonData = "[{\"CityId\":18,\"CityName\":\"西安\",\"ProvinceId\":27,\"CityOrder\":1},{\"CityId\":53,\"CityName\":\"广州\",\"ProvinceId\":27,\"CityOrder\":1}]";
    }

    class Area {
        public int CityId;
        public String CityName;
        public int ProvinceId;
        public int CityOrder;
    }

    public void gsonJSON(View view){
        tvParseContent.clearComposingText();
        Gson gson = new Gson();
        List<Area> areas = gson.fromJson(jsonData, new TypeToken<List<Area>>(){}.getType());

        for (Area area : areas)
        {
            tvParseContent.append("CityId:" + area.CityId);
            tvParseContent.append("CityName:" + area.CityName);
            tvParseContent.append("ProvinceId:" + area.ProvinceId);
            tvParseContent.append("CityOrder:" + area.CityOrder);
            tvParseContent.append(System.getProperty("line.separator"));
        }
    }

    public void parseJSON(View view) {
        tvParseContent.clearComposingText();
        try {
            JSONArray arr = new JSONArray(jsonData);
            for (int i=0; i<arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                tvParseContent.append("CityId:" + obj.getInt("CityId"));
                tvParseContent.append("CityName:" + obj.getString("CityName"));
                tvParseContent.append("ProvinceId:" + obj.getInt("ProvinceId"));
                tvParseContent.append("CityOrder:" + obj.getInt("CityOrder"));
                tvParseContent.append(System.getProperty("line.separator"));
            }

        } catch (Exception ex) {
            tvParseContent.setText(jsonData);
            tvParseContent.append(ex.toString());
        }
    }
}
