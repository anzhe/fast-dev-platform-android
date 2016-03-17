package fast.dev.platform.android.activity;

import java.util.HashMap;

import com.activeandroid.query.Select;
import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.account.LoginActivity;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.business.AccountService;
import fast.dev.platform.android.business.AccountService.LoginCallback;
import fast.dev.platform.android.model.User;
import fast.dev.platform.android.util.BrowsingHistoryUtils;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.NetworkUtils;

public class LoadingActivity extends BaseActivity {

	private AccountService accountService;
	
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Bugly
		 */
		CrashReport.setUserSceneTag(getContext(), 11887);
		
		accountService = new AccountService(getContext(), volleyWrapper);
		
		if (CommonUtils.isLawyer(user_sp)) {
			CrashReport.setUserId("[UserId: " + user_sp.getString("lawyerid", "") + ", Name: " + user_sp.getString("relName", "") + ", Phone: " + user_sp.getString("myPhone", "") + ", Role: 律师]");
		} else if (CommonUtils.isUser(user_sp)) {
			CrashReport.setUserId("[UserId: " + user_sp.getString("userid", "") + ", Name: " + user_sp.getString("relName", "") + ", Phone: " + user_sp.getString("myPhone", "") + ", Role: 普通用户]");
		} else {
			CrashReport.setUserId("用户未登录");
		}
		
		BrowsingHistoryUtils.clearHistory();// 清空历史浏览记录
		
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
		TestinAgent.leaveBreadcrumb("加载页");
		
		setContentView(R.layout.activity_loading);
		
		progressDialog = CommonUtils.createProgressDialog(getContext(), "系统初始化中，请稍候……");
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		if (NetworkUtils.isOpen(getContext())) {
			long time = 0;
			if (sys_sp.getBoolean("dict_exist", false)) {
				time = 2000;
			}
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					checkLogin();
				}
				
			}, time);
		} else {
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					finish();
					startActivity(new Intent(getContext(), LoginActivity.class));
				}
				
			}, 2000);
		}
	}
	
	/**
	 * 判断是否已登录过，有则自动登录，没有则跳到登录界面
	 */
	private void checkLogin() {
		if (!sys_sp.getBoolean("been_booted", false)) {
			startActivity(new Intent(getContext(), BootActivity.class));
			finish();
		} else {
			if (user_sp.getBoolean("logged_on", false)) {
				finishActivity();
				startActivity(new Intent(getContext(), MainActivity.class));
				
//				final User user = new Select().from(User.class).where("Account=?", user_sp.getString("account", "")).executeSingle();
//				if (user != null) {
//					HashMap<String, String> loginParams = new HashMap<String, String>();
//					loginParams.put("account", user.account);
//					loginParams.put("password", user.password);
//					accountService.login(false, loginParams, user.role, new LoginCallback() {
//						
//						@Override
//						public void doCallback() {
//							finish();
//							startActivity(new Intent(getContext(), MainActivity.class));
//						}
//						
//					});
//				} else {// 用户数据被删除则跳转到登录页面重新登录
//					finish();
//					startActivity(new Intent(getContext(), LoginActivity.class));
//				}
			} else {
				finish();
				startActivity(new Intent(getContext(), LoginActivity.class));
			}
		}
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
		
//		ImageUtils.releaseImageViewResouce(imageView);
	}
	
}
