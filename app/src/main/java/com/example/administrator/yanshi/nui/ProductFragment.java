package com.example.administrator.yanshi.nui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.example.administrator.yanshi.R;
import com.example.administrator.yanshi.cpxxDetail;
import com.example.administrator.yanshi.po.Product;
import com.example.administrator.yanshi.util.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sunbo on 16/8/8.
 */
public class ProductFragment extends Fragment implements AdapterView.OnItemClickListener{
    public static final String TAG = "ptype";
    private String productType;
    private ArrayList<Product> productList;
    private ProductAdapter adapter;
    public static final String GET_PRODUCTS_URL = "http://10.11.3.90:8080/yanshiServer/GetProductServlet";
    private Handler getNewsHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            String jsonData = (String) msg.obj;
            Log.e(TAG, "========================================="+jsonData);
            //  jsonData=jsonData.replace("],","],[");
            //  jsonData=jsonData.substring(0,jsonData.length()-1);
            //  jsonData="{"+jsonData.substring(1,jsonData.length()-2)+"}";
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String id = object.getString("productId");
                    String name = object.getString("productName");
                    String type = object.getString("productType");
                    String desc = object.getString("productDescri");
                    String price = object.getString("productPrice");
                    String image = HttpUtils.URL+object.getString("productImage");

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



    public View onCreateView(LayoutInflater inflater,ViewGroup container,
        Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.activity_wscs,container,false);
        GridView productView = (GridView) view.findViewById(R.id.productView);
        productType = getArguments().getString(TAG);
        productList = new ArrayList<Product>();
        adapter = new ProductAdapter(view.getContext(), productList);
        productView.setAdapter(adapter);
        productView.setOnItemClickListener(this);
        HttpUtils.getNewsJSON(GET_PRODUCTS_URL+"?productType="+productType, getNewsHandler);
        return view;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = productList.get(position);
        //WscsActivity.this.openOptionsMenu();
        Intent intent = new Intent(getActivity(), cpxxDetail.class);
        intent.putExtra("id", product.getId());
        intent.putExtra("name", product.getName());
        intent.putExtra("type", product.getType());
        intent.putExtra("price", product.getPrice());
        intent.putExtra("desc", product.getDesc());
        intent.putExtra("image", product.getImage());
        startActivity(intent);
    }
}
