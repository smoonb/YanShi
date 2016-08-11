package com.example.administrator.yanshi.po;

/**
 * Created by Administrator on 2016/6/22 0022.
 */
public class Food {


        public int foodId;          //菜品编号
        public String foodName;     //菜品名称
        public float foodPrice;     //菜品价格
        public int foodType;        //菜品类型
        public String foodDescri;   //菜品描述
        public String foodImage;    //菜品图片——暂定为String类型用于存储图片名

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

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }

    public String getFoodDescri() {
        return foodDescri;
    }

    public void setFoodDescri(String foodDescri) {
        this.foodDescri = foodDescri;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }
}
