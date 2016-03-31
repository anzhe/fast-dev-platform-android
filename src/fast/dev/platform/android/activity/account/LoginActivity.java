package fast.dev.platform.android.activity.account;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.exception.SocializeException;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.MainActivity;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.business.AccountService;
import fast.dev.platform.android.constant.Constants;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.ThirdPartyUtils;
import fast.dev.platform.android.util.ToastUtils;

public class LoginActivity extends BaseActivity {

	private EditText account;
	private EditText password;
	
	private AccountService accountService;
	
	private UMSocialService umSocialService = UMServiceFactory.getUMSocialService(Constants.DESCRIPTOR_LOGIN);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		
		account = (EditText) findViewById(R.id.account);
		password = (EditText) findViewById(R.id.password);
		
		accountService = new AccountService(getContext(), volleyWrapper);
		
		ThirdPartyUtils.addQZoneQQPlatform(LoginActivity.this);
		ThirdPartyUtils.addSinaWeiboPlatform();
		ThirdPartyUtils.addWeixinPlatform(getContext());
	}

	public void login(View v) {
		String account_str = account.getText().toString();
		String password_str = password.getText().toString();
		if (TextUtils.isEmpty(account_str)) {
			ToastUtils.showShort(getContext(), "请输入用户名/邮箱/手机号");
			account.requestFocus();
			return;
		}
		if (TextUtils.isEmpty(password_str)) {
			ToastUtils.showShort(getContext(), "请输入密码");
			password.requestFocus();
			return;
		}
		if (password_str.length() < 6) {
			ToastUtils.showShort(getContext(), "密码长度不能小于6位");
			password.requestFocus();
			password.setSelection(password.length());
			return;
		}
		CommonUtils.hideSoftKeyboard(v);
		user_sp.edit().putBoolean("logged_on", true).apply();
		startActivity(new Intent(getContext(), MainActivity.class));
		finishActivity();
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
			thirdPartyAuthorize(SHARE_MEDIA.QQ);
			break;
		case R.id.weixin_login:
			thirdPartyAuthorize(SHARE_MEDIA.WEIXIN);
			break;
		case R.id.weibo_login:
			thirdPartyAuthorize(SHARE_MEDIA.SINA);
			break;
		}
	}

	/**
	 * 授权。如果授权成功，则获取用户信息
	 */
	private void thirdPartyAuthorize(SHARE_MEDIA platform) {
		umSocialService.doOauthVerify(getContext(), platform, new UMAuthListener() {

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
				if (!TextUtils.isEmpty(uid)) {
//					HashMap<String, String> thirdPartyLoginParams = new HashMap<String, String>();
//					thirdPartyLoginParams.put("thirdparty", uid);
//					accountService.thirdLogin(true, thirdPartyLoginParams, uid, new ThirdLoginCallback() {
//						
//						@Override
//						public void doCallback(final LoginBean loginBean, int userType) {
//							CommonUtils.showLoadingProgressDialog(getContext());
//							
//							HashMap<String, String> loginParams = new HashMap<String, String>();
//							loginParams.put("account", loginBean.getMY_PHONE());
//							loginParams.put("password", loginBean.getPASSWORD());
//							accountService.login(false, loginParams, userType, new LoginCallback() {
//								
//								@Override
//								public void doCallback() {
//									
//								}
//								
//							});
//						}
//						
//					});
					user_sp.edit().putBoolean("logged_on", true).apply();
					startActivity(new Intent(getContext(), MainActivity.class));
					finishActivity();
				} else {
					ToastUtils.showShort(getContext(), "授权失败");
				}
			}

			@Override
			public void onCancel(SHARE_MEDIA platform) {
				
			}

		});
	}
	
}
