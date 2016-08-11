package com.example.administrator.yanshi.util;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by Administrator on 2016/2/3.
 * Wifi辅助类
 */
public class WifiAdmin {
    //0定义组件
    private WifiManager wifiManager;
    private WifiInfo wifiInfo;
    //网络连接
    private List<ScanResult> wifiList;
    private List<WifiConfiguration> wifiConfigList;
    private WifiManager.WifiLock wifiLock;

    //1定义构造函数
    public WifiAdmin(Context context) {
        wifiManager= (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiInfo=wifiManager.getConnectionInfo();
    }
    //2打开Wifi
    public void openWifi(){
        if (!wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);
        }
    }
    //3关闭Wifi
    public void closeWifi() {
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }
    //4检查当前wifi的状态
//    WIFI_STATE_DISABLED : WIFI网卡不可用（1）
//    WIFI_STATE_DISABLING : WIFI网卡正在关闭（0）
//    WIFI_STATE_ENABLED : WIFI网卡可用（3）
//    WIFI_STATE_ENABLING : WIFI网正在打开（2）
//    WIFI_STATE_UNKNOWN  : 未知网卡状态
    public int checkState() {
        return wifiManager.getWifiState();
    }
    //5创建wifiLock
    public void createWifiLock(){
        wifiLock=wifiManager.createWifiLock("laozhang");
    }
    //6锁定wifilock
    //应用程序想在屏幕被关掉后继续使用WiFi则可以调用 acquireWifiLock来锁住WiFi，该操作会阻止WiFi进入睡眠状态
    public void acquireWifiLock(){
        wifiLock.acquire();
    }
    //7解锁wifi
    public void releaseWifiLock()
    {
        if(wifiLock.isHeld()){
            wifiLock.release();
        }
    }
    //8得到配置好的网络
    public List<WifiConfiguration> getWifiConfigList(){
        return wifiConfigList;
    }
    //9与指定的配置好的网络进行连接
    public void connectionConfiguration(int index)
    {
        if(index>wifiConfigList.size()){
            return ;
        }
        //制定配置好的网络
        wifiManager.enableNetwork
                (wifiConfigList.get(index).networkId,true);
    }
    //10扫描
    public void startScan(){
        wifiManager.startScan();
        //获取扫描结果
        wifiList=wifiManager.getScanResults();
        //得到配置好的网络连接
        wifiConfigList=wifiManager.getConfiguredNetworks();
    }
    //11得到网络列表
    public List<ScanResult> getWifiList(){
        return wifiList;
    }
    //12查看扫描结果
    public StringBuffer lookUpScan(){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<wifiList.size();i++){
            sb.append("Index_" + new Integer(i + 1).toString() + ":");
            // 将ScanResult信息转换成一个字符串包
            // 其中把包括：BSSID、SSID、capabilities、frequency、level
            sb.append((wifiList.get(i)).toString()).append("\n");
        }
        return sb;
    }
    //获取网卡mac地址
    //当mac地址更换的时候，一线中就可以2人同时连接一个无线路由器
    public String getMacAddress(){
        return (wifiInfo==null)?"NULL":wifiInfo.getMacAddress();
    }
    public String getBSSID(){
        return (wifiInfo==null)?"NULL":wifiInfo.getBSSID();
    }
    public int getIpAddress(){
        return (wifiInfo==null)?0:wifiInfo.getIpAddress();
    }
    //得到连接的ID
    public int getNetWordId(){
        return (wifiInfo==null)?0:wifiInfo.getNetworkId();
    }
    //得到wifiInfo的所有信息
    public String getWifiInfo(){
        return (wifiInfo==null)?"NULL":wifiInfo.toString();
    }
    //添加一个网络并连接
    public void addNetWork(WifiConfiguration configuration){
        int wcgId=wifiManager.addNetwork(configuration);
        wifiManager.enableNetwork(wcgId, true);
    }
    //断开指定ID的网络
    public void disConnectionWifi(int netId){
        wifiManager.disableNetwork(netId);
        wifiManager.disconnect();
    }
}
