package fast.dev.platform.android.activity.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activeandroid.query.Select;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.SocializeClientListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.UMSsoHandler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.MainActivity;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.application.MyApplication;
import fast.dev.platform.android.bean.LoginBean;
import fast.dev.platform.android.broadcastreceiver.NetworkChangeBroadcastReceiver;
import fast.dev.platform.android.business.AccountService;
import fast.dev.platform.android.business.AccountService.LoginCallback;
import fast.dev.platform.android.business.AccountService.ThirdLoginCallback;
import fast.dev.platform.android.constant.Constants;
import fast.dev.platform.android.model.User;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.NetworkUtils;
import fast.dev.platform.android.util.ThirdPartyUtils;
import fast.dev.platform.android.util.ToastUtils;

public class LoginActivity extends BaseActivity {

	private static final String TAG = LoginActivity.class.getSimpleName();
	
	private AccountService accountService;
	
	private TextView forget_password;
	private Button login_btn;
	private EditText user_edit;
	private EditText password_edit;
	private TextView btn_register;
	private ImageView qq_login;
	private ImageView weibo_login;
	private ImageView weixin_login;
	private TextView change_url;
	private View phone_divider;
	private PopupWindow popupWindow;
	
	private String up_Username = "";
	private String up_Password = "";
	private boolean isUser = true;

	private RadioButton userRb;
	private RadioButton lawyerRb;
	
	private ListView login_history_list;
	private List<String> login_history_data = new ArrayList<String>();
	private List<String> login_history_data_backup = new ArrayList<String>();
	private BaseAdapter login_history_list_adapter;
	
	private AlertDialog networkDialog;
	
	private NetworkChangeBroadcastReceiver networkChangeBroadcastReceiver = new NetworkChangeBroadcastReceiver(LoginActivity.class);
	
	private UMSocialService mController = UMServiceFactory.getUMSocialService(Constants.DESCRIPTOR_LOGIN);
	
	/**
	 * 注销本次登录
	 */
	private void logout(final SHARE_MEDIA platform) {
		mController.deleteOauth(getContext(), platform, new SocializeClientListener() {

			@Override
			public void onStart() {

			}

			@Override
			public void onComplete(int status, SocializeEntity entity) {
				String showText = "解除" + platform.toString() + "平台授权成功";
				if (status != StatusCode.ST_CODE_SUCCESSED) {
					showText = "解除" + platform.toString() + "平台授权失败[" + status + "]";
				}
				ToastUtils.showShort(getContext(), showText);
			}

		});
	}

	/**
	 * 授权。如果授权成功，则获取用户信息
	 */
	private void login(final SHARE_MEDIA platform) {
		mController.doOauthVerify(getContext(), platform, new UMAuthListener() {

			@Override
			public void onStart(SHARE_MEDIA platform) {
				
			}

			@Override
			public void onError(SocializeException e, SHARE_MEDIA platform) {
				ToastUtils.showShort(getContext(), "授权错误");
			}

			@Override
			public void onComplete(Bundle value, SHARE_MEDIA platform) {
				String uid = value.getString("uid");
				Log.i("Third Party", uid);
				if (!TextUtils.isEmpty(uid)) {
					//ToastUtils.showShort(getContext(), "授权成功");
					HashMap<String, String> thirdPartyLoginParams = new HashMap<String, String>();
					thirdPartyLoginParams.put("thirdparty", uid);
					accountService.thirdLogin(true, thirdPartyLoginParams, uid, new ThirdLoginCallback() {
						
						@Override
						public void doCallback(final LoginBean loginBean, int userType) {
							CommonUtils.showLoadingProgressDialog(getContext());
							
							HashMap<String, String> loginParams = new HashMap<String, String>();
							loginParams.put("account", loginBean.getMY_PHONE());
							loginParams.put("password", loginBean.getPASSWORD());
							accountService.login(false, loginParams, userType, new LoginCallback() {
								
								@Override
								public void doCallback() {
								}
								
							});
						}
						
					});
					//getUserInfo(platform);
				} else {
					ToastUtils.showShort(getContext(), "授权失败");
				}
			}

			@Override
			public void onCancel(SHARE_MEDIA platform) {
				
			}

		});
	}

	/**
	 * 获取授权平台的用户信息</br>
	 */
	private void getUserInfo(SHARE_MEDIA platform) {
		mController.getPlatformInfo(getContext(), platform, new UMDataListener() {

			@Override
			public void onStart() {
				
			}

			@Override
			public void onComplete(int status, Map<String, Object> info) {
				String showText = "";
				if (status == StatusCode.ST_CODE_SUCCESSED) {
					showText = "用户名：" + info.get("screen_name").toString();
					Log.d("QQ_Login", info.toString());
				} else {
					showText = "获取用户信息失败";
				}
				ToastUtils.showShort(getContext(), showText);
			}

		});
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * Bugly
		 */
		CrashReport.setUserSceneTag(getContext(), 11885);
		
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		registerReceiver(networkChangeBroadcastReceiver, filter);
		
		setContentView(R.layout.activity_login);
		
		accountService = new AccountService(getContext(), volleyWrapper);
		
		ThirdPartyUtils.addQZoneQQPlatform(LoginActivity.this);
		ThirdPartyUtils.addSinaWeiboPlatform();
		ThirdPartyUtils.addWeixinPlatform(getContext());
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		initData();
		initViews();
		initListeners();
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		if (!NetworkUtils.isOpen(getContext())) {
			if (networkDialog == null) {
				networkDialog = new AlertDialog.Builder(getContext()).setTitle("网络设置提示")
						.setMessage("网络连接不可用，是否进行设置？").setPositiveButton("设置", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
								startActivity(intent);
							}
							
						}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								MyApplication.getInstance().exit();
							}
							
						}).create();
				networkDialog.setCanceledOnTouchOutside(false);
			}
			if (!networkDialog.isShowing()) {
				networkDialog.show();
			}
		} else {
			if (networkDialog != null && networkDialog.isShowing()) {
				networkDialog.dismiss();
			}
			
			if (user_sp.getBoolean("logged_on", false)) {
				final User user = new Select().from(User.class).where("Account=?", user_sp.getString("account", "")).executeSingle();
				if (user != null) {
					HashMap<String, String> loginParams = new HashMap<String, String>();
					loginParams.put("account", user.account);
					loginParams.put("password", user.password);
					CommonUtils.showLoadingProgressDialog(getContext());
					accountService.login(false, loginParams, user.role, new LoginCallback() {
						
						@Override
						public void doCallback() {
						}
						
					});
				}
			}
		}
	}

	private void initData() {
		List<User> users = new Select().distinct().from(User.class).orderBy("Id DESC").limit(10).execute();
		if (users != null && !users.isEmpty()) {
			for (User user : users) {
				login_history_data.add(user.account);
				login_history_data_backup.add(user.account);
			}
		}
	}

	private void initViews() {
		View rootView = LayoutInflater.from(getContext()).inflate(R.layout.listview_login_history, null);
		login_history_list = (ListView) rootView.findViewById(R.id.login_history_list);
		popupWindow = new PopupWindow(rootView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, false);
		popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
		popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setOutsideTouchable(true);
		
		login_history_list_adapter = new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView root_view = (TextView) convertView;
				if (root_view == null) {
					root_view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.listview_item_login_history, parent, false);
				}
				final String phone = login_history_data.get(position);
				root_view.setText(phone);
				root_view.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						user_edit.setText(phone);
						popupWindow.dismiss();
						password_edit.requestFocus();
					}
					
				});
				return root_view;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return login_history_data.get(position);
			}
			
			@Override
			public int getCount() {
				return login_history_data.size();
			}
			
		};
		
		login_history_list.setAdapter(login_history_list_adapter);
		
		forget_password = (TextView) findViewById(R.id.forget_password);

		login_btn = (Button) findViewById(R.id.login_btn);
		user_edit = (EditText) findViewById(R.id.user_edit);
		password_edit = (EditText) findViewById(R.id.password_edit);
		btn_register = (TextView) findViewById(R.id.btn_register);
		qq_login = (ImageView) findViewById(R.id.qq_login);
		weibo_login = (ImageView) findViewById(R.id.weibo_login);
		weixin_login = (ImageView) findViewById(R.id.weixin_login);
		change_url = (TextView) findViewById(R.id.change_url);
		userRb = (RadioButton) findViewById(R.id.user_rb);
		lawyerRb = (RadioButton) findViewById(R.id.lawyer_rb);
		isUser = user_sp.getBoolean("isuser", true);
		userRb.setChecked(isUser);
		lawyerRb.setChecked(!isUser);
		
		phone_divider = findViewById(R.id.phone_divider);
	}

	private void initListeners() {
		user_edit.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_NEXT) {
					password_edit.requestFocus();
					if (popupWindow.isShowing()) {
						popupWindow.dismiss();
					}
					return true;
				}
				return false;
			}
			
		});
		password_edit.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				CommonUtils.hideSoftKeyboard(v);
				return true;
			}
			
		});
		
		login_btn.setOnClickListener(this);
		forget_password.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		qq_login.setOnClickListener(this);
		weibo_login.setOnClickListener(this);
		weixin_login.setOnClickListener(this);
		change_url.setOnClickListener(this);
		
		user_edit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.length() == 11) {
					popupWindow.dismiss();
				} else {
					if (!popupWindow.isShowing()) {
						popupWindow.showAsDropDown(phone_divider, 0, 0);
					}
					if (s.length() > 0) {
						login_history_data.clear();
						for (String phone : login_history_data_backup) {
							if (phone.startsWith(s.toString())) {
								login_history_data.add(phone);
							}
						}
						login_history_list_adapter.notifyDataSetChanged();
						popupWindow.dismiss();
						popupWindow.showAsDropDown(phone_divider, 0, 0);
					} else {
						login_history_data.clear();
						login_history_data.addAll(login_history_data_backup);
						login_history_list_adapter.notifyDataSetChanged();
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
			
		});
		user_edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!login_history_data.isEmpty()) {
					if (!popupWindow.isShowing()) {
						popupWindow.showAsDropDown(phone_divider, 0, 0);
					}
				}
			}
			
		});
		user_edit.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					if (!login_history_data.isEmpty()) {
						if (!popupWindow.isShowing()) {
							popupWindow.showAsDropDown(phone_divider, 0, 0);
						}
					}
				}
			}
			
		});
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);

		switch (v.getId()) {
		case R.id.change_url:
			// systemConfiguration();
			break;
		case R.id.qq_login:
			login(SHARE_MEDIA.QQ);
			break;
		case R.id.weibo_login:
			login(SHARE_MEDIA.SINA);
			break;
		case R.id.weixin_login:
			login(SHARE_MEDIA.WEIXIN);
			break;
		case R.id.forget_password:
			startActivity(new Intent(getContext(), ForgetPasswordStep01Activity.class));
			break;
		case R.id.login_btn:
			CommonUtils.hideSoftKeyboard(v);
			up_Username = user_edit.getText().toString().trim();
			up_Password = password_edit.getText().toString().trim();
			if (TextUtils.isEmpty(up_Username)) {
				ToastUtils.showShort(getContext(), "请输入手机号码");
				user_edit.requestFocus();
				return;
			}
			if (TextUtils.isEmpty(up_Password)) {
				ToastUtils.showShort(getContext(), "请输入密码");
				password_edit.requestFocus();
				return;
			}
			if (up_Password.length() < 6) {
				ToastUtils.showShort(getContext(), "密码长度不能小于6位");
				password_edit.requestFocus();
				password_edit.setSelection(password_edit.length());
				return;
			}
			CommonUtils.showLoadingProgressDialog(getContext());
			
			finishActivity();
			startActivity(new Intent(getContext(), MainActivity.class));
			
			user_sp.edit().putBoolean("logged_on", true).apply();
			
//			HashMap<String, String> loginParams = new HashMap<String, String>();
//			loginParams.put("account", up_Username);
//			loginParams.put("password", MD5Utils.toMD5(up_Password));
//			int user_type = lawyerRb.isChecked() ? 1 : 0;
//			accountService.login(false, loginParams, user_type, new LoginCallback() {
//
//				@Override
//				public void doCallback() {
//					
//				}
//
//			});
			
			break;
		case R.id.btn_register:
			startActivity(new Intent(getContext(), RegisterActivity.class));
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			new AlertDialog.Builder(getContext()).setTitle("提示")
					.setMessage("退出" + this.getResources().getString(R.string.app_name) + "?")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							MyApplication.getInstance().exit();
						}

					}).setNegativeButton("取消", null).show();
			return false;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		unregisterReceiver(networkChangeBroadcastReceiver);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}
	
}
