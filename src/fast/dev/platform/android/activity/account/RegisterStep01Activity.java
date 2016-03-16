package fast.dev.platform.android.activity.account;

import com.tencent.bugly.crashreport.CrashReport;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.InputValidateUtils;
import fast.dev.platform.android.util.ToastUtils;

public class RegisterStep01Activity extends BaseActivity {

	private EditText phone;
	private Button btn_captcha;
	private RadioButton user_rb;
	
	private Intent intent;
	private String thirdparty;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Bugly
		 */
		CrashReport.setUserSceneTag(getContext(), 6149);
		
		setContentView(R.layout.activity_register_step_01);

		initData();
		initViews();
		initListeners();
	}

	private void initViews() {
		phone = (EditText) findViewById(R.id.phone);
		btn_captcha = (Button) findViewById(R.id.btn_captcha);
		user_rb = (RadioButton) findViewById(R.id.user_rb);
	}

	private void initData() {
		intent = getIntent();
		thirdparty = intent.getStringExtra("thirdparty");
	}

	private void initListeners() {
		btn_captcha.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		
		switch (v.getId()) {
		case R.id.btn_captcha:
			if (TextUtils.isEmpty(phone.getText().toString())) {
				ToastUtils.showShort(getContext(), "请输入手机号码");
				return;
			}
			if (!InputValidateUtils.isMobileNo(phone.getText().toString())) {
				ToastUtils.showShort(getContext(), "不是可用的手机号码");
				return;
			}
			CommonUtils.hideSoftKeyboard(v);
			Intent intent = new Intent(getContext(), UserAgreementActivity.class);
			intent.putExtra("thirdparty", thirdparty);
			intent.putExtra("phone", phone.getText().toString());
			if (user_rb.isChecked()) {
				intent.putExtra("role", "user");
			} else {
				intent.putExtra("role", "lawyer");
			}
			startActivity(intent);
			break;
		}
	}
	
}
