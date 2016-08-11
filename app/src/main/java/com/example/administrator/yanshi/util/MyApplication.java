package com.example.administrator.yanshi.util;

import android.app.Application;
import android.provider.Settings;

import org.xutils.x;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("sss");
        //将xu3进行实例化
        x.Ext.init(this);
        //设置日志
        x.Ext.setDebug(true);

    }
}
