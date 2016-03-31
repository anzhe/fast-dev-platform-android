package fast.dev.platform.android.activity.account;

import com.tencent.bugly.crashreport.CrashReport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.MainActivity;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.ToastUtils;

public class RegisterActivity extends BaseActivity {

	private EditText account;
	private EditText password1;
	private EditText password2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		CrashReport.setUserSceneTag(getContext(), 11888);
		
		setContentView(R.layout.activity_register);
		
		account = (EditText) findViewById(R.id.account);
		password1 = (EditText) findViewById(R.id.password1);
		password2 = (EditText) findViewById(R.id.password2);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.register_text));
	}

	public void register(View v) {
		String account_str = account.getText().toString();
		String password1_str = password1.getText().toString();
		String password2_str = password2.getText().toString();
		if (TextUtils.isEmpty(account_str)) {
			ToastUtils.showShort(getContext(), "请输入用户名/邮箱/手机号");
			account.requestFocus();
			return;
		}
		if (TextUtils.isEmpty(password1_str)) {
			ToastUtils.showShort(getContext(), "请输入密码");
			password1.requestFocus();
			return;
		}
		if (password1_str.length() < 6) {
			ToastUtils.showShort(getContext(), "密码长度不能小于6位");
			password1.requestFocus();
			password1.setSelection(password1.length());
			return;
		}
		if (!TextUtils.equals(password1_str, password2_str)) {
			ToastUtils.showShort(getContext(), "两次输入的密码不一致");
			password2.requestFocus();
			password2.setSelection(password2.length());
			return;
		}
		CommonUtils.hideSoftKeyboard(v);
		user_sp.edit().putBoolean("logged_on", true).apply();
		startActivity(new Intent(getContext(), MainActivity.class));
		finishActivity();
	}
	
}
