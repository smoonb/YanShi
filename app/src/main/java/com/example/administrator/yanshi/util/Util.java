package com.example.administrator.yanshi.util;

import org.xutils.DbManager;
import org.xutils.x;


/**
 * Created by Administrator on 2016/4/13 0013.
 */
public class Util extends MyApplication{
    //定义数据接口
    private static DbManager.DaoConfig daoConfig;
    //定义空构造

    public Util() {
    }
    //返回一个数据驱动经理
    public  static DbManager getDbManager(){
        daoConfig = new DbManager.DaoConfig().setDbName("db95306").setDbVersion(1);
        return x.getDb(daoConfig);

    }
    public static String getIpStr() {
        String sd="http://10.11.17.213:8080/yanshiServer/";
        //String sd="http://192.168.1.104:8080/yanshiServer/";
        return sd;
    }
}
