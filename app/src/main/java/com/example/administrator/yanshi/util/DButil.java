package com.example.administrator.yanshi.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.yanshi.po.ShopCart;

import java.util.ArrayList;
import java.util.HashMap;


public class DButil {
	//列出购物车中所有字段
	private static final String[] COLS = new String[]{"foodid","foodname","foodprice",
			"foodnum","foodsumprices","foodimage"};
	//数据库名字
	public static String DB = "shopcartDB";
	//购物车的表名
	public static String TABLE_SHOP_CART = "shopcart";
	//创建购物车表
	public static String create_table_shopcard = "create table " + TABLE_SHOP_CART
			+ "(foodid integer,foodname text,foodprice float,foodnum int,foodsumprices float,foodimage int)";
	public int version = 7;
	private Context ctx;
	private SQLiteDatabase sqllite;
	private SQLiteOpenHelper helper;
	//new 出数据库并初始化
	public DButil(Context context){
		this.ctx = context;
		init();
	}
	//数据库初始化
	private void init(){
		if(helper == null){
			helper = new MySQLiteHelper(ctx, DB, null, version);
			sqllite = helper.getWritableDatabase();
		}
	}

	// 新增一条购买商品记录
	public long addFood(ShopCart food) {
		//用来装数据
		try {
			Cursor cur = sqllite.query(TABLE_SHOP_CART, COLS, null, null, null, null, null);
			cur.moveToFirst();
			int foodId;
			int count = cur.getCount();
			for (int i = 0; i < count; i++) {
				foodId=cur.getInt(0);
				//若存在此foodid则只增加数量和总价
				if(food.getFoodId()==foodId){
					ContentValues value = new ContentValues();
					value.put("foodnum", food.getFoodNum()+cur.getInt(3));
					value.put("foodsumprices", food.getSumPrices()+cur.getFloat(4));
					return sqllite.update(TABLE_SHOP_CART, value, "foodid="+foodId, null);
				}
				cur.moveToNext();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ContentValues value = new ContentValues();
		value.put("foodid", food.getFoodId());
		value.put("foodname", food.getFoodName());
		value.put("foodprice", food.getFoodPrice());
		value.put("foodnum", food.getFoodNum());
		value.put("foodsumprices", food.getSumPrices());
		value.put("foodimage", food.getImage());
		return sqllite.insert(TABLE_SHOP_CART, null, value);
	}
	//查询所有的游标
	public Cursor getAllCartCursor(){
		return 	sqllite.query(TABLE_SHOP_CART, COLS, null, null, null, null, null);
	}
	//得到购物车表里的所有数据
	public ArrayList getAllShopCart(){
		ArrayList rs = new ArrayList();
		try {

			Cursor cur = sqllite.query(TABLE_SHOP_CART, COLS, null, null, null, null, null);
			cur.moveToFirst();
			int count = cur.getCount();
			for (int i = 0; i < count; i++) {
				HashMap item = new HashMap();
				item.put("foodid", cur.getInt(0));
				item.put("foodname", cur.getString(1));
				item.put("foodprice", cur.getFloat(2));
				item.put("foodnum", cur.getInt(3));
				item.put("foodsumprices", cur.getFloat(4));
				item.put("foodimage", cur.getInt(5));
				rs.add(item);
				cur.moveToNext();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	//返回所有shopCart类型的ArrayList
	public ArrayList<ShopCart> getFoodFromCart(){
		ArrayList rs = new ArrayList();
		try {

			Cursor cur = sqllite.query(TABLE_SHOP_CART, COLS, null, null, null, null, null);
			cur.moveToFirst();
			int count = cur.getCount();
			for (int i = 0; i < count; i++) {
				ShopCart shopcart = new ShopCart();
				shopcart.setFoodId(cur.getInt(0));
				shopcart.setFoodName(cur.getString(1));
				shopcart.setFoodPrice(cur.getFloat(2));
				shopcart.setFoodNum(cur.getInt(3));
				shopcart.setSumPrices(cur.getFloat(4));
				rs.add(shopcart);
				cur.moveToNext();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	//修改购物车中所有的数据
	public int ModifyShopcart(int foodId,int foodnum,float foodsumprices ){
		ContentValues value = new ContentValues();
		value.put("foodnum", foodnum);
		value.put("foodsumprices",foodsumprices);
		return sqllite.update(TABLE_SHOP_CART, value, "foodid="+foodId, null);
	}
	//删除购物车中所有的数据
	public int deleteAllShopcart(){
		return sqllite.delete(TABLE_SHOP_CART, null, null);
	}
	//删除不需要的商品
	public int deleteFood(String whereparam){
		return sqllite.delete(TABLE_SHOP_CART, whereparam, null);
	}
	//查找需要的商品
	public ShopCart findFood(int foodid){
		ShopCart shopcart=new ShopCart();
		Cursor cur=sqllite.query(TABLE_SHOP_CART,COLS, "foodid="+foodid, null, null, null, null);
		cur.moveToFirst();
		shopcart.setFoodId(cur.getInt(0));
		shopcart.setFoodName(cur.getString(1));
		shopcart.setFoodPrice(cur.getFloat(2));
		shopcart.setFoodNum(cur.getInt(3));
		shopcart.setSumPrices(cur.getFloat(4));
		shopcart.setImage(cur.getInt(5));
		return shopcart;
	}

	/**
	 * 获取数据库连接
	 * @return
	 */
	public SQLiteDatabase getDB(){
		return this.sqllite;
	}
	/**
	 * 关闭数据库
	 */
	public void close(){
		this.sqllite.close();
	}
	class MySQLiteHelper extends SQLiteOpenHelper {

		public MySQLiteHelper(Context context, String name,
							  CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(create_table_shopcard);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists "+TABLE_SHOP_CART);
			onCreate(db);
		}
	}
}
