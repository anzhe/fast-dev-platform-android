package fast.dev.platform.android.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.MainActivity;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.util.ToastUtils;

public class LoginActivity extends BaseActivity {

	private EditText account;
	private EditText password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		
		account = (EditText) findViewById(R.id.account);
		password = (EditText) findViewById(R.id.password);
	}

	public void login(View v) {
		String account_str = account.getText().toString().trim();
		String password_str = password.getText().toString().trim();
		if (TextUtils.isEmpty(account_str)) {
			ToastUtils.showShort(getContext(), "请输入用户名/邮箱/手机号");
			return;
		}
		if (TextUtils.isEmpty(password_str)) {
			ToastUtils.showShort(getContext(), "请输入密码");
			return;
		}
		finishActivity();
		startActivity(new Intent(getContext(), MainActivity.class));
	}

	public void register(View v) {
		startActivity(new Intent(getContext(), RegisterActivity.class));
	}

	public void forgetPassword(View v) {
		startActivity(new Intent(getContext(), ForgetPasswordStep03Activity.class));
	}

	public void thirdPartyLogin(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.qq_login:

			break;
		case R.id.weixin_login:

			break;
		case R.id.weibo_login:

			break;
		}
	}

}
