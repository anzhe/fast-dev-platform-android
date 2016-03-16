package fast.dev.platform.android.location;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.tencent.bugly.crashreport.CrashReport;

import android.content.Context;
import android.util.Log;
import fast.dev.platform.android.constant.Constants;

public class GaodeRequestLocation implements AMapLocationListener {

	/**
	 * 定位间隔，单位毫秒
	 */
	private final long LOCATION_INTERVAL = 3000;
	
	private Context mContext;

	public AMapLocationClient mLocationClient = null;
	public AMapLocationClientOption mLocationClientOption = null;
	private LocationCallback mLocationCallback;

	/**
	 * 是否只定位一次
	 */
	private boolean locate_once = false;
	
	public GaodeRequestLocation(Context context, boolean locate_once) {
		this.mContext = context;
		this.locate_once = locate_once;
		initLocation();
	}

	/**
	 * 初始化定位
	 */
	private void initLocation() {
		AMapLocationClient.setApiKey(Constants.GAODE_API_KEY);
		
		// 初始化定位
		mLocationClient = new AMapLocationClient(this.mContext);

		// 初始化定位参数
		mLocationClientOption = new AMapLocationClientOption();
		// 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
		mLocationClientOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
		// 设置是否返回地址信息（默认返回地址信息）
		mLocationClientOption.setNeedAddress(true);
		// 设置是否只定位一次,默认为false
		mLocationClientOption.setOnceLocation(locate_once);
		// 设置是否强制刷新WIFI，默认为强制刷新
		mLocationClientOption.setWifiActiveScan(true);
		// 设置是否允许模拟位置,默认为false，不允许模拟位置
		mLocationClientOption.setMockEnable(false);
		// 设置定位间隔,单位毫秒,默认为2000ms
		mLocationClientOption.setInterval(LOCATION_INTERVAL);
		// 给定位客户端对象设置定位参数
		mLocationClient.setLocationOption(mLocationClientOption);
		
		// 设置定位回调监听
		mLocationClient.setLocationListener(this);
	}

	/**
	 * 开始定位
	 * 
	 * @param locationCallback
	 */
	public void location(LocationCallback locationCallback) {
		this.mLocationCallback = locationCallback;
		mLocationClient.startLocation();
	}

	/**
	 * 停止定位
	 */
	public void cancelLocation() {
		mLocationClient.stopLocation();
	}

	/**
	 * 销毁定位客户端
	 */
	public void destroyLocationClient() {
		mLocationClient.onDestroy();
	}

	@Override
	public void onLocationChanged(AMapLocation location) {
		if (location != null) {
			if (location.getErrorCode() == 0) {
				// 定位成功回调信息，设置相关消息
				location.getLocationType();// 获取当前定位结果来源，如网络定位结果，详见定位类型表
				location.getLatitude();// 获取纬度
				location.getLongitude();// 获取经度
				location.getAccuracy();// 获取精度信息
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(location.getTime());
				df.format(date);// 定位时间
				location.getAddress();// 地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
				location.getCountry();// 国家信息
				location.getProvince();// 省信息
				location.getCity();// 城市信息
				location.getDistrict();// 城区信息
				location.getStreet();// 街道信息
				location.getStreetNum();// 街道门牌号信息
				location.getCityCode();// 城市编码
				location.getAdCode();// 地区编码
				location.getAoiName();// 获取当前定位点的AOI信息

				if (this.mLocationCallback != null) {
					this.mLocationCallback.callback(location, true);
				}
			} else {
				if (this.mLocationCallback != null) {
					this.mLocationCallback.callback(location, false);
				}

				// 显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
				Log.e("AmapError",
						"location Error, ErrCode:" + location.getErrorCode() + ", errInfo:" + location.getErrorInfo());

				try {
					throw new RuntimeException("高德定位失败--location Error, ErrCode:" + location.getErrorCode()
							+ ", errInfo:" + location.getErrorInfo());
				} catch (Exception e) {
					CrashReport.postCatchedException(e);
				}
			}
		} else {
			if (this.mLocationCallback != null) {
				this.mLocationCallback.callback(location, false);
			}
			
			try {
				throw new RuntimeException("高德定位失败--location Error");
			} catch (Exception e) {
				CrashReport.postCatchedException(e);
			}
		}
	}

	public interface LocationCallback {

		/**
		 * 定位回调方法
		 * 
		 * @param location
		 * @param located 是否定位成功
		 */
		public void callback(AMapLocation location, boolean located);

	}

}
