package fast.dev.platform.android.location;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

import android.content.Context;

public class BaiduRequestLocation implements BDLocationListener {

	private Context mContext;
	private LocationListener locationListener;

	private LocationClient mLocationClient;
	private LocationMode tempMode = LocationMode.Hight_Accuracy;
	private String tempcoor = "gcj02";
	private boolean isNeedAddress = true;
	private int scanSpan = 120000;// 120秒轮询定位

	public BaiduRequestLocation(Context context) {
		this.mContext = context;
		mLocationClient = new LocationClient(mContext);
		mLocationClient.registerLocationListener(this);
		initLocation();
	}

	/**
	 * 开始定位
	 * 
	 * @param locationListener
	 */
	public void location(LocationListener locationListener) {
		this.locationListener = locationListener;
		mLocationClient.start();// 定位SDK start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
		//mLocationClient.requestLocation();
	}

	/**
	 * 停止定位
	 */
	public void cancelLocation() {
		mLocationClient.stop();
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		switch (location.getLocType()) {
		case BDLocation.TypeGpsLocation:
		case BDLocation.TypeNetWorkLocation:
		case BDLocation.TypeOffLineLocation:
			locationListener.located(location, true);
			return;
		case BDLocation.TypeServerError:
//			ToastUtils.showShort(mContext, "服务端网络定位失败");
			break;
		case BDLocation.TypeNetWorkException:
//			ToastUtils.showShort(mContext, "网络不同导致定位失败，请检查网络是否通畅");
			break;
		case BDLocation.TypeCriteriaException:
//			ToastUtils.showShort(mContext, "无法获取有效定位依据导致定位失败");
			break;
		default:
//			ToastUtils.showShort(mContext, "定位失败");
			break;
		}
		locationListener.located(location, false);
	}

	/**
	 * 初始化定位
	 */
	private void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(tempMode);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		option.setCoorType(tempcoor);// 可选，默认gcj02，设置返回的定位结果坐标系，
		option.setScanSpan(scanSpan);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		option.setIsNeedAddress(isNeedAddress);// 可选，设置是否需要地址信息，默认不需要
		//option.setOpenGps(true);// 可选，默认false,设置是否使用gps
		//option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIgnoreKillProcess(true);// 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

		mLocationClient.setLocOption(option);
	}

	public interface LocationListener {

		/**
		 * 定位回调方法
		 * 
		 * @param location
		 * @param located 是否定位成功
		 */
		public void located(BDLocation location, boolean located);

	}

}
