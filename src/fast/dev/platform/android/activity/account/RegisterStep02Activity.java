package fast.dev.platform.android.activity.account;

import java.util.HashMap;

import com.android.volley.Response.Listener;
import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;

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
import fast.dev.platform.android.bean.business.GetCaptchaForThirdPartyBusinessBean;
import fast.dev.platform.android.business.AccountService;
import fast.dev.platform.android.business.AccountService.LoginCallback;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.fragment.MainFragment;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.ToastUtils;

public class RegisterStep02Activity extends BaseActivity {

	private AccountService accountService;
	
	private EditText captcha;
	private Button btn_next;
	private TextView phone_tv;
	private View reget_captcha;
	private TextView reget_captcha_text;
	private TextView countdown;
	
	private Intent intent;
	private String thirdparty;
	private String phone;
	private String role;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Bugly
		 */
		CrashReport.setUserSceneTag(getContext(), 6149);
		
		setContentView(R.layout.activity_register_step_02);

		accountService = new AccountService(getContext(), volleyWrapper);
		
		initData();
		initViews();
		initListeners();
	}

	private void initData() {
		intent = getIntent();
		thirdparty = intent.getStringExtra("thirdparty");
		phone = intent.getStringExtra("phone");
		role = intent.getStringExtra("role");
	}
	
	private void initViews() {
		captcha = (EditText) findViewById(R.id.captcha);
		btn_next = (Button) findViewById(R.id.btn_next);
		phone_tv = (TextView) findViewById(R.id.phone_tv);
//		phone_tv.setText(CommonUtils.formatPhone(phone));
		phone_tv.setText(phone);
		reget_captcha = findViewById(R.id.reget_captcha);
		reget_captcha_text = (TextView) findViewById(R.id.reget_captcha_text);
		countdown = (TextView) findViewById(R.id.countdown);
		
		countdown();
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
			if (TextUtils.isEmpty(thirdparty)) {
				verifyCaptcha();
			} else {
				verifyCaptchaForThirdParty();
			}
			break;
		case R.id.reget_captcha:
			if (TextUtils.isEmpty(thirdparty)) {
				regetCaptcha();
			} else {
				regetCaptchaForThirdParty();
			}
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
					Intent intent = new Intent(getContext(), RegisterStep03Activity.class);
					intent.putExtra("thirdparty", thirdparty);
					intent.putExtra("phone", phone);
					intent.putExtra("role", role);
					startActivity(intent);
				}
				
				CommonUtils.dismissProgressDialog();
			}

		}, VolleyWrapper.MAX_RETRY_NUM);
	}
	
	/**
	 * 校验验证码（用于第三方登录）
	 */
	private void verifyCaptchaForThirdParty() {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("myphone", phone);
		requestParams.put("thirdparty", thirdparty);
		requestParams.put("smsnum", captcha.getText().toString());
		post(true, CommonData.REMOTE_REQUEST_URL_HTTP + "/if/common/validateThirdSms", requestParams, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				GetCaptchaForThirdPartyBusinessBean result = gson.fromJson(response, GetCaptchaForThirdPartyBusinessBean.class);
				if (handleRequestResult(result)) {
					if (result.getData().getIfexist() == 0) {
						Intent intent = new Intent(getContext(), RegisterStep03Activity.class);
						intent.putExtra("thirdparty", thirdparty);
						intent.putExtra("phone", phone);
						intent.putExtra("role", role);
						startActivity(intent);
					} else {
						final String account = result.getData().getAccount().getUSERNAME();
						final String password = result.getData().getAccount().getPASSWORD();
						HashMap<String, String> loginParams = new HashMap<String, String>();
						loginParams.put("account", account);
						loginParams.put("password", password);
						CommonUtils.showLoadingProgressDialog(getContext());
						int user_role = result.getData().getIfexist() == 1 ? 0 : 1;
						accountService.login(false, loginParams, user_role, new LoginCallback() {
							
							@Override
							public void doCallback() {
							}
							
						});
					}
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
		requestParams.put("status", "0");
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
	 * 重新获取验证码（用于第三方登录）
	 */
	private void regetCaptchaForThirdParty() {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("myphone", phone);
		post(true, CommonData.REMOTE_REQUEST_URL_HTTP + "/if/common/sentThirdSms", requestParams, new Listener<String>() {

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
					reget_captcha.setOnClickListener(RegisterStep02Activity.this);
					reget_captcha.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bac));
					reget_captcha_text.setTextColor(getResources().getColor(android.R.color.white));
				}
			}

		};
		countdown.postDelayed(runnable, 1000);
	}
	
}
