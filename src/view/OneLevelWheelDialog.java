package view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import fast.dev.platform.android.R;
import fast.dev.platform.android.adapter.ArrayWheelAdapter;
import fast.dev.platform.android.bean.business.BaseWheelListDataSource;
import view.select.OnWheelChangedListener;
import view.select.WheelView;

public class OneLevelWheelDialog extends AlertDialog implements OnWheelChangedListener {

	private WheelView mProvince; // 省滚轮
	private String[] mProvinceData; // 存放省字符串数组
	private View menuCity;
	private Button btnConfirm;
	private Context context;
	private BaseWheelListDataSource dataSource;
	private OnConfirmBack onConfirmBack;
	private int pCurrent = 0;
	
	/**
	 * 滚轮列表最多可见项个数
	 */
	private final int VISIBLE_ITEM_NUM = 7;

	public interface OnConfirmBack {

		public void confirmback(String mCurrentProviceName, int pCurrent);

	}

	public OneLevelWheelDialog(Context context, BaseWheelListDataSource dataSource, OnConfirmBack onConfirmBack, int pCurrent) {
		super(context);
		super.show();

		this.onConfirmBack = onConfirmBack;
		this.dataSource = dataSource;
		this.context = context;
		this.pCurrent = pCurrent;
		setDialog();
	}

	private void setDialog() {
		initData(dataSource);// 初始化省、市数据
		menuCity = getLayoutInflater().inflate(R.layout.one_level_wheel_dialog, null);
		initCityView();
		super.setContentView(menuCity);
	}

	/**
	 * 初始化城市选择对话框数据
	 */
	private void initData(BaseWheelListDataSource dataSource) {
		if (mProvinceData == null) {
			mProvinceData = dataSource.getProviceData();
		}
	}

	protected void initCityView() {
		mProvince = (WheelView) menuCity.findViewById(R.id.id_province);
		btnConfirm = (Button) menuCity.findViewById(R.id.btnConfirm);

		mProvince.setViewAdapter(new ArrayWheelAdapter<String>(context, mProvinceData));
		// 设置默认的选中的
		mProvince.setCurrentItem(pCurrent);
		// 添加change事件
		mProvince.addChangingListener(this);

		// 显示的条目
		mProvince.setVisibleItems(VISIBLE_ITEM_NUM);
		btnConfirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				onConfirmBack.confirmback(mProvinceData[pCurrent], pCurrent);
			}

		});
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		pCurrent = newValue;
	}

}
