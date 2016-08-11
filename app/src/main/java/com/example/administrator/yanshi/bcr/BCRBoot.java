package com.example.administrator.yanshi.bcr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.administrator.yanshi.nui.frmWifiService;

public class BCRBoot extends BroadcastReceiver {
    public BCRBoot() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //
        System.out.println("系统广播启动!");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context.getApplicationContext(),frmWifiService.class);
        context.startService(intent);//开启服务
    }
}
