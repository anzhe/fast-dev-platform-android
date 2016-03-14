package fast.dev.platform.android.app;

import com.tencent.bugly.crashreport.CrashReport;

import android.app.Application;
import fast.dev.platform.android.constant.Constants;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		CrashReport.initCrashReport(getApplicationContext(), Constants.BUGLY_APP_ID, true);
	}
	
}
