package com.example.administrator.yanshi.nui;

import com.example.administrator.yanshi.po.Product;

/**
 * Created by sunbo on 16/8/9.
 */
public class ProductType {
    private String name;
    private String value;

    public ProductType(String name,String value){
        this.name = name;
        this.value = value;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
