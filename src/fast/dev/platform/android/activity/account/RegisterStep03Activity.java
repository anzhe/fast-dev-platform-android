package fast.dev.platform.android.activity.account;

import java.util.HashMap;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.bean.business.RegisterBusinessBean;
import fast.dev.platform.android.business.AccountService;
import fast.dev.platform.android.business.AccountService.LoginCallback;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.MD5Utils;
import fast.dev.platform.android.util.ToastUtils;

public class RegisterStep03Activity extends BaseActivity {

	private AccountService accountService;
	
	private EditText name;
	private EditText password_01;
	private EditText password_02;
	private EditText referrer;
	private View referrer_box;
	private Button submit;
	
	private Intent intent;
	private String thirdparty;
	private String phone;
	private String role;
	
	private HashMap<String, String> registerParams = new HashMap<String, String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Bugly
		 */
		CrashReport.setUserSceneTag(getContext(), 6149);
		
		setContentView(R.layout.activity_register_step_03);

		accountService = new AccountService(getContext(), volleyWrapper);
		
		initData();
		initViews();
		initListeners();
	}

	private void initViews() {
		name = (EditText) findViewById(R.id.name);
		password_01 = (EditText) findViewById(R.id.password_01);
		password_02 = (EditText) findViewById(R.id.password_02);
		referrer = (EditText) findViewById(R.id.referrer);
		referrer_box = findViewById(R.id.referrer_box);
		if (TextUtils.equals(role, "lawyer")) {
			referrer_box.setVisibility(View.GONE);
		}
		submit = (Button) findViewById(R.id.submit);
	}

	private void initData() {
		intent = getIntent();
		thirdparty = intent.getStringExtra("thirdparty");
		phone = intent.getStringExtra("phone");
		role = intent.getStringExtra("role");
	}

	private void initListeners() {
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		
		switch (v.getId()) {
		case R.id.submit:
			
			if (TextUtils.isEmpty(name.getText())) {
				ToastUtils.showShort(getContext(), "请输入姓名");
				name.requestFocus();
				return;
			}
			
			if (TextUtils.isEmpty(password_01.getText())) {
				ToastUtils.showShort(getContext(), "请输入密码");
				password_01.requestFocus();
				password_01.setSelection(password_01.length());
				return;
			}
			if (password_01.getText().length() < 6) {
				ToastUtils.showShort(getContext(), "密码长度不能小于6位");
				password_01.requestFocus();
				password_01.setSelection(password_01.length());
				return;
			}
			if (!TextUtils.equals(password_01.getText(), password_02.getText())) {
				ToastUtils.showShort(getContext(), "两次输入的密码不一致");
				password_02.requestFocus();
				password_02.setSelection(password_02.length());
				return;
			}
			
			registerParams.put("myphone", phone);
			registerParams.put("thirdparty", thirdparty);
			registerParams.put("relname", name.getText().toString());
			registerParams.put("password", MD5Utils.toMD5(password_01.getText().toString()));
			registerParams.put("invitedcode", referrer.getText().toString());
			registerParams.put("type", "0");
			register();
			break;
		}
	}
	
	/**
	 * 注册
	 */
	private void register() {
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				final RegisterBusinessBean registerBusinessBean = gson.fromJson(response, RegisterBusinessBean.class);
				if (handleRequestResult(registerBusinessBean)) {
					HashMap<String, String> loginParams = new HashMap<String, String>();
					loginParams.put("account", phone);
					loginParams.put("password", MD5Utils.toMD5(password_01.getText().toString()));
					accountService.login(false, loginParams, "user".equals(role) ? 0 : 1, new LoginCallback() {

						@Override
						public void doCallback() {
						}

					});
				} else {
					CommonUtils.dismissProgressDialog();
				}
			}

		};
		post(true, CommonData.REMOTE_REQUEST_URL_HTTP + "/if/" + role + "/register", registerParams, listener, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				CommonUtils.dismissProgressDialog();

				CrashReport.postCatchedException(error);

				TestinAgent.uploadException(getContext(), "调用服务器端接口报错", error);
				
				Intent intent = new Intent(getContext(), Login111Activity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
			
		});
	}
	
}
