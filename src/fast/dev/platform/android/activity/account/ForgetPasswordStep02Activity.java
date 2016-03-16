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
import android.widget.TextView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.bean.business.GetCaptchaBusinessBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.ToastUtils;

public class ForgetPasswordStep02Activity extends BaseActivity {

	private EditText captcha;
	private Button btn_next;
	private TextView phone_tv;
	private View reget_captcha;
	private TextView reget_captcha_text;
	private TextView countdown;
	
	private Intent intent;
	private String phone;
	private String role;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Bugly
		 */
		CrashReport.setUserSceneTag(getContext(), 6150);
		
		setContentView(R.layout.activity_forget_password_step_02);

		initData();
		initViews();
		initListeners();
	}

	private void initViews() {
		captcha = (EditText) findViewById(R.id.captcha);
		btn_next = (Button) findViewById(R.id.btn_next);
		phone_tv = (TextView) findViewById(R.id.phone_tv);
		phone_tv.setText(CommonUtils.formatPhone(phone));
		reget_captcha = findViewById(R.id.reget_captcha);
		reget_captcha_text = (TextView) findViewById(R.id.reget_captcha_text);
		countdown = (TextView) findViewById(R.id.countdown);
		countdown();
	}

	private void initData() {
		intent = getIntent();
		phone = intent.getStringExtra("phone");
		role = intent.getStringExtra("role");
	}

	private void initListeners() {
		btn_next.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);

		switch (v.getId()) {
		case R.id.btn_next:
			if (TextUtils.isEmpty(captcha.getText().toString())) {
				ToastUtils.showShort(getContext(), "请输入验证码");
				return;
			}
			verifyCaptcha();
			break;
		case R.id.reget_captcha:
			regetCaptcha();
			break;
		}
	}

	/**
	 * 校验验证码
	 */
	private void verifyCaptcha() {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("myphone", phone);
		requestParams.put("smsnum", captcha.getText().toString());
		post(true, CommonData.REMOTE_REQUEST_URL_HTTP + "/if/common/sms", requestParams, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				GetCaptchaBusinessBean getCaptchaBusinessBean = gson.fromJson(response, GetCaptchaBusinessBean.class);
				if (handleRequestResult(getCaptchaBusinessBean)) {
					Intent intent = new Intent(getContext(), ForgetPasswordStep03Activity.class);
					intent.putExtra("phone", phone);
					intent.putExtra("role", role);
					startActivity(intent);
				}
				
				CommonUtils.dismissProgressDialog();
			}

		}, VolleyWrapper.MAX_RETRY_NUM);
	}
	
	/**
	 * 重新获取验证码
	 */
	private void regetCaptcha() {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("myphone", phone);
		requestParams.put("status", "1");
		if ("user".equals(role)) {
			requestParams.put("idcode", "1");
		} else {
			requestParams.put("idcode", "0");
		}
		post(true, CommonData.REMOTE_REQUEST_URL_HTTP + "/if/common/sms", requestParams, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				GetCaptchaBusinessBean getCaptchaBusinessBean = gson.fromJson(response, GetCaptchaBusinessBean.class);
				if (handleRequestResult(getCaptchaBusinessBean)) {
					ToastUtils.showShort(getContext(), "发送成功");
					countdown.setVisibility(View.VISIBLE);
					countdown.setText("（60s）");
					reget_captcha.setOnClickListener(null);
					reget_captcha.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bac_grey));
					reget_captcha_text.setTextColor(getResources().getColor(android.R.color.black));
					countdown();
				}
				
				CommonUtils.dismissProgressDialog();
			}

		}, VolleyWrapper.MAX_RETRY_NUM);
	}
	
	/**
	 * 倒计时60秒
	 */
	private void countdown() {
		Runnable runnable = new Runnable() {

			private int countdown_time = 60;

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				if (countdown_time != 1) {
					countdown.setText("（" + --countdown_time + "s）");
					countdown.postDelayed(this, 1000);
				} else {
					countdown.setVisibility(View.GONE);
					reget_captcha.setOnClickListener(ForgetPasswordStep02Activity.this);
					reget_captcha.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bac));
					reget_captcha_text.setTextColor(getResources().getColor(android.R.color.white));
				}
			}

		};
		countdown.postDelayed(runnable, 1000);
	}
	
}
