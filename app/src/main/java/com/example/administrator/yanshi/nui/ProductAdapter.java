package com.example.administrator.yanshi.nui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yanshi.R;
import com.example.administrator.yanshi.po.News;
import com.example.administrator.yanshi.po.Product;
import com.example.administrator.yanshi.util.HttpUtils;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

	private Context context;
	private List<Product> productList;

	public ProductAdapter(Context context, List<Product> productList){
		this.context = context;
		this.productList = productList;
	}

	@Override
	public int getCount() {
		return productList.size();
	}

	@Override
	public Product getItem(int position) {
		return productList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.product_item, null);
		}
		TextView tvName = (TextView) convertView.findViewById(R.id.productName);
		TextView tvDesc = (TextView) convertView.findViewById(R.id.productDesc);
		ImageView tvImage = (ImageView) convertView.findViewById(R.id.productImage);
		Product product = productList.get(position);
		tvName.setText(product.getName());
		tvDesc.setText(product.getDesc());
		String image = product.getImage();
		HttpUtils.setPicBitmapForTask(tvImage, image);
		
		return convertView;
	}
}
