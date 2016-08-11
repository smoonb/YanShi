package com.example.administrator.yanshi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.administrator.yanshi.nui.NewsAdapter;
import com.example.administrator.yanshi.nui.ProductAdapter;
import com.example.administrator.yanshi.po.News;
import com.example.administrator.yanshi.po.Product;
import com.example.administrator.yanshi.util.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class WscsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private RequestParams requestParams;
    private GridView productView;
    private ProductAdapter adapter;
    private List<Product> productList;
    public final static int SHOW_FOOD_DETAIL = 0;
    public final static int CONFIRM_PURCHASE = 1;
    //public static final String GET_PRODUCTS_URL = "http://10.11.17.213:8080/yanshiServer/GetAllFoodServlet";
    public static final String GET_PRODUCTS_URL = "http://10.11.3.90:8080/yanshiServer/GetAllFoodServlet";
    private Handler getNewsHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            String jsonData = (String) msg.obj;
            //  jsonData=jsonData.replace("],","],[");
            //  jsonData=jsonData.substring(0,jsonData.length()-1);
            //  jsonData="{"+jsonData.substring(1,jsonData.length()-2)+"}";
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String id = object.getString("foodId");
                    String name = object.getString("foodName");
                    String type = object.getString("foodType");
                    String desc = object.getString("foodDescri");
                    String price = object.getString("foodPrice");
                    String image = HttpUtils.URL+object.getString("foodImage");

                    Product p = new Product();
                    p.setId(id);
                    p.setName(name);
                    p.setType(type);
                    p.setDesc(desc);
                    p.setImage(image);
                    p.setPrice(Float.parseFloat(price));
                    productList.add(p);
                }

                //notifyDataSetChanged方法强制listview调用getView来刷新每个Item的内容。
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wscs);
        productView = (GridView) findViewById(R.id.productView);
        productList = new ArrayList<Product>();
        adapter = new ProductAdapter(this, productList);
        productView.setAdapter(adapter);
        productView.setOnItemClickListener(this);
        HttpUtils.getNewsJSON(GET_PRODUCTS_URL, getNewsHandler);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = productList.get(position);
        //WscsActivity.this.openOptionsMenu();
        Intent intent = new Intent(WscsActivity.this, cpxxDetail.class);
        intent.putExtra("id", product.getId());
        intent.putExtra("name", product.getName());
        intent.putExtra("type", product.getType());
        intent.putExtra("price", product.getPrice());
        intent.putExtra("desc", product.getDesc());
        intent.putExtra("image", product.getImage());
        startActivity(intent);
    }

    /*public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(1, SHOW_FOOD_DETAIL, Menu.CATEGORY_SYSTEM, "菜品详情").setIcon(
                R.drawable.zqz_menu_lookdetail);
        menu.add(1, CONFIRM_PURCHASE, Menu.CATEGORY_SYSTEM, "加入购物车").setIcon(
                R.drawable.zqz_menu_purchase);
        //getMenuInflater().inflate(R.menu.menu_frm_wscs, menu);
        return true;
    }*/
}
