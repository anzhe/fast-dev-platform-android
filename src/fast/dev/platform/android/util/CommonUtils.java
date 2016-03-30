package fast.dev.platform.android.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import com.loopj.android.http.HttpGet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import fast.dev.platform.android.activity.account.Login111Activity;
import fast.dev.platform.android.application.MyApplication;
import fast.dev.platform.android.bean.LoginBean;
import fast.dev.platform.android.bean.base.BaseBusinessBean;
import fast.dev.platform.android.constant.CommonData;

@SuppressWarnings("deprecation")
public class CommonUtils {

	private static Context mContext;
	private static ProgressDialog mProgressDialog;
	
	private static SharedPreferences user_sp = MyApplication.getInstance().user_sp();
	
	/**
	 * 判断是否是普通用户
	 * 
	 * @param user_sp
	 * 
	 * @return
	 */
	public static boolean isUser(SharedPreferences user_sp) {
		if (TextUtils.isEmpty(user_sp.getString("userid", ""))) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否是律师用户
	 * 
	 * @param user_sp
	 * 
	 * @return
	 */
	public static boolean isLawyer(SharedPreferences user_sp) {
		if (TextUtils.isEmpty(user_sp.getString("lawyerid", ""))) {
			return false;
		}
		return true;
	}
	
	/**
	 * 混淆电话号码
	 * 
	 * @param phone
	 * 
	 * @return
	 */
	public static String mixPhone(String phone) {
		if (phone == null || TextUtils.isEmpty(phone) || phone.length() != 11) {
			return "";
		}
		String prefix = phone.substring(0, 3);
		String suffix = phone.substring(7, 11);
		return prefix + "****" + suffix;
	}
	
	/**
	 * 退出应用
	 */
	public static void exitApp() {
		MyApplication.getInstance().exit();
		
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}
	
	/**
	 * 显示软键盘
	 * 
	 * @param view
	 */
	public static void showSoftKeyboard(View view) {
		Context context = view.getContext();
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, 0);
	}
	
	/**
	 * 隐藏软键盘
	 * 
	 * @param view
	 */
	public static void hideSoftKeyboard(View view) {
		Context context = view.getContext();
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
	
	public static void showLoadingProgressDialog(Context context) {
		showProgressDialog(context, "加载中……");
	}

	public static void showProgressDialog(Context context, String message) {
		if (mProgressDialog == null || mContext != context) {
			mContext = context;
			mProgressDialog = new ProgressDialog(context);
			mProgressDialog.setIndeterminate(true);
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mProgressDialog.setCanceledOnTouchOutside(false);
			mProgressDialog.setCancelable(false);
		}
		if (!mProgressDialog.isShowing()) {
			mProgressDialog.setMessage(message);
			mProgressDialog.show();
		}
	}

	/**
	 * 创建指定消息的加载框
	 * 
	 * @param context
	 * @param message
	 */
	public static ProgressDialog createProgressDialog(Context context, String message) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setIndeterminate(true);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setCancelable(false);
		progressDialog.setMessage(message);
		return progressDialog;
	}
	
	public static void dismissProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}
	
	public boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}
	
	/**
	 * 用户信息数据库
	 * 
	 * @param context
	 * 
	 * @return
	 */
	public static SharedPreferences user_sp(Context context) {
		return context.getSharedPreferences("user", Context.MODE_PRIVATE);
	}
	
	/**
	 * 字典信息数据库
	 * 
	 * @param context
	 * 
	 * @return
	 */
	public static SharedPreferences dict_sp(Context context) {
		return context.getSharedPreferences("dict", Context.MODE_PRIVATE);
	}
	
	/**
	 * 系统信息数据库
	 * 
	 * @param context
	 * 
	 * @return
	 */
	public static SharedPreferences sys_sp(Context context) {
		return context.getSharedPreferences("sys", Context.MODE_PRIVATE);
	}
	
	/**
	 * 定位信息数据库
	 * 
	 * @param context
	 * 
	 * @return
	 */
	public static SharedPreferences location_sp(Context context) {
		return context.getSharedPreferences("location", Context.MODE_PRIVATE);
	}
	
	/**
	 * 处理请求的返回信息，不提示错误提示消息
	 * 
	 * @param context
	 * @param businessBean
	 * 
	 * @return
	 */
	public static boolean handleRequestResultWithoutErrorInfo(Context context, BaseBusinessBean businessBean) {
		return handleRequestResult(context, businessBean, true, false, null);
	}
	
	/**
	 * 处理请求的返回信息
	 * 
	 * @param context
	 * @param businessBean
	 * 
	 * @return
	 */
	public static boolean handleRequestResult(Context context, BaseBusinessBean businessBean) {
		return handleRequestResult(context, businessBean, false, false, null);
	}
	
	/**
	 * 处理请求的返回信息
	 * 
	 * @param context
	 * @param businessBean
	 * @param hideErrorInfo 隐藏错误提示信息
	 * @param showDefaultErrorInfo 显示默认错误提示信息
	 * @param customMsg 自定义错误提示消息
	 * 
	 * @return
	 */
	public static boolean handleRequestResult(Context context, BaseBusinessBean businessBean,
			boolean hideErrorInfo, boolean showDefaultErrorInfo, String customMsg) {
		String info = null;
		if (businessBean != null) {
			info = businessBean.getResultdesc();
			if (CommonData.REQUEST_SUCCESS.equals(businessBean.getResultcode())) {
				return true;
			} else if (CommonData.REQUEST_CODE_ERROR.equals(businessBean.getResultcode())) {
				log_out(context, businessBean);
				return false;
			}
		}
		if (!hideErrorInfo) {
			if (!TextUtils.isEmpty(customMsg)) {
				ToastUtils.showShort(context, customMsg);
			} else if (TextUtils.isEmpty(info) || showDefaultErrorInfo) {
				ToastUtils.showShort(context, "网络异常，请稍后重试。");
			} else {
				ToastUtils.showShort(context, info);
			}
		}
		return false;
	}

	public static void log_out(Context context, BaseBusinessBean baseBean) {
		if (baseBean != null) {
			ToastUtils.showShort(context, baseBean.getResultdesc());
		}
		user_sp(context).edit().clear().apply();
		Intent intent = new Intent(context, Login111Activity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		context.startActivity(intent);
		((Activity) context).finish();
	}
	
	public static String getUserId() {
		return user_sp.getString("userid", "");
	}

	public static String getLawyerId() {
		return user_sp.getString("lawyerid", "");
	}

	public static String getUserType() {
		if (CommonUtils.isLawyer(user_sp)) {
			return "2";
		} else {
			return "1";
		}
	}

	public static String getSession() {
		return user_sp.getString("Session", "");
	}

	public static void setSession(String Session) {
		user_sp.edit().putString("Session", Session).apply();
	}
	
	/**
	 * 格式化电话号码
	 * 
	 * @param phone
	 * 
	 * @return
	 */
	public static String formatPhone(String phone) {
		StringBuilder result = new StringBuilder();
		if (phone != null && phone.length() == 11) {
			String prefix = "+86 ";
			result.append(prefix);
			result.append(phone.substring(0, 3)).append(" ");
			result.append(phone.substring(3, 7)).append(" ");
			result.append(phone.substring(7));
		}
		return result.toString();
	}
	
	/**
	 * 将指定url的网络上的文件保存到本地SD卡中
	 * 
	 * @param url
	 * 
	 * @return
	 */
	public static String saveNetworkFile2SDCard(String url) {
		String file_path = Environment.getExternalStorageDirectory() + CommonData.cache_directory + System.currentTimeMillis() + ".jpg";
		try {
			HttpClient httpClient = HttpUtils.getHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			BufferedInputStream bis = new BufferedInputStream(is);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file_path));
			int c;
			while ((c = bis.read()) != -1) {
				bos.write(c);
			}
			bos.flush();
			is.close();
			bis.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file_path;
	}
	
	public static int dp2px(Context context, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
	}

	public static void setupUserInfo(LoginBean userInfo, int user_type) {
		user_sp.edit().putString("username", userInfo.getUSERNAME()).apply();
		user_sp.edit().putString("password", userInfo.getPASSWORD()).apply();
		user_sp.edit().putString("relName", userInfo.getREL_NAME()).apply();
		user_sp.edit().putString("myPhone", userInfo.getMY_PHONE()).apply();
		user_sp.edit().putString("email", userInfo.getEMAIL()).apply();
		user_sp.edit().putString("address", userInfo.getADDRESS()).apply();
		user_sp.edit().putString("province", userInfo.getPROVINCE()).apply();
		user_sp.edit().putString("city", userInfo.getCITY()).apply();
		user_sp.edit().putString("district", userInfo.getCOUNTY()).apply();
		user_sp.edit().putString("photo", userInfo.getPHOTO()).apply();
		user_sp.edit().putInt("status", userInfo.getSTATUS()).apply();
		user_sp.edit().putString("frozen_amount", userInfo.getFROZEN_AMOUNT() + "").apply();// 冻结金额
		String attention = userInfo.getATTENTION();
		if (!TextUtils.isEmpty(attention)) {
			String[] attention_array = attention.split("\\|");
			if (attention_array.length == 2) {
				user_sp.edit().putString("focus_count", attention_array[0]).apply();
				user_sp.edit().putString("fans_count", attention_array[1]).apply();
			}
		}
		user_sp.edit().putString("balance", userInfo.getBALANCE()).apply();// 保存用户余额
		switch (user_type) {
		case 0:
			user_sp.edit().putString("agentName", userInfo.getAGENT_NAME()).apply();
			user_sp.edit().putBoolean("isuser", true).apply();
			user_sp.edit().putString("userid", userInfo.getID() + "").apply();
			user_sp.edit().putString("lawyerid", "").apply();
			user_sp.edit().putString("packet", userInfo.getPACKET()).apply();// 保存用户红包
			user_sp.edit().putString("MY_INVITED_CODE", userInfo.getMY_INVITED_CODE()).apply();// 分享码
			break;
		case 1:
			user_sp.edit().putString("profit", userInfo.getPROFIT() + "").apply();// 盈利收入
			user_sp.edit().putString("profile", userInfo.getPROFILE()).apply();
			user_sp.edit().putString("worktime", userInfo.getWORK_TIME()).apply();
			user_sp.edit().putString("idnumber", userInfo.getID_NUMBER()).apply();
			user_sp.edit().putString("goodfield", userInfo.getGOOD_FIELD()).apply();
			user_sp.edit().putString("licenceNum", userInfo.getLICENCE_NUM()).apply();
			user_sp.edit().putString("layFirm", userInfo.getLAY_FIRM()).apply();
			user_sp.edit().putBoolean("isuser", false).apply();
			user_sp.edit().putString("lawyerid", userInfo.getID() + "").apply();
			user_sp.edit().putString("userid", "").apply();
			user_sp.edit().putInt("lawerLevel", userInfo.getLAWER_LEVEL()).apply();
			user_sp.edit().putString("idphotoface", userInfo.getID_PHOTO_FACE()).apply();
			user_sp.edit().putString("idphotooppo", userInfo.getID_PHOTO_OPPO()).apply();
			user_sp.edit().putString("licencephotoface", userInfo.getLICENCE_PHOTO_FACE()).apply();
			user_sp.edit().putString("licencephotooppo", userInfo.getLICENCE_PHOTO_OPPO()).apply();
			break;
		}
	}

}
