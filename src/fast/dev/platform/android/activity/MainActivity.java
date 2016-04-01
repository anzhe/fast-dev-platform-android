package fast.dev.platform.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.amap.api.location.AMapLocation;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.MailShareContent;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.TencentWbShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.account.LoginActivity;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.application.MyApplication;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.constant.Constants;
import fast.dev.platform.android.fragment.CheeseListFragment;
import fast.dev.platform.android.fragment.find.FindFragment;
import fast.dev.platform.android.location.GaodeRequestLocation;
import fast.dev.platform.android.location.GaodeRequestLocation.LocationCallback;
import fast.dev.platform.android.util.ThirdPartyUtils;
import fast.dev.platform.android.util.ToastUtils;

public class MainActivity extends BaseActivity {

	private DrawerLayout mDrawerLayout;
	private FloatingActionButton mFloatingActionButton;
	private TextView location_tv;

	private UMSocialService umSocialService = UMServiceFactory.getUMSocialService(Constants.DESCRIPTOR_SHARE);
	
	private int back_clicked_count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		location_tv = (TextView) findViewById(R.id.location);
		GaodeRequestLocation gaodeRequestLocation = new GaodeRequestLocation(getContext(), true);
		gaodeRequestLocation.location(new LocationCallback() {
			
			@Override
			public void callback(AMapLocation location, boolean located) {
				if (located) {
					ToastUtils.showLong(getContext(), location.getProvince() + "-" + location.getCity() + "-" + location.getDistrict());
					location_tv.setText(location.getProvince() + "-" + location.getCity() + "-" + location.getDistrict());
				}
			}
			
		});
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		if (navigationView != null) {
			setupDrawerContent(navigationView);
		}

		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
		if (viewPager != null) {
			setupViewPager(viewPager);
		}

		mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
		mFloatingActionButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}

		});

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);
		
		ThirdPartyUtils.addQZoneQQPlatform(MainActivity.this);
		ThirdPartyUtils.addSinaWeiboPlatform();
		ThirdPartyUtils.addWeixinPlatform(getContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mDrawerLayout.openDrawer(GravityCompat.START);
			return true;
		case R.id.action_settings:
			startActivity(new Intent(getContext(), SettingsActivity.class));
			return true;
		case R.id.action_share:
			share();
			return true;
		case R.id.action_logout:
			user_sp.edit().clear().apply();
			startActivity(new Intent(getContext(), LoginActivity.class));
			finishActivity();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setupViewPager(ViewPager viewPager) {
		Adapter adapter = new Adapter(getSupportFragmentManager());
		adapter.addFragment(new CheeseListFragment(), "首页");
		adapter.addFragment(new FindFragment(), "发现");
		adapter.addFragment(new CheeseListFragment(), "热门");
		adapter.addFragment(new CheeseListFragment(), "地区");
		adapter.addFragment(new CheeseListFragment(), "地图");
		viewPager.setAdapter(adapter);
	}

	private void setupDrawerContent(NavigationView navigationView) {
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

			@Override
			public boolean onNavigationItemSelected(MenuItem menuItem) {
				menuItem.setChecked(true);
				mDrawerLayout.closeDrawers();
				return true;
			}

		});
	}

	static class Adapter extends FragmentPagerAdapter {

		private final List<Fragment> mFragments = new ArrayList<>();
		private final List<String> mFragmentTitles = new ArrayList<>();

		public Adapter(FragmentManager fm) {
			super(fm);
		}

		public void addFragment(Fragment fragment, String title) {
			mFragments.add(fragment);
			mFragmentTitles.add(title);
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mFragmentTitles.get(position);
		}

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			back_clicked_count++;
			if (back_clicked_count == 2) {
				MyApplication.getInstance().exit();
			} else {
//				ToastUtils.showShort(getContext(), "再按一次退出");
				Snackbar.make(mFloatingActionButton, "再按一次退出", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
			}
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					back_clicked_count = 0;
				}
				
			}, 1500);
			return false;
		}

		return super.onKeyDown(keyCode, event);
	}
	
	private void share() {
		//mController.getConfig().setSsoHandler(new SinaSsoHandler());
		//mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
		
		String share_title = "快速开发平台-安卓";
		String share_content = "仅供学习 切勿模仿 珍惜生命 远离挨踢";
		String share_url = CommonData.SHARE_URL + user_sp.getString("MY_INVITED_CODE", "");

		umSocialService.setShareContent(share_content);

		UMImage localImage = new UMImage(getContext(), R.drawable.app_logo);

		WeiXinShareContent weixinContent = new WeiXinShareContent();
		weixinContent.setTitle(share_title);
		weixinContent.setShareContent(share_content);
		weixinContent.setTargetUrl(share_url);
		weixinContent.setShareMedia(localImage);
		umSocialService.setShareMedia(weixinContent);

		// 设置朋友圈分享的内容
		CircleShareContent circleMedia = new CircleShareContent();
		circleMedia.setTitle(share_title);
		circleMedia.setShareContent(share_content);
		circleMedia.setTargetUrl(share_url);
		circleMedia.setShareMedia(localImage);
		umSocialService.setShareMedia(circleMedia);

		// 设置QQ空间分享内容
		QZoneShareContent qzone = new QZoneShareContent();
		qzone.setTitle(share_title);
		qzone.setShareContent(share_content);
		qzone.setTargetUrl(share_url);
		qzone.setShareMedia(localImage);
		umSocialService.setShareMedia(qzone);

		QQShareContent qqShareContent = new QQShareContent();
		qqShareContent.setTitle(share_title);
		qqShareContent.setShareContent(share_content);
		qqShareContent.setTargetUrl(share_url);
		qqShareContent.setShareMedia(localImage);
		umSocialService.setShareMedia(qqShareContent);

		// 设置腾讯微博分享内容
		TencentWbShareContent tencent = new TencentWbShareContent();
		tencent.setTitle(share_title);
		tencent.setShareContent(share_content);
		tencent.setTargetUrl(share_url);
		tencent.setShareImage(localImage);
		umSocialService.setShareMedia(tencent);

		// 设置邮件分享内容， 如果需要分享图片则只支持本地图片
		MailShareContent mail = new MailShareContent(localImage);
		mail.setTitle(share_title);
		mail.setShareContent(share_content);
		mail.setShareImage(localImage);
		umSocialService.setShareMedia(mail);

		// 设置短信分享内容
		SmsShareContent sms = new SmsShareContent();
		sms.setShareContent(share_content + share_url);
		umSocialService.setShareMedia(sms);

		SinaShareContent sinaContent = new SinaShareContent();
		sinaContent.setTitle(share_title);
		sinaContent.setShareContent(share_content);
		sinaContent.setTargetUrl(share_url);
		sinaContent.setShareImage(localImage);
		umSocialService.setShareMedia(sinaContent);
		
		umSocialService.openShare(MainActivity.this, false);
	}
	
}
