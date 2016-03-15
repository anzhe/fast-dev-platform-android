package fast.dev.platform.android.activity.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.imageloader.PicassoImageLoader;
import fast.dev.platform.android.location.BaiduRequestLocation;
import fast.dev.platform.android.util.CommonUtils;

public class BaseActivity extends FragmentActivity {

	protected VolleyWrapper volleyWrapper;
	
	protected SharedPreferences user_sp;// 用户信息数据库
	protected SharedPreferences dict_sp;// 字典信息数据库
	protected SharedPreferences sys_sp;// 系统信息数据库
	protected SharedPreferences location_sp;// 定位信息数据库
	
	private BaiduRequestLocation mBaiduRequestLocation;// 请求定位-百度地图
	
	protected PicassoImageLoader mImageLoader;// 图片加载器-Picasso

	public Context getContext() {
		return this;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mBaiduRequestLocation = new BaiduRequestLocation(getContext());
		
		mImageLoader = new PicassoImageLoader(getContext());
		
		user_sp = CommonUtils.user_sp(getContext());
		dict_sp = CommonUtils.dict_sp(getContext());
		sys_sp = CommonUtils.sys_sp(getContext());
		location_sp = CommonUtils.location_sp(getContext());
		
		volleyWrapper = new VolleyWrapper(getContext());
	}
	
}
