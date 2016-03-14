package fast.dev.platform.android.activity;

import com.tencent.bugly.crashreport.CrashReport;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.base.BaseActivity;

public class LoadingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_loading);
		
		CrashReport.setUserSceneTag(getContext(), 11887);
		
		CrashReport.setUserId("测试用户");
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				finish();
				startActivity(new Intent(getContext(), MainActivity.class));
			}
			
		}, 2000);
	}
	
}
