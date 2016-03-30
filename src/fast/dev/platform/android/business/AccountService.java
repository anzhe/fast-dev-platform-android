package fast.dev.platform.android.business;

import java.util.HashMap;

import com.activeandroid.query.Select;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;
import com.testin.agent.TestinAgent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import fast.dev.platform.android.activity.account.Login111Activity;
import fast.dev.platform.android.activity.account.RegisterActivity;
import fast.dev.platform.android.bean.LoginBean;
import fast.dev.platform.android.bean.business.LoginBussniseBean;
import fast.dev.platform.android.bean.business.ThirdPartyLoginBussniseBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.fragment.MainFragment;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.model.User;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.DeviceInfoUtils;
import fast.dev.platform.android.util.ToastUtils;

/**
 * 账号相关服务
 */
public class AccountService implements ErrorListener {

	private Gson gson = new Gson();

	private VolleyWrapper volleyWrapper;

	private Context mContext;

	public AccountService(Context context, VolleyWrapper volleyWrapper) {
		this.mContext = context;
		this.volleyWrapper = volleyWrapper;
	}

	/**
	 * 登录
	 * 
	 * @param showLoadingDialog
	 * @param loginParams
	 * @param user_type
	 * @param callback
	 */
	public void login(final boolean showLoadingDialog, final HashMap<String, String> loginParams, final int user_type, final LoginCallback callback) {
		if (showLoadingDialog) {
			CommonUtils.showLoadingProgressDialog(mContext);
		}
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				LoginBussniseBean loginBussniseBean = gson.fromJson(response, LoginBussniseBean.class);
				if (CommonUtils.handleRequestResult(mContext, loginBussniseBean)) {
//					BrowsingHistoryUtils.clearHistory();// 清空历史浏览记录
					
					User user = new Select().from(User.class).where("Account=?", loginParams.get("account")).executeSingle();
					if (user != null) {
						user.password = loginParams.get("password");// 防止该用户在其他设备修改密码
						user.role = user_type;
					} else {
						user = new User(loginParams.get("account"), loginParams.get("password"), user_type);
					}
					user.save();

					CommonUtils.setupUserInfo(loginBussniseBean.getData(), user_type);
					CommonUtils.user_sp(mContext).edit().putString("account", loginParams.get("account")).apply();
					CommonUtils.user_sp(mContext).edit().putBoolean("logged_on", true).apply();// 已登录标记

					if (callback != null) {
						callback.doCallback();
					} else {
						mContext.startActivity(new Intent(mContext, MainFragment.class));
						((Activity) mContext).finish();
					}
				} else {
					CommonUtils.dismissProgressDialog();
					
					CommonUtils.user_sp(mContext).edit().putBoolean("logged_on", false).apply();// 未登录标记
					
					Intent loginIntent = new Intent(mContext, Login111Activity.class);
					loginIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					mContext.startActivity(loginIntent);
				}

				if (showLoadingDialog) {
					CommonUtils.dismissProgressDialog();
				}
			}

		};
		String role = null;
		switch (user_type) {// 0为用户, 1为律师
		case 0:
			role = "user";
			break;
		case 1:
			role = "lawer";
			break;
		}
		loginParams.put("versioncode", DeviceInfoUtils.getVersionCode(mContext) + "");
		volleyWrapper.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/" + role + "/login", loginParams, listener, this, VolleyWrapper.MAX_RETRY_NUM);
	}

	/**
	 * 第三方登录
	 * 
	 * @param showLoadingDialog
	 * @param thirdPartyLoginParams
	 * @param thirdparty
	 * @param callback
	 */
	public void thirdLogin(final boolean showLoadingDialog, HashMap<String, String> thirdPartyLoginParams,
			final String thirdparty, final ThirdLoginCallback callback) {
		if (showLoadingDialog) {
			CommonUtils.showLoadingProgressDialog(mContext);
		}
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				ThirdPartyLoginBussniseBean thirdPartyLoginBussniseBean = gson.fromJson(response, ThirdPartyLoginBussniseBean.class);
				if (CommonUtils.handleRequestResult(mContext, thirdPartyLoginBussniseBean, true, false, null)) {
					if (callback != null) {
						ThirdPartyLoginBussniseBean.Data data = thirdPartyLoginBussniseBean.getData();
						LoginBean loginBean = data.getAccount();
						callback.doCallback(loginBean, thirdPartyLoginBussniseBean.getData().getType());
					}
				} else if (TextUtils.equals("7103", thirdPartyLoginBussniseBean.getResultcode())) {// 版本校验限制，登录时检查应用版本
					ToastUtils.showLong(mContext, thirdPartyLoginBussniseBean.getResultdesc());
				} else {
					Intent intent = new Intent(mContext, RegisterActivity.class);
					intent.putExtra("thirdparty", thirdparty);
					mContext.startActivity(intent);
				}

				if (showLoadingDialog) {
					CommonUtils.dismissProgressDialog();
				}
			}

		};
		thirdPartyLoginParams.put("versioncode", DeviceInfoUtils.getVersionCode(mContext) + "");
		volleyWrapper.post(CommonData.REMOTE_REQUEST_URL_HTTP + "/if/thirdparty/login", thirdPartyLoginParams, listener, this, VolleyWrapper.MAX_RETRY_NUM);
	}
	
	@Override
	public void onErrorResponse(VolleyError error) {
		CrashReport.postCatchedException(error);
		
		TestinAgent.uploadException(mContext, "调用服务器端接口报错", error);
		
		CommonUtils.dismissProgressDialog();

		ToastUtils.showShort(mContext, "网络异常，请稍后重试。");
	}

	public interface LoginCallback {

		public void doCallback();

	}
	
	public interface ThirdLoginCallback {

		public void doCallback(LoginBean loginBean, int userType);

	}
	
}
