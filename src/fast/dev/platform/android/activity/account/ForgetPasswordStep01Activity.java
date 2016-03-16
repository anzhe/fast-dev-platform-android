package fast.dev.platform.android.activity.account;

import java.util.HashMap;

import com.android.volley.Response.Listener;
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
import fast.dev.platform.android.bean.business.GetCaptchaBusinessBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.InputValidateUtils;
import fast.dev.platform.android.util.ToastUtils;

public class ForgetPasswordStep01Activity extends BaseActivity {

	private EditText phone;
	private Button btn_captcha;
	private RadioButton user_rb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Bugly
		 */
		CrashReport.setUserSceneTag(getContext(), 6150);
		
		setContentView(R.layout.activity_forget_password_step_01);

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
			getCaptcha();
			break;
		}
	}
	
	/**
	 * 获取验证码
	 */
	private void getCaptcha() {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("myphone", phone.getText().toString());
		requestParams.put("status", "1");
		if (user_rb.isChecked()) {
			requestParams.put("idcode", "1");
		} else {
			requestParams.put("idcode", "0");
		}
		post(true, CommonData.REMOTE_REQUEST_URL_HTTP + "/if/common/sms", requestParams, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				GetCaptchaBusinessBean getCaptchaBusinessBean = gson.fromJson(response, GetCaptchaBusinessBean.class);
				if (handleRequestResult(getCaptchaBusinessBean)) {
					Intent intent = new Intent(getContext(), ForgetPasswordStep02Activity.class);
					intent.putExtra("phone", phone.getText().toString());
					if (user_rb.isChecked()) {
						intent.putExtra("role", "user");
					} else {
						intent.putExtra("role", "lawyer");
					}
					startActivity(intent);
				}
				
				CommonUtils.dismissProgressDialog();
			}

		}, VolleyWrapper.MAX_RETRY_NUM);
	}
	
}
