package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import fast.dev.platform.android.R;
import fast.dev.platform.android.adapter.ArrayWheelAdapter;
import fast.dev.platform.android.bean.business.BaseWheelListDataSource;
import view.select.OnWheelChangedListener;
import view.select.WheelView;

public class WheelDialog extends AlertDialog implements OnWheelChangedListener {

	private WheelView mProvince; // 省滚轮
	private WheelView mCity;// 市滚轮
	// 存放省->市(全部) 的键值关系的Map
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	private String[] mProvinceDatas; // 存放省字符串数组
	private String mCurrentProviceName;// 当前选择的省
	private String mCurrentCityName; // 当前选择的市
	private View menuCity;
	private Button btnConfirm;
	private Context context;
	private BaseWheelListDataSource dataSource;
	private OnConfirmBack onConfirmBack;
	private int pCurrent = 0;
	private int cCurrent = 0;
	private int startPosition = 0;
	
	/**
	 * 滚轮列表最多可见项个数
	 */
	private final int VISIBLE_ITEM_NUM = 7;

	public interface OnConfirmBack {

		public void confirmback(String mCurrentProviceName, String mCurrentCityName, int pCurrent, int cCurrent);

	}

	public WheelDialog(Context context, BaseWheelListDataSource dataSource, OnConfirmBack onConfirmBack, int pCurrent, int cCurrent, int startPosition) {
		super(context);
		super.show();

		this.onConfirmBack = onConfirmBack;
		this.dataSource = dataSource;
		this.context = context;
		this.pCurrent = pCurrent;
		this.cCurrent = cCurrent;
		this.startPosition = startPosition;
		setDialog();
	}

	private void setDialog() {
		initData(dataSource);// 初始化省、市数据
		menuCity = getLayoutInflater().inflate(R.layout.city, null);
		initCityView();
		super.setContentView(menuCity);
	}

	/**
	 * 初始化城市选择对话框数据
	 */
	private void initData(BaseWheelListDataSource dataSource) {
		try {
			if (mProvinceDatas == null) {
				if (startPosition == 0) {
					mProvinceDatas = dataSource.getProviceData();
				} else {
					List<String> list = Arrays.asList(dataSource.getProviceData());
					ArrayList<String> dataList = new ArrayList<String>(list);
					dataList.remove(0);
					mProvinceDatas = dataList.toArray(new String[dataList.size()]);
				}

				JSONObject city = dataSource.getCityData();
				for (int i = 0; i < mProvinceDatas.length; i++) {
					String pName = mProvinceDatas[i];// 每个省的json对象
					String[] mCitiesDatas = city.getString(pName).split("\\+");
					mCitisDatasMap.put(pName, mCitiesDatas);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	protected void initCityView() {
		mProvince = (WheelView) menuCity.findViewById(R.id.id_province);
		mCity = (WheelView) menuCity.findViewById(R.id.id_city);
		btnConfirm = (Button) menuCity.findViewById(R.id.btnConfirm);

		mProvince.setViewAdapter(new ArrayWheelAdapter<String>(context, mProvinceDatas));
		// 设置默认的选中的
		mProvince.setCurrentItem(pCurrent);
		// 添加change事件
		mProvince.addChangingListener(this);
		// 添加change事件
		mCity.addChangingListener(this);

		// 显示的条目
		mProvince.setVisibleItems(VISIBLE_ITEM_NUM);
		mCity.setVisibleItems(VISIBLE_ITEM_NUM);
		// 一开始是需要根据当前选择的省来初始化市的
		updateCities();
		btnConfirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				onConfirmBack.confirmback(mCurrentProviceName, mCurrentCityName, pCurrent, cCurrent);
			}

		});
	}

	private void updateCities() {
		pCurrent = mProvince.getCurrentItem(); // 获取当前省选择的位置
		mCurrentProviceName = mProvinceDatas[pCurrent];// 根据位置获取省的名字
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);// 根据省来获取当前省的市
		if (mCitisDatasMap != null && cities != null) {
			mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[0];
		}
		if (cities == null) {
			cities = new String[] { "" };
		}
		mCity.setViewAdapter(new ArrayWheelAdapter<String>(context, cities));

		mCity.setCurrentItem(cCurrent);
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mProvince) {
			cCurrent = 0;
			updateCities();
		} else if (wheel == mCity) {
			mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[newValue];
			cCurrent = newValue;
		}
	}

	@Override
	public void dismiss() {
		super.dismiss();
		// if (mProvince!=null) {
		// mProvince =null;
		// mCity= null;
		// mCitisDatasMap = null;
		// }
	}

	public void setDialogSelectedItem(int pCurrent, int cCurrent) {
		this.pCurrent = pCurrent;
		this.cCurrent = cCurrent;
		initCityView();
	}
	
}
