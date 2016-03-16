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
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.MD5Utils;
import fast.dev.platform.android.util.ToastUtils;

public class ForgetPasswordStep03Activity extends BaseActivity {

	private EditText password_01;
	private EditText password_02;
	private Button submit;
	
	private Intent intent;
	private String phone;
	private String role;
	
	private HashMap<String, String> registerParams = new HashMap<String, String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Bugly
		 */
		CrashReport.setUserSceneTag(getContext(), 6150);
		
		setContentView(R.layout.activity_forget_password_step_03);

		initData();
		initViews();
		initListeners();
	}

	private void initViews() {
		password_01 = (EditText) findViewById(R.id.password_01);
		password_02 = (EditText) findViewById(R.id.password_02);
		submit = (Button) findViewById(R.id.submit);
	}

	private void initData() {
		intent = getIntent();
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
			if (TextUtils.isEmpty(password_01.getText())) {
				ToastUtils.showShort(getContext(), "请输入密码");
				password_01.requestFocus();
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
			registerParams.put("password", MD5Utils.toMD5(password_01.getText().toString()));
			registerParams.put("type", "1");
			register();
			break;
		}
	}

	/**
	 * 注册
	 */
	private void register() {
		post(true, CommonData.REMOTE_REQUEST_URL_HTTP + "/if/" + role + "/register", registerParams, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				RegisterBusinessBean registerBusinessBean = gson.fromJson(response, RegisterBusinessBean.class);
				if (handleRequestResult(registerBusinessBean)) {
					ToastUtils.showShort(getContext(), registerBusinessBean.getResultdesc());
					Intent intent = new Intent(getContext(), LoginActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(intent);
				}
				
				CommonUtils.dismissProgressDialog();
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				CrashReport.postCatchedException(error);
				
				TestinAgent.uploadException(getContext(), "调用服务器端接口报错", error);
				
				CommonUtils.dismissProgressDialog();
				
				Intent intent = new Intent(getContext(), LoginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
			
		});
	}
	
}
