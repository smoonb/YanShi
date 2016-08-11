package com.example.administrator.yanshi.po;

/**
 * Created by Administrator on 2016/6/22 0022.
 */
public class Product {


    public String id;          //商品编号
    public String name;     //商品名称
    public float price;     //商品价格
    public String type;        //商品类型
    public String desc;   //商品描述
    public String image;    //商品图片——暂定为String类型用于存储图片名

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
