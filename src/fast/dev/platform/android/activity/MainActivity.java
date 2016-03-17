package fast.dev.platform.android.activity;

import java.util.HashMap;

import com.amap.api.location.AMapLocation;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.base.BaseActionBarActivity;
import fast.dev.platform.android.application.MyApplication;
import fast.dev.platform.android.broadcastreceiver.NetworkChangeBroadcastReceiver;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.fragment.find.FindFragment;
import fast.dev.platform.android.fragment.home.HomeFragment;
import fast.dev.platform.android.fragment.my.MyFragment;
import fast.dev.platform.android.fragment.second.HotspotFragment;
import fast.dev.platform.android.location.GaodeRequestLocation;
import fast.dev.platform.android.location.GaodeRequestLocation.LocationCallback;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.NetworkUtils;
import fast.dev.platform.android.util.ToastUtils;

public class MainActivity extends BaseActionBarActivity {

	private LinearLayout view1, view2, view3, view4;
	private TextView home_tv1, home_tv2, home_tv3, home_tv4;
	private ImageView home_iv1, home_iv2, home_iv3, home_iv4;
	
	private Fragment currentFragment;
	
	private int back_clicked_count = 0;
	
	public static int currentPageIndex = 0;// 当前页面号
	private int oldPageIndex = 0;// 旧的页面号
	
	private AlertDialog networkDialog;
	
	private GaodeRequestLocation requestLocation;
	
	private NetworkChangeBroadcastReceiver networkChangeBroadcastReceiver = new NetworkChangeBroadcastReceiver(MainActivity.class);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * Bugly
		 */
		CrashReport.setUserSceneTag(getContext(), 6122);
		
		if (CommonUtils.isLawyer(user_sp)) {
			CrashReport.setUserId("[UserId: " + user_sp.getString("lawyerid", "") + ", Name: " + user_sp.getString("relName", "") + ", Phone: " + user_sp.getString("myPhone", "") + ", Role: 律师]");
		} else if (CommonUtils.isUser(user_sp)) {
			CrashReport.setUserId("[UserId: " + user_sp.getString("userid", "") + ", Name: " + user_sp.getString("relName", "") + ", Phone: " + user_sp.getString("myPhone", "") + ", Role: 普通用户]");
		} else {
			CrashReport.setUserId("用户未登录");
		}
		
		/**
		 * 云测Testin
		 */
		if (CommonUtils.isLawyer(user_sp)) {
			TestinAgent.setUserInfo("[UserId: " + user_sp.getString("lawyerid", "") + ", Name: " + user_sp.getString("relName", "") + ", Phone: " + user_sp.getString("myPhone", "") + ", Role: 律师]");
		} else if (CommonUtils.isUser(user_sp)) {
			TestinAgent.setUserInfo("[UserId: " + user_sp.getString("userid", "") + ", Name: " + user_sp.getString("relName", "") + ", Phone: " + user_sp.getString("myPhone", "") + ", Role: 普通用户]");
		} else {
			TestinAgent.setUserInfo("用户未登录");
		}
		TestinAgent.leaveBreadcrumb("主页面");
		
//		CrashReport.testJavaCrash();
		
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		registerReceiver(networkChangeBroadcastReceiver, filter);
		
		setContentView(R.layout.activity_main);
		
		initData();
		initViews();
		initListeners();
		
		switchPage(currentPageIndex);
		
		// 定位
		requestLocation = new GaodeRequestLocation(getContext(), false);
		requestLocation.location(new LocationCallback() {
			
			@Override
			public void callback(AMapLocation location, boolean located) {
				if (located) {
					location_sp.edit().putString("longitude", location.getLongitude() + "").apply();
					location_sp.edit().putString("latitude", location.getLatitude() + "").apply();
					location_sp.edit().putString("province", location.getProvince()).apply();
					location_sp.edit().putString("city", location.getCity()).apply();
					location_sp.edit().putString("district", location.getDistrict()).apply();

					if (CommonUtils.isLawyer(user_sp)) {
						HashMap<String, String> syncParams = new HashMap<String, String>();
						syncParams.put("lawerid", user_sp.getString("lawyerid", ""));
						syncParams.put("longitude", location_sp.getString("longitude", ""));
						syncParams.put("latitude", location_sp.getString("latitude", ""));
						syncLocation(syncParams);// 同步律师经纬度
					}
				}
			}
			
		});
	}

	@SuppressWarnings("deprecation")
	private void initViews() {
		view1 = (LinearLayout) findViewById(R.id.tv_guid1);
		view2 = (LinearLayout) findViewById(R.id.tv_guid2);
		view3 = (LinearLayout) findViewById(R.id.tv_guid3);
		view4 = (LinearLayout) findViewById(R.id.tv_guid4);
		home_tv1 = (TextView) findViewById(R.id.home_tv1);
		home_tv2 = (TextView) findViewById(R.id.home_tv2);
		if (CommonUtils.isLawyer(user_sp)) {
			home_tv2.setText("服 务");
		} else {
			home_tv2.setText("热 门");
		}
		home_tv3 = (TextView) findViewById(R.id.home_tv3);
		home_tv4 = (TextView) findViewById(R.id.home_tv4);
		home_iv1 = (ImageView) findViewById(R.id.home_iv1);
		home_iv2 = (ImageView) findViewById(R.id.home_iv2);
		home_iv3 = (ImageView) findViewById(R.id.home_iv3);
		home_iv4 = (ImageView) findViewById(R.id.home_iv4);

		home_iv1.setImageDrawable(getResources().getDrawable(R.drawable.hm_consuld));
		home_tv1.setTextColor(getResources().getColor(R.color.app_primary_color));
	}

	private void initListeners() {
		view1.setOnClickListener(this);
		view2.setOnClickListener(this);
		view3.setOnClickListener(this);
		view4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_guid1:
			if (currentPageIndex == 0) {
				return;
			}
			switchPage(0);
			break;
		case R.id.tv_guid2:
			if (currentPageIndex == 1) {
				return;
			}
			switchPage(1);
			break;
		case R.id.tv_guid3:
			if (currentPageIndex == 2) {
				return;
			}
			switchPage(2);
			break;
		case R.id.tv_guid4:
			if (currentPageIndex == 3) {
				return;
			}
			switchPage(3);
			break;
		}
	}

	private void initData() {
		
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		setIntent(intent);
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		if (oldPageIndex != currentPageIndex) {
			switchPage(currentPageIndex);
		}
		
		if (!NetworkUtils.isOpen(getContext())) {
			if (networkDialog == null) {
				networkDialog = new AlertDialog.Builder(getContext()).setTitle("网络设置提示")
						.setMessage("网络连接不可用，是否进行设置？").setPositiveButton("设置", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
								startActivity(intent);
							}
							
						}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								MyApplication.getInstance().exit();
							}
							
						}).create();
				networkDialog.setCanceledOnTouchOutside(false);
			}
			if (!networkDialog.isShowing()) {
				networkDialog.show();
			}
		} else {
			if (networkDialog != null && networkDialog.isShowing()) {
				networkDialog.dismiss();
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		unregisterReceiver(networkChangeBroadcastReceiver);
		
		requestLocation.cancelLocation();// 取消定位
	}
	
	/**
	 * 同步位置信息
	 * 
	 * @param syncParams
	 */
	private void syncLocation(HashMap<String, String> syncParams) {
		post(false, CommonData.REMOTE_REQUEST_URL_HTTP + "/if/gislog/save", syncParams, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				
			}
			
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				CrashReport.postCatchedException(error);

				TestinAgent.uploadException(getContext(), "调用服务器端接口报错", error);
			}
			
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			back_clicked_count++;
			if (back_clicked_count == 2) {
				currentPageIndex = 0;
				MyApplication.getInstance().exit();
			} else {
				ToastUtils.showShort(getContext(), "再按一次退出");
			}
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					back_clicked_count = 0;
				}
				
			}, 1500);
			return false;
		}

		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * 切换页面，从0开始
	 * 
	 * @param index
	 */
	@SuppressWarnings("deprecation")
	private void switchPage(int index) {
		switch (index) {
		case 0:
			currentPageIndex = 0;
			oldPageIndex = 0;
			currentFragment = new HomeFragment();
			
			home_tv1.setTextColor(getResources().getColor(R.color.app_primary_color));
			home_tv2.setTextColor(getResources().getColor(android.R.color.black));
			home_tv3.setTextColor(getResources().getColor(android.R.color.black));
			home_tv4.setTextColor(getResources().getColor(android.R.color.black));
			home_iv1.setImageDrawable(getResources().getDrawable(R.drawable.hm_consuld));
			home_iv2.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn2));
			home_iv3.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn3));
			home_iv4.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn4));
			break;
		case 1:
			currentPageIndex = 1;
			oldPageIndex = 1;
			
			currentFragment = new HotspotFragment();
			
			home_tv1.setTextColor(getResources().getColor(android.R.color.black));
			home_tv2.setTextColor(getResources().getColor(R.color.app_primary_color));
			home_tv3.setTextColor(getResources().getColor(android.R.color.black));
			home_tv4.setTextColor(getResources().getColor(android.R.color.black));
			home_iv1.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn1));
			home_iv2.setImageDrawable(getResources().getDrawable(R.drawable.hm_serviced));
			home_iv3.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn3));
			home_iv4.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn4));
			break;
		case 2:
			currentPageIndex = 2;
			oldPageIndex = 2;
			
			currentFragment = new FindFragment();
			
			home_tv1.setTextColor(getResources().getColor(android.R.color.black));
			home_tv2.setTextColor(getResources().getColor(android.R.color.black));
			home_tv3.setTextColor(getResources().getColor(R.color.app_primary_color));
			home_tv4.setTextColor(getResources().getColor(android.R.color.black));
			home_iv1.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn1));
			home_iv2.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn2));
			home_iv3.setImageDrawable(getResources().getDrawable(R.drawable.hm_founded));
			home_iv4.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn4));
			break;
		case 3:
			currentPageIndex = 3;
			oldPageIndex = 3;
			
			currentFragment = new MyFragment();
			
			home_tv1.setTextColor(getResources().getColor(android.R.color.black));
			home_tv2.setTextColor(getResources().getColor(android.R.color.black));
			home_tv3.setTextColor(getResources().getColor(android.R.color.black));
			home_tv4.setTextColor(getResources().getColor(R.color.app_primary_color));
			home_iv1.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn1));
			home_iv2.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn2));
			home_iv3.setImageDrawable(getResources().getDrawable(R.drawable.unfocus_btn3));
			home_iv4.setImageDrawable(getResources().getDrawable(R.drawable.hm_myd));
			break;
		}
		
		getSupportFragmentManager().beginTransaction().replace(R.id.main_container, currentFragment).commitAllowingStateLoss();
	}
	
}
