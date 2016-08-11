package com.example.administrator.yanshi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.administrator.yanshi.po.TblUser;
import com.example.administrator.yanshi.service.UserService;
import com.example.administrator.yanshi.service.impl.UserServiceImpl;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import com.alibaba.fastjson.JSON;

//import com.example.administrator.zhang.R;

public class FrmTab0 extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_tab0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_frm_tab0, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {
        //0定义字段
        private View rootView;
        private EditText userNameLocal;
        private EditText userAgeLocal;
        private EditText userNameNet;
        private EditText userAgeNet;
        private Button btnUserInsertLocal;
        private Button btnUserDeleteLocal;
        private Button btnUserUpdateLocal;
        private Button btnUserSelectOneLocal;
        private Button btnUserSelectAllLocal;

        private Button btnUserInsertNetWithP;
        private Button btnUserInsertNetWithJ;
        private Button btnUserInsertNetReturn01;
        private Button btnUserInsertNetReturnJ;

        private ListView lvUserLocal;
        private ListView lvUserNet;
        private UserService userService;
        private TblUser tblUser;
        private  String userNameTxt="";
        private  String userAgeTxt="";
        private ArrayAdapter<String> arrayAdapter;
        private List<TblUser> userList;
        private List<String> userNameList;
        private  List<Integer> userAgeList;
        //网络有关的部分
        //s使用模拟器 url=“http://10.0.2.2:8080/mulu”；
        //使用真机  url=“http://ip:8080/mulu”；
        private String URL0="http://10.0.2.2:8080/";
        private String URL1="http://172.27.35.1:8080/Temp1/UserServlet?";
        private RequestParams requestParams;
        //withJ使用
        private  TblUser[] tblUsers;
        private  String jsonStr="";
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_frm_tab0, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            //判别当前所传递的数字
            switch (getArguments().getInt(ARG_SECTION_NUMBER))
            {
                case 1:
                    rootView=inflater.inflate(R.layout.fragment_frm_tab0a, container, false);
                    m_0a();
                    break;
                case 2:
                    rootView=inflater.inflate(R.layout.fragment_frm_tab0b, container, false);
                    m_0b();
                    break;
                case 3:
                    rootView=inflater.inflate(R.layout.fragment_frm_tab0c, container, false);
                    break;
            }
            return rootView;
        }

        private void m_0b() {
            //1实例化
            m_0b_1();
            //2监听器
            m_0b_2();
        }

        private void m_0b_2() {
            btnUserInsertNetWithP.setOnClickListener(this);
            btnUserInsertNetWithJ.setOnClickListener(this);
            btnUserInsertNetReturn01.setOnClickListener(this);
            btnUserInsertNetReturnJ.setOnClickListener(this);

        }

        private void m_0b_1() {
            btnUserInsertNetWithP= (Button) rootView.findViewById(R.id.btnUserInsertNetWithP);
            btnUserInsertNetWithJ=(Button)rootView.findViewById(R.id.btnUserInsertNetWithJ);
            btnUserInsertNetReturn01=(Button)rootView.findViewById(R.id.btnUserInsertNetReturn01);
            btnUserInsertNetReturnJ=(Button)rootView.findViewById(R.id.btnUserInsertNetReturnJ);
            lvUserNet= (ListView) rootView.findViewById(R.id.lvUserNet);
            userNameNet=(EditText) rootView.findViewById(R.id.userNameNet);
            userAgeNet=(EditText) rootView.findViewById(R.id.userAgeNet);
        }

        private void m_0a() {
            //1实例化
            m_0a_1();
            //2监听器
            m_0a_2();

        }

        private void m_0a_2() {
            btnUserInsertLocal.setOnClickListener(this);
            btnUserDeleteLocal.setOnClickListener(this);
            btnUserUpdateLocal.setOnClickListener(this);
            btnUserSelectOneLocal.setOnClickListener(this);
            btnUserSelectAllLocal.setOnClickListener(this);

        }

        private void m_0a_1() {
            userNameLocal= (EditText) rootView.findViewById(R.id.userNameLocal);
            userAgeLocal= (EditText) rootView.findViewById(R.id.userAgeLocal);
            btnUserInsertLocal= (Button) rootView.findViewById(R.id.btnUserInsertLocal);
            btnUserDeleteLocal= (Button) rootView.findViewById(R.id.btnUserDeleteLocal);
            btnUserUpdateLocal= (Button) rootView.findViewById(R.id.btnUserUpdateLocal);
            btnUserSelectOneLocal= (Button) rootView.findViewById(R.id.btnUserSelectOneLocal);
            btnUserSelectAllLocal= (Button) rootView.findViewById(R.id.btnUserSelectAllLocal);
            lvUserLocal= (ListView) rootView.findViewById(R.id.lvUserLocal);
            userService=new UserServiceImpl();

        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btnUserInsertLocal:
                    m_btnUserInsertLocal();
                    break;
                case R.id.btnUserDeleteLocal:
                    m_btnUserDeleteLocal();
                    break;
                case R.id.btnUserUpdateLocal:
                    m_btnUserUpdateLocal();
                    break;
                case R.id.btnUserSelectOneLocal:
                    m_btnUserSelectOneLocal();
                    break;
                case R.id.btnUserSelectAllLocal:
                    m_btnUserSelectAllLocal();
                    break;
                case R.id.btnUserInsertNetWithP:
                    m_btnUserInsertNetWithP();
                    break;
                case R.id.btnUserInsertNetWithJ:
                    m_btnUserInsertNetWithJ();
                    break;
                case R.id.btnUserInsertNetReturn01:
                    m_btnUserInsertNetReturn01();
                    break;
                case R.id.btnUserInsertNetReturnJ:
                    m_btnUserInsertNetReturnJ();
                    break;
            }
        }

        private void m_btnUserInsertNetReturnJ() {
            //向网路提交
            requestParams=new RequestParams(URL1);
            requestParams.addQueryStringParameter("requestType","returnJ");
            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    System.out.println(s.trim());
                    userList = JSONArray.parseArray(s.toString(),TblUser.class);
                    for (TblUser u:userList ) {
                        System.out.println(u);

                    }

                }

                @Override
                public void onError(Throwable throwable, boolean b) {

                }

                @Override
                public void onCancelled(CancelledException e) {

                }

                @Override
                public void onFinished() {

                }
            });
        }

        private void m_btnUserInsertNetReturn01() {
            //向网路提交
            requestParams=new RequestParams(URL1);
            requestParams.addQueryStringParameter("requestType","return01");
            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    System.out.println(s.trim());
                }

                @Override
                public void onError(Throwable throwable, boolean b) {

                }

                @Override
                public void onCancelled(CancelledException e) {

                }

                @Override
                public void onFinished() {

                }
            });
        }

        private void m_btnUserInsertNetWithJ() {
            tblUsers=new TblUser[2];
            tblUsers[0]=new TblUser("u0",0);
            tblUsers[0].setId(0);
            tblUsers[1]=new TblUser("u1",1);
            tblUsers[1].setId(1);
            //由数组变成集合
            userList= Arrays.asList(tblUsers);
            //由集合产生json串
            jsonStr=JSON.toJSONString(userList);
            //向网路提交
            requestParams=new RequestParams(URL1);
            requestParams.addQueryStringParameter("requestType","withJ");
            requestParams.addQueryStringParameter("jsonStr",jsonStr);
            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    System.out.println(s.trim());
                }

                @Override
                public void onError(Throwable throwable, boolean b) {

                }

                @Override
                public void onCancelled(CancelledException e) {

                }

                @Override
                public void onFinished() {

                }
            });


        }

        private void m_btnUserInsertNetWithP() {
            userNameTxt=userNameNet.getText().toString().trim();
            userAgeTxt=userAgeNet.getText().toString().trim();
            requestParams=new RequestParams(URL1);
            requestParams.addQueryStringParameter("requestType","withP");
            requestParams.addQueryStringParameter("userName",userNameTxt);
            requestParams.addQueryStringParameter("userAge",userAgeTxt);
            //发起请求
            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    System.out.println(s.trim());

                }

                @Override
                //网路不通
                public void onError(Throwable throwable, boolean b) {

                }

                @Override
                public void onCancelled(CancelledException e) {

                }

                @Override
                public void onFinished() {

                }
            });
        }

        private void m_btnUserSelectAllLocal() {
            userList=userService.userSelectAllLocal();
            userNameList=new ArrayList<>();
            userAgeList=new ArrayList<>();
            //应该判别userlist是否为空
            for(TblUser u:userList)
            {
                userNameList.add(u.getUserName());


            }

            arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,userNameList);
            lvUserLocal.setAdapter(arrayAdapter);
        }

        private void m_btnUserSelectOneLocal() {
            userNameTxt=userNameLocal.getText().toString().trim();
            userAgeTxt=userAgeLocal.getText().toString().trim();

            try
            {

                //应该进行填写判断  省略
                tblUser=new TblUser(userNameTxt,Integer.parseInt(userAgeTxt));
                if(userService.userSelectOneLocal(tblUser)>0)
                {
                    Toast.makeText(getActivity(),"本单成功",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getActivity(),"本单失败",Toast.LENGTH_SHORT).show();

                }
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(),"年龄必须为整数",Toast.LENGTH_SHORT).show();
            }
        }


        private void m_btnUserUpdateLocal() {
            //获取用户名和用户年龄
            //老用户名新年领
            userNameTxt=userNameLocal.getText().toString().trim();
            userAgeTxt=userAgeLocal.getText().toString().trim();

            try
            {

                //应该进行填写判断  省略
                tblUser=new TblUser(userNameTxt,Integer.parseInt(userAgeTxt));
                if(userService.userUpdateLocal(tblUser)>0)
                {
                    Toast.makeText(getActivity(),"本改成功",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(),"本改失败",Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(),"年龄必须为整数",Toast.LENGTH_SHORT).show();
            }

        }
        private void m_btnUserDeleteLocal() {

            //获取用户名和用户年龄
            userNameTxt=userNameLocal.getText().toString().trim();
            userAgeTxt=userAgeLocal.getText().toString().trim();
//            if(userNameTxt.equals(""))
//            {
//                Toast.makeText(getActivity(),"用户名不能为空",Toast.LENGTH_SHORT).show();
//            }
//            if(userAgeTxt.equals(""))
//            {
//                Toast.makeText(getActivity(),"年龄不能为空",Toast.LENGTH_SHORT).show();
//            }
            try
            {

                //应该进行填写判断  省略
                tblUser=new TblUser(userNameTxt,Integer.parseInt(userAgeTxt));
                if(userService.userDeleteLocal(tblUser)>0)
                {
                    Toast.makeText(getActivity(),"本删成功",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(),"本删失败",Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(),"年龄必须为整数",Toast.LENGTH_SHORT).show();
            }
        }

        private void m_btnUserInsertLocal() {
            //获取用户名和用户年龄
            userNameTxt=userNameLocal.getText().toString().trim();
            userAgeTxt=userAgeLocal.getText().toString().trim();
            if(userNameTxt.equals(""))
            {
                Toast.makeText(getActivity(),"用户名不能为空",Toast.LENGTH_SHORT).show();
            }
            if(userAgeTxt.equals(""))
            {
                Toast.makeText(getActivity(),"年龄不能为空",Toast.LENGTH_SHORT).show();
            }
            try
            {

                //应该进行填写判断  省略
                tblUser=new TblUser(userNameTxt,Integer.parseInt(userAgeTxt));
                if(userService.userInsertLocal(tblUser)>0)
                {
                    Toast.makeText(getActivity(),"本增成功",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(),"本增失败",Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(),"年龄必须为整数",Toast.LENGTH_SHORT).show();
            }


        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
