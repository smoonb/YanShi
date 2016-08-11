package com.example.administrator.yanshi.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
	//菜品图片资源定位符
	 public final static String URL = Util.getIpStr()+"images/";
	//String =  (String) this.getResources().getText(R.string.common_ip) +"images/";
	public static void getNewsJSON(final String url, final Handler handler){

		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection conn;
				InputStream is;
				try {
					conn = (HttpURLConnection) new URL(url).openConnection();
					conn.setRequestMethod("GET");
					is = conn.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(is,"GBK"));
					String line = "";
					StringBuilder result = new StringBuilder();
					while ( (line = reader.readLine()) != null ){
						result.append(line);
					}
					Message msg = new Message();
					msg.obj = result.toString();
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}).start();
	}
	
	public static void setPicBitmap(final ImageView ivPic, final String pic_url){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					HttpURLConnection conn = (HttpURLConnection) new URL(pic_url).openConnection();
					conn.connect();
					InputStream is = conn.getInputStream();
					Bitmap bitmap = BitmapFactory.decodeStream(is);
					ivPic.setImageBitmap(bitmap);
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}).start();
	}

	public static void setPicBitmapForTask(final ImageView ivImage, final String pic_url){
		new AsyncTask<String,Void,Bitmap>() {
			protected Bitmap doInBackground(String... params) {
				try {
					HttpURLConnection conn = (HttpURLConnection) new URL(params[0]).openConnection();
					conn.connect();
					InputStream is = conn.getInputStream();
					Bitmap bitmap = BitmapFactory.decodeStream(is);
					is.close();
					return bitmap;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onCancelled() {
				super.onCancelled();
			}

			protected void onCancelled(Bitmap result) {
				super.onCancelled(result);
			}

			protected void onPostExecute(Bitmap result) {
				ivImage.setImageBitmap(result);
				super.onPostExecute(result);
			}

			protected void onPreExecute() {
				super.onPreExecute();
			}

			protected void onProgressUpdate(Void... values) {
				super.onProgressUpdate(values);
			}
		}.execute(pic_url);
	}
	
}












