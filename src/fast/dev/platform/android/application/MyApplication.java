package fast.dev.platform.android.application;

import java.util.LinkedList;
import java.util.List;

import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;
import com.umeng.socialize.common.SocializeConstants;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.constant.Constants;
import fast.dev.platform.android.util.CommonUtils;

public class MyApplication extends Application {

	private static MyApplication app;
	private List<Activity> activities = new LinkedList<Activity>();
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		app = this;
		
		/**
		 * 友盟
		 */
		SocializeConstants.APPKEY = CommonData.UMENG_APP_KEY;
		
		/**
		 * Bugly
		 */
		CrashReport.initCrashReport(getApplicationContext(), Constants.BUGLY_APP_ID, true);
		
		/**
		 * 云测Testin
		 */
		TestinAgent.init(this, CommonData.TESTIN_APP_KEY);
		TestinAgent.setLocalDebug(true);
	}
	
	public static MyApplication getInstance() {
		return app;
	}

	public SharedPreferences user_sp() {
		return CommonUtils.user_sp(app);
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public void removeLatestActivity() {
		try {
			activities.remove(activities.size() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exit() {
		for (Activity activity : activities) {
			activity.finish();
		}
	}

	/**
	 * 判断是否为主线程
	 * 
	 * @return
	 */
	public boolean isMainProcess() {
		ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
		List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		String mainProcessName = getPackageName();
		int myPid = android.os.Process.myPid();
		for (RunningAppProcessInfo info : processInfos) {
			if (info.pid == myPid && mainProcessName.equals(info.processName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 指定弹出num个数的Activity
	 * 
	 * @param num
	 */
	public void popupLatestActivity(int num) {
		if (!activities.isEmpty() && activities.size() >= num) {
			Activity activity = null;
			for (int i = 1; i <= num; i++) {
				try {
					activity = activities.get(activities.size() - i);
				} catch (Exception e) {
					e.printStackTrace();
				}
				activity.finish();
			}
		}
	}
	
}
