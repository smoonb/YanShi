package com.example.administrator.yanshi;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.yanshi.nui.NewsAdapter;
import com.example.administrator.yanshi.po.News;
import com.example.administrator.yanshi.util.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class ctyy extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private RequestParams requestParams;
    private ListView lvNews;
    private NewsAdapter adapter;
    private List<News> newsList;
    //public static final String GET_NEWS_URL = "http://10.11.17.213:8080/yanshiServer/GetAllFoodServlet";
    public static final String GET_NEWS_URL = "http://192.168.1.104:8080/yanshiServer/GetAllFoodServlet";
    private Handler getNewsHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            String jsonData = (String) msg.obj;
            //  jsonData=jsonData.replace("],","],[");
            //  jsonData=jsonData.substring(0,jsonData.length()-1);
            //  jsonData="{"+jsonData.substring(1,jsonData.length()-2)+"}";
            System.out.println(jsonData);
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String title = object.getString("foodName");
                    String desc = object.getString("foodDescri");
                    String time = object.getString("foodId");
                    String content_url = object.getString("foodType");
                    String pic_url = HttpUtils.URL+object.getString("foodImage");
                    newsList.add(new News(title, desc, time, content_url, pic_url));
                }
                adapter.notifyDataSetChanged();//notifyDataSetChanged方法强制listview调用getView来刷新每个Item的内容。
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctyy);

        lvNews = (ListView) findViewById(R.id.lvNews1);
        newsList = new ArrayList<News>();
        adapter = new NewsAdapter(this, newsList);
        lvNews.setAdapter(adapter);
        lvNews.setOnItemClickListener(this);
        HttpUtils.getNewsJSON(GET_NEWS_URL, getNewsHandler);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
