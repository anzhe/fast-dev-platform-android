package fast.dev.platform.android.util;

import com.umeng.socialize.sso.EmailHandler;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import android.app.Activity;
import android.content.Context;
import fast.dev.platform.android.constant.CommonData;

public class ThirdPartyUtils {

	// 添加QQ支持, 并且设置QQ分享内容的target url
	public static void addQZoneQQPlatform(Activity activity) {
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity, CommonData.QQ_APP_ID, CommonData.QQ_APP_KEY);
		qqSsoHandler.addToSocialSDK();

		// 添加QZone平台
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, CommonData.QQ_APP_ID, CommonData.QQ_APP_KEY);
		qZoneSsoHandler.addToSocialSDK();
	}
	
	// 设置新浪SSO handler
	public static void addSinaWeiboPlatform() {
		//mController.getConfig().setSsoHandler(new SinaSsoHandler());
	}
	
	// 添加微信平台
	public static void addWeixinPlatform(Context context) {
		UMWXHandler wxHandler = new UMWXHandler(context, CommonData.WX_APP_ID, CommonData.WX_API_SECRET);
		wxHandler.addToSocialSDK();
		// 添加微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(context, CommonData.WX_APP_ID, CommonData.WX_API_SECRET);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
	}
	
	/**
	 * 添加短信平台
	 */
	public static void addSMS() {
		SmsHandler smsHandler = new SmsHandler();
		smsHandler.addToSocialSDK();
	}

	/**
	 * 添加Email平台
	 */
	public static void addEmail() {
		EmailHandler emailHandler = new EmailHandler();
		emailHandler.addToSocialSDK();
	}
	
}
