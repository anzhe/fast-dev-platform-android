package fast.dev.platform.android.activity;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.MailShareContent;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.TencentWbShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.account.LoginActivity;
import fast.dev.platform.android.activity.account.ModifyPasswordActivity;
import fast.dev.platform.android.activity.account.ProfileActivity;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.application.MyApplication;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.constant.Constants;
import fast.dev.platform.android.fragment.MainFragment;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.DeviceInfoUtils;
import fast.dev.platform.android.util.ThirdPartyUtils;

public class SettingsActivity extends BaseActivity {

	private UMSocialService mController = UMServiceFactory.getUMSocialService(Constants.DESCRIPTOR_SHARE);
	
	private View setting_01;// 个人资料
	private View setting_02;// 我要加盟
	private View setting_03;// 登录密码
	private View setting_04;// 版本更新
	private View setting_05;// 意见反馈
	private View setting_06;// 退出登录
	private View setting_pay_password;// 支付密码
	private View setting_contact;// 联系客服
	private View setting_share;// 分享
	private View join_in_container;
	private View share_box;
	
	private TextView join_tv;
	private TextView version_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Bugly
		 */
		CrashReport.setUserSceneTag(getContext(), 6123);
		
		setContentView(R.layout.activity_settings);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.action_settings));
		
		initViews();
		initData();
		initListeners();
		
		version_name.setText("v" + DeviceInfoUtils.getVersionName(getContext()));
		
		ThirdPartyUtils.addQZoneQQPlatform(SettingsActivity.this);
		ThirdPartyUtils.addSinaWeiboPlatform();
		ThirdPartyUtils.addWeixinPlatform(getContext());
//		ThirdPartyUtils.addSMS();
//		ThirdPartyUtils.addEmail();
	}

	private void initViews() {
		setting_01 = findViewById(R.id.setting_01);
		setting_02 = findViewById(R.id.setting_02);
		version_name = (TextView) findViewById(R.id.version_name);
		join_tv = (TextView) findViewById(R.id.join_tv);
		if (user_sp.getInt("lawerLevel", 0) == 1) {
			join_tv.setText("加盟信息");
		}
		setting_03 = findViewById(R.id.setting_03);
		setting_04 = findViewById(R.id.setting_04);
		setting_05 = findViewById(R.id.setting_05);
		setting_06 = findViewById(R.id.setting_06);
		setting_pay_password = findViewById(R.id.setting_pay_password);
		setting_contact = findViewById(R.id.setting_contact);
		setting_share = findViewById(R.id.setting_share);
		join_in_container = findViewById(R.id.join_in_container);
		share_box = findViewById(R.id.share_box);
		if (CommonUtils.isUser(user_sp)) {
			join_in_container.setVisibility(View.GONE);
		} else {
//			share_box.setVisibility(View.GONE);
		}
	}

	private void initData() {
		
	}

	private void initListeners() {
		setting_01.setOnClickListener(this);
		setting_02.setOnClickListener(this);
		setting_03.setOnClickListener(this);
		setting_04.setOnClickListener(this);
		setting_05.setOnClickListener(this);
		setting_06.setOnClickListener(this);
		setting_pay_password.setOnClickListener(this);
		setting_contact.setOnClickListener(this);
		setting_share.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		
		switch (v.getId()) {
		case R.id.setting_01:
			startActivity(new Intent(getContext(), ProfileActivity.class));
			break;
		case R.id.setting_03:
			startActivity(new Intent(getContext(), ModifyPasswordActivity.class));
			break;
		case R.id.setting_06:
			new AlertDialog.Builder(getContext()).setTitle("提示").setMessage("您确定要退出吗?")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							user_sp.edit().clear().apply();
							MyApplication.getInstance().exit();

							Intent intent = new Intent(getContext(), LoginActivity.class);
							startActivity(intent);
							
							MainFragment.currentPageIndex = 0;
						}

					}).setNegativeButton("取消", null).show();
			break;
		case R.id.setting_pay_password:
			
			break;
		case R.id.setting_share:
			share();
			break;
		}
	}

	private void share() {
		//mController.getConfig().setSsoHandler(new SinaSsoHandler());
		//mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
		
		String share_title = "找律师上律师同行";
		String share_content = "携手中国最权威的法律专家 打造最全面的一站式法律服务平台";
		String share_url = CommonData.SHARE_URL + user_sp.getString("MY_INVITED_CODE", "");

		mController.setShareContent(share_content);

		UMImage localImage = new UMImage(SettingsActivity.this, R.drawable.share_img);

		WeiXinShareContent weixinContent = new WeiXinShareContent();
		weixinContent.setTitle(share_title);
		weixinContent.setShareContent(share_content);
		weixinContent.setTargetUrl(share_url);
		weixinContent.setShareMedia(localImage);
		mController.setShareMedia(weixinContent);

		// 设置朋友圈分享的内容
		CircleShareContent circleMedia = new CircleShareContent();
		circleMedia.setTitle(share_title);
		circleMedia.setShareContent(share_content);
		circleMedia.setTargetUrl(share_url);
		circleMedia.setShareMedia(localImage);
		mController.setShareMedia(circleMedia);

		// 设置QQ空间分享内容
		QZoneShareContent qzone = new QZoneShareContent();
		qzone.setTitle(share_title);
		qzone.setShareContent(share_content);
		qzone.setTargetUrl(share_url);
		qzone.setShareMedia(localImage);
		mController.setShareMedia(qzone);

		QQShareContent qqShareContent = new QQShareContent();
		qqShareContent.setTitle(share_title);
		qqShareContent.setShareContent(share_content);
		qqShareContent.setTargetUrl(share_url);
		qqShareContent.setShareMedia(localImage);
		mController.setShareMedia(qqShareContent);

		// 设置腾讯微博分享内容
		TencentWbShareContent tencent = new TencentWbShareContent();
		tencent.setTitle(share_title);
		tencent.setShareContent(share_content);
		tencent.setTargetUrl(share_url);
		tencent.setShareImage(localImage);
		mController.setShareMedia(tencent);

		// 设置邮件分享内容， 如果需要分享图片则只支持本地图片
		MailShareContent mail = new MailShareContent(localImage);
		mail.setTitle(share_title);
		mail.setShareContent(share_content);
		mail.setShareImage(localImage);
		mController.setShareMedia(mail);

		// 设置短信分享内容
		SmsShareContent sms = new SmsShareContent();
		sms.setShareContent(share_content + share_url);
		mController.setShareMedia(sms);

		SinaShareContent sinaContent = new SinaShareContent();
		sinaContent.setTitle(share_title);
		sinaContent.setShareContent(share_content);
		sinaContent.setTargetUrl(share_url);
		sinaContent.setShareImage(localImage);
		mController.setShareMedia(sinaContent);
		
		mController.openShare(SettingsActivity.this, false);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == android.R.id.home) {
//            NavUtils.navigateUpFromSameTask(this);
			finishActivity();
            return true;
        }
		return super.onOptionsItemSelected(item);
	}
	
}
