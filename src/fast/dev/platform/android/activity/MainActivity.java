package fast.dev.platform.android.activity;

import com.tencent.bugly.crashreport.CrashReport;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.base.BaseActivity;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		CrashReport.setUserSceneTag(getContext(), 11886);
		
//		CrashReport.testJavaCrash();
		
		try {
			throw new RuntimeException("手动捕获异常测试");
		} catch (Exception e) {
			CrashReport.postCatchedException(e);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
