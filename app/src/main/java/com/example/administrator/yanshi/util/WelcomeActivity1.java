package com.example.administrator.yanshi.util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.yanshi.FrmLogin;
import com.example.administrator.yanshi.R;

public class WelcomeActivity1 extends Activity {
/*	@InjectView(R.id.welcome_start_order)
	private TextView start;*/
     private TextView start;
	/*@SuppressLint("NewApi")*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
	///	requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		start=(TextView)findViewById(R.id.welcome_start_order);
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//System.out.println("Hello zhang!");
				//实现页面的跳转
				WelcomeActivity1.this.startActivity(new Intent(WelcomeActivity1.this,FrmLogin.class));
				//销毁当前闪屏页
				WelcomeActivity1.this.finish();
			}
		});

	}

}
