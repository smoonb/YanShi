package com.example.administrator.yanshi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.yanshi.nui.NewsAdapter;
import com.example.administrator.yanshi.po.News;
import com.example.administrator.yanshi.util.HttpUtils;
import com.example.administrator.yanshi.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class FrmMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemClickListener {
    private Button ctyy,qtyy,wscs;
    private RequestParams requestParams;
    private ListView lvNews;
    private NewsAdapter adapter;
    private List<News> newsList;

    //此处需要修改为自己的服务器地址
    //public static final String GET_NEWS_URL = "http://172.31.28.208/NewsDemo/getNewsJSON.php";
 //   public Resources sss=this.getResources();
  //  String sd=(String)sss.getText(R.string.common_ip);
 //   static String ss=sd+ "http://10.11.24.16:8080/DestineFoodServer/GetAllFoodServlet";
  //  public static final String GET_NEWS_URL =ss;
     //public static final String GET_NEWS_URL = "http://10.11.24.16:8080/DestineFoodServer/GetAllFoodServlet";
       public static final String GET_NEWS_URL = Util.getIpStr()+ "GetAllDishServlet";
   //  public static final String GET_NEWS_URL = "http://10.11.24.16:8080/DestineFoodServer/GetAllFoodServlet";
    private Handler getNewsHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String title = object.getString("cp");
                    String desc = object.getString("bz");
                    String time = object.getString("id");
                    String content_url = object.getString("type");
                    String pic_url = HttpUtils.URL+object.getString("image");
                    newsList.add(new News(title, desc, time, content_url, pic_url));
                }
                adapter.notifyDataSetChanged();//notifyDataSetChanged方法强制listview调用getView来刷新每个Item的内容。
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        lvNews = (ListView) findViewById(R.id.lvNews);
        newsList = new ArrayList<News>();
        adapter = new NewsAdapter(this, newsList);
        lvNews.setAdapter(adapter);
        lvNews.setOnItemClickListener(this);
        HttpUtils.getNewsJSON(GET_NEWS_URL, getNewsHandler);


        //职工餐厅预约
        ctyy=(Button)findViewById(R.id.dh1);
        qtyy=(Button)findViewById(R.id.dh2);
        wscs=(Button)findViewById(R.id.dh3);
        ctyy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(FrmMain.this, ctyy.class);
                                        startActivity(intent);
                                    }
                                }
        );
        qtyy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(FrmMain.this, gtyy.class);
                                        startActivity(intent);
                                    }
                                }
        );
        wscs.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(FrmMain.this, WscsActivity.class);
                                        startActivity(intent);
                                    }
                                }
        );

/*
ydzc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String url = "http://10.11.24.16:8080/DestineFoodServer/RegisterServlet";
        //向网路提交
        requestParams=new RequestParams(url);
        requestParams.addQueryStringParameter("username","yuding");
        requestParams.addQueryStringParameter("pwd","1");
        requestParams.addQueryStringParameter("cfpwd","1");

        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String s) {

                     *//*   System.out.println(s.trim());
                        Intent intent = new Intent(FrmMain.this, FrmMain.class);
                        startActivity(intent);
                        finish();*//*
                System.out.println(s);
                Toast.makeText(FrmMain.this, "欢迎预定早餐成功!", Toast.LENGTH_LONG).show();
                ydzc.setBackgroundColor(Color.parseColor("#666666"));
                ydzc.setEnabled(false);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Toast.makeText(FrmMain.this, "预定早餐失败!",
                        Toast.LENGTH_LONG).show();
                       *//* Intent intent = new Intent(FrmLogin.this, FrmLogin.class);
                        startActivity(intent);
                        finish();*//*
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
});

        ydwc.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String url = "http://10.11.24.16:8080/DestineFoodServer/RegisterServlet";
                //向网路提交
                requestParams=new RequestParams(url);
                requestParams.addQueryStringParameter("username","yuding");
                requestParams.addQueryStringParameter("pwd","1");
                requestParams.addQueryStringParameter("cfpwd","1");

                x.http().post(requestParams, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String s) {

                     *//*   System.out.println(s.trim());
                        Intent intent = new Intent(FrmMain.this, FrmMain.class);
                        startActivity(intent);
                        finish();*//*
                        System.out.println(s);
                        Toast.makeText(FrmMain.this, "欢迎预定午餐成功!", Toast.LENGTH_LONG).show();
                        ydwc.setBackgroundColor(Color.parseColor("#666666"));
                        ydzc.setEnabled(false);
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        Toast.makeText(FrmMain.this, "预定午餐失败!",
                                Toast.LENGTH_LONG).show();
                       *//* Intent intent = new Intent(FrmLogin.this, FrmLogin.class);
                        startActivity(intent);
                        finish();*//*
                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });


            }
        });*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        News news = newsList.get(position);

        String url =  Util.getIpStr()+"RegisterServlet";
        //向网路提交
        requestParams=new RequestParams(url);
        requestParams.addQueryStringParameter("username",news.getTitle());
        requestParams.addQueryStringParameter("pwd","1");
        requestParams.addQueryStringParameter("cfpwd","1");

         x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String s) {

              /*  Intent intent = new Intent(FrmMain.this, FrmMain.class);
                startActivity(intent);
                finish();*/
                System.out.println(s);
                Toast.makeText(FrmMain.this, "欢迎预定"+""+"成功!", Toast.LENGTH_LONG).show();
           //     ydzc.setBackgroundColor(Color.parseColor("#666666"));
           //     ydzc.setEnabled(false);


            }

            @Override
            public void onError(Throwable throwable, boolean b) {
             //   Toast.makeText(FrmMain.this, "预定早餐失败!",
                   //     Toast.LENGTH_LONG).show();
               /* Intent intent = new Intent(FrmLogin.this, FrmLogin.class);
                startActivity(intent);
                finish(); */
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
      /*  Intent intent = new Intent(this, BrowseNewsActivity.class);
        intent.putExtra("content_url", news.getDesc());
        startActivity(intent);*/
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.frm_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id)
        {
            case R.id.hdbj0:
//                跳动到滑动布局
                FrmMain.this.startActivity(new Intent(FrmMain.this,ShopCartShowAll.class));
                break;
            case R.id.hdbj1:
//                跳动到滑动布局
                FrmMain.this.startActivity(new Intent(FrmMain.this,FrmTab0.class));
                break;
            case R.id.hdbj2:
//                跳动到滑动布局
                FrmMain.this.startActivity(new Intent(FrmMain.this,FrmTab1.class));
                break;
            case R.id.hdbj3:
//                跳动到滑动布局
                FrmMain.this.startActivity(new Intent(FrmMain.this,FrmTab2.class));
                break;
        }
       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
