package fast.dev.platform.android.activity.account;

import java.util.HashMap;

import com.android.volley.Response.Listener;
import com.tencent.bugly.crashreport.CrashReport;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.bean.business.GetCaptchaBusinessBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.util.CommonUtils;

public class UserAgreementActivity extends BaseActivity {

	private WebView webView;
	private View progressBar;
	private TextView btn_refuse;
	private TextView btn_agree;
	
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
		CrashReport.setUserSceneTag(getContext(), 6127);
		
		setContentView(R.layout.activity_user_agreement);

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
		progressBar = findViewById(R.id.progressBar);
		webView = (WebView) findViewById(R.id.webView);
		webView.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				progressBar.setVisibility(View.GONE);
			}
			
		});
		String url = null;
		if (TextUtils.equals(role, "user")) {
			url = dict_sp.getString(CommonData.DICT_U_REGISTERAGREEMENT, "");
		} else {
			url = dict_sp.getString(CommonData.DICT_L_REGISTERAGREEMENT, "");
		}
		webView.loadUrl(url);
		btn_refuse = (TextView) findViewById(R.id.btn_refuse);
		btn_agree = (TextView) findViewById(R.id.btn_agree);
	}
	
	private void initListeners() {
		btn_refuse.setOnClickListener(this);
		btn_agree.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);

		switch (v.getId()) {
		case R.id.btn_refuse:
			this.finish();
			break;
		case R.id.btn_agree:
			if (TextUtils.isEmpty(thirdparty)) {
				getCaptcha();
			} else {
				getCaptchaForThirdParty();
			}
			break;
		}
	}

	/**
	 * 获取验证码
	 */
	private void getCaptcha() {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("myphone", phone);
		requestParams.put("status", "0");
		if (TextUtils.equals(role, "user")) {
			requestParams.put("idcode", "1");
		} else {
			requestParams.put("idcode", "0");
		}
		post(true, CommonData.REMOTE_REQUEST_URL_HTTP + "/if/common/sms", requestParams, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				GetCaptchaBusinessBean getCaptchaBusinessBean = gson.fromJson(response, GetCaptchaBusinessBean.class);
				if (handleRequestResult(getCaptchaBusinessBean)) {
					Intent intent = new Intent(getContext(), RegisterStep02Activity.class);
					intent.putExtra("thirdparty", thirdparty);
					intent.putExtra("phone", phone);
					if (TextUtils.equals(role, "user")) {
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
	
	/**
	 * 获取验证码（用于第三方登录）
	 */
	private void getCaptchaForThirdParty() {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("myphone", phone);
		post(true, CommonData.REMOTE_REQUEST_URL_HTTP + "/if/common/sentThirdSms", requestParams, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				GetCaptchaBusinessBean getCaptchaBusinessBean = gson.fromJson(response, GetCaptchaBusinessBean.class);
				if (handleRequestResult(getCaptchaBusinessBean)) {
					Intent intent = new Intent(getContext(), RegisterStep02Activity.class);
					intent.putExtra("thirdparty", thirdparty);
					intent.putExtra("phone", phone);
					if (TextUtils.equals(role, "user")) {
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
