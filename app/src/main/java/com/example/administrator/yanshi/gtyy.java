package com.example.administrator.yanshi;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.administrator.yanshi.nui.NewsAdapter;
import com.example.administrator.yanshi.nui.ProductAdapter;
import com.example.administrator.yanshi.nui.ProductFragment;
import com.example.administrator.yanshi.nui.ProductType;
import com.example.administrator.yanshi.nui.ProductTypeAdapter;
import com.example.administrator.yanshi.po.News;
import com.example.administrator.yanshi.po.Product;
import com.example.administrator.yanshi.util.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class gtyy extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ProductType[] pTypes = { new ProductType("特价商品","1"),
            new ProductType("饮料冲调", "2"),
            new ProductType("副食调料","3"),
            new ProductType("粮油调味","4"),
            new ProductType("中外酒饮","5"),
            new ProductType("混合食品","6"),
            new ProductType("水果蔬菜","7"),
            new ProductType("日用品","8")};
    private ListView listView;
    private ProductTypeAdapter adapter;
    private ProductFragment pFragment;
    public static int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gtyy);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        // TODO Auto-generated method stub
        listView = (ListView) findViewById(R.id.listview);

        adapter = new ProductTypeAdapter(this, pTypes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        //创建MyFragment对象
        pFragment = new ProductFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, pFragment);
        //通过bundle传值给MyFragment
        Bundle bundle = new Bundle();
        bundle.putString(ProductFragment.TAG, pTypes[mPosition].getValue());
        pFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        //拿到当前位置
        mPosition = position;
        //即时刷新adapter
        adapter.notifyDataSetChanged();
        /*for (int i = 0; i < strs.length; i++) {
            pFragment = new ProductFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, pFragment);
            Bundle bundle = new Bundle();
            bundle.putString(ProductFragment.TAG, strs[position]);
            pFragment.setArguments(bundle);
            fragmentTransaction.commit();
        }*/


        pFragment = new ProductFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, pFragment);
        Bundle bundle = new Bundle();
        bundle.putString(ProductFragment.TAG, pTypes[position].getValue());
        pFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

}
