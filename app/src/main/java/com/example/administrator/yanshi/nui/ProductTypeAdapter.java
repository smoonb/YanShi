package com.example.administrator.yanshi.nui;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yanshi.R;
import com.example.administrator.yanshi.gtyy;
import com.example.administrator.yanshi.po.Product;
import com.example.administrator.yanshi.util.HttpUtils;

import java.util.List;

public class ProductTypeAdapter extends BaseAdapter {

	private Context context;
	private ProductType[] pTypes;
	public static int mPosition;

	public ProductTypeAdapter(Context context, ProductType[] pTypes){
		this.context =context;
		this.pTypes = pTypes;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pTypes.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return pTypes[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(context).inflate(R.layout.product_type_item, null);
		TextView tv = (TextView) convertView.findViewById(R.id.tv);
		mPosition = position;
		tv.setText(pTypes[position].getName());
		if (position == gtyy.mPosition) {
			convertView.setBackgroundColor(Color.parseColor("#ffffff"));
		} else {
			convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
		}
		return convertView;
	}
}
