package com.example.administrator.yanshi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.example.administrator.yanshi.po.ShopCart;
import com.example.administrator.yanshi.util.DButil;
import java.util.ArrayList;
import java.util.HashMap;


public class ShopCartShowAll extends Activity {

	//定义一个数据库类的引用
	private DButil db;

	//定义ArrayList包含所有的餐品
	private ArrayList shopcartList = new ArrayList();

	//定义购物车的一个类
	private ShopCart shopCart;

	//弹出删除餐品的标志 ，1表示删除或修改选中的餐品
	private static final int DIALOG1 = 1;

	//2 表示清空购物车中的食品
	private static final int DIALOG2 = 2;

	private HashMap item;

	//定义餐品的一个hashmap food
	private HashMap food;

	//表示选中listview的那一行的商品id
	private int food_id ;

	public int foodID;

	private Cursor shopcur;

	//设置总金额
	public float totalprices;

	//表示选中listview的那一行
	public int lvpostion;

	//设置头视图
	private View headview;

	//设置尾视图
	private View lastview;

	private TextView foodId;

	private TextView foodName;

	private TextView foodPrice;

	private TextView foodNum;

	//表示每一种餐品的总价
	private TextView foodSumPrices;

	//表示所有餐品的总价
	private TextView foodtotalmoney;

	//定义传递的参数
	public Intent param;

	public int userId;

	public String username;

	public String foodname;

	public float foodprice;

	private int foodnum;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//初始化数据库
		db = new DButil(this);

		//获得购物车中所有的游标
		shopcur = db.getAllCartCursor();

		//获取数据库中所有的数据
		shopcartList = db.getAllShopCart();

		//总价初始化为0；
		totalprices=0;

		param = this.getIntent();

		//解析wjf_shopcartthead文件
		headview = LayoutInflater.from(this).inflate(R.layout.wjf_shopcarthead,null);

		//解析wjf_shopcartlast文件
		lastview= LayoutInflater.from(this).inflate(R.layout.wjf_shopcartlast,null);

		foodId=(TextView) headview.findViewById(R.id.foodid);
		foodName=(TextView) headview.findViewById(R.id.foodname);
		foodPrice=(TextView) headview.findViewById(R.id.foodprice);
		foodNum=(TextView) headview.findViewById(R.id.foodnumber);
		foodSumPrices=(TextView) headview.findViewById(R.id.sumpirces);
		foodtotalmoney=(TextView) lastview.findViewById(R.id.toatalmoney);

		for(int i=0;i<shopcartList.size();i++){
			food=(HashMap) shopcartList.get(i);
			Float prices= (Float) food.get("foodsumprices");
			//得到所有餐品总的金额
			totalprices+=prices;
		}
		//显示所有餐品的总金额
		foodtotalmoney.setText(totalprices+"");
		ListView lv = new ListView(this);
		SimpleAdapter sa=new SimpleAdapter(this,shopcartList, R.layout.wjf_shopcartshowall,
				new String[]{"foodid","foodname","foodprice","foodnum","foodsumprices"},
				new int[]{R.id.foodid,R.id.foodname,R.id.foodprice, R.id.foodnumber,R.id.sumpirces});
		sa.notifyDataSetChanged();

		//加载头文件视图
		lv.addHeaderView(headview);

		//加载尾文件视图
		lv.addFooterView(lastview);

		lv.setAdapter(sa);
		//设置整个界面文件
		setContentView(lv);

		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				if(0<arg2&&arg2<=shopcartList.size()){
					//移动游标位置
					shopcur.moveToPosition(arg2-1);
					//获取选中的listview的某一行的foodid
					foodID = shopcur.getInt(shopcur.getColumnIndex("foodid"));
					foodname = (String) shopcur.getString(shopcur.getColumnIndex("foodname"));
					foodprice = shopcur.getFloat(shopcur.getColumnIndex("foodprice"));
					foodnum = shopcur.getInt(shopcur.getColumnIndex("foodnum"));
					lvpostion = arg2-1;
					Log.e("---------foodId", "" + foodID);
					//表示listview的位置
					showDialog(DIALOG1);
				}
				else{
					foodID=-1;
				}
			}
		});
		lv.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
									   long arg3) {
				if(0<arg2&&arg2<=shopcartList.size()){
					//移动游标位置
					shopcur.moveToPosition(arg2-1);
					//获取选中的listview的某一行的foodid
					food_id = shopcur.getInt(shopcur.getColumnIndex("foodid"));
					Log.e("---------Id", "" + food_id);
					//表示listview的位置
					lvpostion=arg2-1;

				}
				else{
					food_id=-1;
				}
			}
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

	}
	protected Dialog onCreateDialog(int id) {
		super.onCreateDialog(id);
		switch (id) {
			case DIALOG1: {
				return builder1(this);
			}
			case DIALOG2: {
				return builder2(this);
			}
		}
		return null;

	}
	private Dialog builder1(Context ctx) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		// 设置Dialog的标题
		builder.setTitle("修改或删除");
		// 设置Dialog的图标
		builder.setIcon(R.drawable.modify);
		// 设置Dialog的显示信息
		builder.setMessage("修改还是删除");
		// 设置Dialog上的一个按钮和按钮的单击事件
		builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				/*Intent tent=new Intent(ShopCartShowAll.this,ModifyListCart.class);
				tent.putExtra("foodname", foodname);
				tent.putExtra("foodprice", foodprice);
				tent.putExtra("foodid", foodID);
				tent.putExtra("foodnum", foodnum);
				//关闭数据库
				db.close();
				startActivity(tent);
				finish();*/
			}
		});
		builder.setNeutralButton("删除", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				if(foodID>0){
					HashMap map=(HashMap) shopcartList.get(lvpostion);
					Float price=(Float) map.get("foodsumprices");
					totalprices-=price;
					db.deleteFood("foodid="+foodID);
					Intent tent=new Intent(ShopCartShowAll.this,ShopCartShowAll.class);
					tent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					//关闭数据库
					db.close();
					startActivity(tent);
					finish();
				}
			}

		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

			}

		});

		return builder.create();
	}


	private Dialog builder2(Context ctx) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		// 设置Dialog的标题
		builder.setTitle("删除确认");
		// 设置Dialog的图标
		builder.setIcon(R.drawable.delete);
		// 设置Dialog的显示信息
		builder.setMessage("真的要删除吗?");
		// 设置Dialog上的一个按钮和按钮的单击事件
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if(db.getAllShopCart().size()>0){
					Log.e("-----------get shopcart", ""+db.getAllShopCart().size());
					db.deleteAllShopcart();
					Intent tent=new Intent(ShopCartShowAll.this,ShopCartShowAll.class);
					tent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					//关闭数据库
					db.close();
					startActivity(tent);
					finish();
				}
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				setTitle("取消按钮按下");
			}
		});

		return builder.create();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(2, Menu.FIRST + 1, 2, "清空购物车").setIcon(R.drawable.delete);
		menu.add(3, Menu.FIRST + 2, 3, "继续购物").setIcon(R.drawable.goshop);
		menu.add(4, Menu.FIRST + 3, 4, "下订单").setIcon(R.drawable.lastlist);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
			//删除所有的餐品
			case Menu.FIRST + 1: {
				showDialog(DIALOG2);
				break;
			}
			//继续购物
			case Menu.FIRST + 2: {
				/*Intent intent=new Intent(ShopCartShowAll.this,AndroidFoodMain.class);
				db.close();
				startActivity(intent);
				finish();
				break;*/
			}
			//生产订单
			case Menu.FIRST + 3: {
				/*Intent intent=new Intent(ShopCartShowAll.this,OrderListActivity.class);
				intent.putExtra("totalprices", totalprices);
				db.close();
				startActivity(intent);
				finish();
				break;*/
			}
		}
		return true;
	}

/*	@Override
	protected void onStart() {
		super.onStart();
		if(db != null)
		    db.close();
		//初始化数据库
		db=new DButil(this);

	}*/

	protected void onStop() {
		super.onStop();
		db.close();
	}


}
