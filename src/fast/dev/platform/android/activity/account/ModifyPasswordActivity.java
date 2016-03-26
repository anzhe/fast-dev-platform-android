package fast.dev.platform.android.activity.account;

import com.tencent.bugly.crashreport.CrashReport;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.base.BaseActivity;

public class ModifyPasswordActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_password);
		
		CrashReport.setUserSceneTag(getContext(), 11889);
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
