package com.example.administrator.yanshi.po;

//定义一个购物车的类，封装购物车的信息
public class ShopCart {
	private int foodId;// 商品id
	private String foodName;// 商品名称
	private float foodPrice;// 商品单价
	private int foodNum;// 商品数量
	private float sumPrices;// 商品金额
	private int image;// 商品图片
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public float getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}
	public int getFoodNum() {
		return foodNum;
	}
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	public float getSumPrices() {
		return sumPrices;
	}
	public void setSumPrices(float sumPrices) {
		this.sumPrices = sumPrices;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}

	public String toString() {
		return "ShopCart [foodId=" + foodId + ", foodName=" + foodName
				+ ", foodNum=" + foodNum + ", foodPrice=" + foodPrice
				+ ", image=" + image + ", sumPrices=" + sumPrices + "]";
	}
}
