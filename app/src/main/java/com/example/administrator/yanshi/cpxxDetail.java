package com.example.administrator.yanshi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.yanshi.po.Product;
import com.example.administrator.yanshi.po.ShopCart;
import com.example.administrator.yanshi.util.DButil;
import com.example.administrator.yanshi.util.HttpUtils;

import java.math.BigDecimal;

public class cpxxDetail extends Activity {

    private Product selectedProduct;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        Intent intent = this.getIntent();

        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String type = intent.getStringExtra("type");
        String desc = intent.getStringExtra("desc");
        String image = intent.getStringExtra("image");
        float price = intent.getFloatExtra("price", 0.0f);


        selectedProduct = new Product();
        selectedProduct.setId(id);
        selectedProduct.setName(name);
        selectedProduct.setType(type);
        selectedProduct.setImage(image);
        selectedProduct.setPrice(price);
        selectedProduct.setDesc(desc);

        ImageView iv = (ImageView) findViewById(R.id.val_image);
        Button ib = (Button) findViewById(R.id.ib_zfd_toback);
        //TextView tvId = (TextView) findViewById(R.id.val_id);
        TextView tvName = (TextView) findViewById(R.id.val_name);
        TextView tvPrice = (TextView) findViewById(R.id.val_price);
        //TextView tvType = (TextView) findViewById(R.id.val_type);
        TextView tvMes = (TextView) findViewById(R.id.val_desc);
        Button addShopCardBtn = (Button)findViewById(R.id.addShopCart);
        //tvId.setText(""+id);
        tvName.setText(""+name);
        //tvType.setText(""+type);
        tvPrice.setText("单价: ¥"+price);
        tvMes.setText("商品简介: "+desc);
        HttpUtils.setPicBitmap(iv, image);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addShopCardBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final View zqz_dialog = LayoutInflater.from(cpxxDetail.this).inflate(R.layout.add_shop_cart_dialog, null);

                // 提示用户输入数量
                new AlertDialog.Builder(cpxxDetail.this).setTitle("订购").setView(zqz_dialog).setPositiveButton("确认", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,int which) {

                                EditText etnumber = (EditText) zqz_dialog.findViewById(R.id.zd_etnumber);
                                String etnumberStr = etnumber.getText().toString().trim();
                                if (!etnumberStr.equals("")
                                        && etnumberStr != null) {
                                    DButil db = new DButil(cpxxDetail.this);
                                    ShopCart shopcart = new ShopCart();
                                    shopcart.setFoodId(Integer.parseInt(selectedProduct.getId()));
                                    shopcart.setFoodName(selectedProduct.getName());
                                    shopcart.setFoodPrice(selectedProduct.getPrice());
                                    int foodnum = Integer.parseInt(etnumberStr);
                                    shopcart.setFoodNum(foodnum);
                                    // float sumprices = selectedFood.foodPrice
                                    // * foodnum;
                                    float sumprices = getTotalMoney(selectedProduct.getPrice(), foodnum);
                                    shopcart.setSumPrices(sumprices);
                                    db.addFood(shopcart);
                                    db.close();

                                    Intent intent = new Intent(cpxxDetail.this,ShopCartShowAll.class);
                                    Bundle extras = new Bundle();
                                    extras.putInt("foodId",Integer.parseInt(selectedProduct.getId()));
                                    extras.putFloat("foodPrice",selectedProduct.getPrice());
                                    extras.putInt("foodNumber", Integer.parseInt(etnumberStr));
                                    extras.putString("foodName",selectedProduct.getName());
                                    extras.putString("用户名", "");
                                    extras.putInt("用户Id", 0);
                                    intent.putExtras(extras);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(cpxxDetail.this,"请输入数量", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                        }
                }).create().show();
            }

        });


    }
    private float getTotalMoney(float price, int number) {

        BigDecimal bd_price = new BigDecimal(price);
        BigDecimal bd_number = new BigDecimal(number);

        return bd_price.multiply(bd_number).floatValue();
    }
}
