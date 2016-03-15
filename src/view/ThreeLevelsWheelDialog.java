package view;

import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import fast.dev.platform.android.R;
import fast.dev.platform.android.adapter.ArrayWheelAdapter;
import fast.dev.platform.android.bean.business.BaseWheelListDataSource;
import view.select.OnWheelChangedListener;
import view.select.WheelView;

public class ThreeLevelsWheelDialog extends AlertDialog implements OnWheelChangedListener {

	/**
	 * 滚轮列表最多可见项个数
	 */
	private final int VISIBLE_ITEM_NUM = 7;
	
	private int showLevelNum = 3;// 显示的层级数
	
	private Context context;
	private View rootView;
	private WheelView mOneWheel; // 第一级滚轮
	private WheelView mTwoWheel;// 第二级滚轮
	private WheelView mThreeWheel;// 第三级滚轮
	private Button btn_confirm;
	
	private BaseWheelListDataSource dataSource;
	
	private OnConfirmListener onConfirmListener;
	
	private String mCurrentOneName;// 当前选择的第一级的名称
	private String mCurrentTwoName; // 当前选择的第二级的名称
	private String mCurrentThreeName;// 当前选择的第三级的名称
	private int currentOneIndex = 0;// 当前选择的第一级的名称
	private int currentTwoIndex = 0;// 当前选择的第二级的名称
	private int currentThreeIndex = 0;// 当前选择的第三级的名称
	private int[] oneIds;// 第一级ID集合
	private String[] oneNames;// 第一级名称集合
	private Integer[] currentTwoIds;// 当前选中的第一级对应的所有第二级的ID集合
	private String[] currentTwoNames;// 当前选中的第一级对应的所有第二级的名称集合
	private String[] currentThreeNames;// 当前选中的第二级对应的所有第三级的名称集合

	public interface OnConfirmListener {

		public void onConfirm(String mCurrentOneName, String mCurrentTwoName, String mCurrentThreeName,
				int currentOneIndex, int currentTwoIndex, int currentThreeIndex);

	}

	public ThreeLevelsWheelDialog(Context context, BaseWheelListDataSource dataSource,
			OnConfirmListener onConfirmListener, int currentOneIndex, int currentTwoIndex, int currentThreeIndex) {
		this(context, dataSource, onConfirmListener, currentOneIndex, currentTwoIndex, currentThreeIndex, null, null, null, 3);
	}

	public ThreeLevelsWheelDialog(Context context, BaseWheelListDataSource dataSource,
			OnConfirmListener onConfirmListener, int currentOneIndex, int currentTwoIndex, int currentThreeIndex,
			String mCurrentOneName, String mCurrentTwoName, String mCurrentThreeName, int showLevelNum) {
		super(context);
		super.show();
		this.onConfirmListener = onConfirmListener;
		this.dataSource = dataSource;
		this.context = context;
		this.currentOneIndex = currentOneIndex;
		this.currentTwoIndex = currentTwoIndex;
		this.currentThreeIndex = currentThreeIndex;
		this.mCurrentOneName = mCurrentOneName;
		this.mCurrentTwoName = mCurrentTwoName;
		this.mCurrentThreeName = mCurrentThreeName;
		this.showLevelNum = showLevelNum;

		setDialog();
	}

	private void setDialog() {
		initData();
		rootView = getLayoutInflater().inflate(R.layout.three_levels_wheel_dialog, null);
		initViews();
		super.setContentView(rootView);
	}

	private void initData() {
		oneIds = dataSource.getOneIds();
		oneNames = dataSource.getOneNames();
	}

	private void showOneLevel() {
		mOneWheel = (WheelView) rootView.findViewById(R.id.one);
		mOneWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, oneNames));
		mOneWheel.addChangingListener(this);
		mOneWheel.setVisibleItems(VISIBLE_ITEM_NUM);
		
		if (mCurrentOneName != null) {
			for (int i = 0; i < oneNames.length; i++) {
				if (TextUtils.equals(mCurrentOneName, oneNames[i])) {
					currentOneIndex = i;
					break;
				}
			}
		}
		mOneWheel.setCurrentItem(currentOneIndex);
	}
	
	private void showTwoLevel() {
		mTwoWheel = (WheelView) rootView.findViewById(R.id.two);
		mTwoWheel.setVisibility(View.VISIBLE);
		mTwoWheel.addChangingListener(this);
		mTwoWheel.setVisibleItems(VISIBLE_ITEM_NUM);
		
		if (mCurrentTwoName != null) {
			Map<String, Object[]> namesAndIds = dataSource.getChildrenNamesAndIdsViaParentId(oneIds[currentOneIndex]);
			currentTwoIds = (Integer[]) namesAndIds.get("ids");
			currentTwoNames = (String[]) namesAndIds.get("names");
			for (int i = 0; i < currentTwoNames.length; i++) {
				if (TextUtils.equals(mCurrentTwoName, currentTwoNames[i])) {
					currentTwoIndex = i;
					break;
				}
			}
		}
	}
	
	private void showThreeLevel() {
		mThreeWheel = (WheelView) rootView.findViewById(R.id.three);
		mThreeWheel.setVisibility(View.VISIBLE);
		mThreeWheel.addChangingListener(this);
		mThreeWheel.setVisibleItems(VISIBLE_ITEM_NUM);
		
		if (mCurrentThreeName != null) {
			Map<String, Object[]> namesAndIds = dataSource.getChildrenNamesAndIdsViaParentId(currentTwoIds[currentTwoIndex]);
			currentThreeNames = (String[]) namesAndIds.get("names");
			for (int i = 0; i < currentThreeNames.length; i++) {
				if (TextUtils.equals(mCurrentThreeName, currentThreeNames[i])) {
					currentThreeIndex = i;
					break;
				}
			}
		}
	}
	
	protected void initViews() {
		btn_confirm = (Button) rootView.findViewById(R.id.btn_confirm);
		btn_confirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onConfirmListener.onConfirm(mCurrentOneName, mCurrentTwoName, mCurrentThreeName, currentOneIndex, currentTwoIndex, currentThreeIndex);
			}
			
		});
		
		showOneLevel();
		if (oneNames.length > currentOneIndex) {
			mCurrentOneName = oneNames[currentOneIndex];
		} else {
			mCurrentOneName = "";
		}
		
		if (showLevelNum >= 2) {
			showTwoLevel();
			
			if (currentTwoNames == null) {
				Map<String, Object[]> namesAndIds = dataSource.getChildrenNamesAndIdsViaParentId(oneIds[currentOneIndex]);
				currentTwoIds = (Integer[]) namesAndIds.get("ids");
				currentTwoNames = (String[]) namesAndIds.get("names");
			}
			
			mTwoWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, currentTwoNames));
			mTwoWheel.setCurrentItem(currentTwoIndex);
			if (currentTwoNames.length > currentTwoIndex) {
				mCurrentTwoName = currentTwoNames[currentTwoIndex];
			} else {
				mCurrentTwoName = "";
			}
		}
		
		if (showLevelNum == 3) {
			showThreeLevel();
			
			if (currentThreeNames == null) {
				Map<String, Object[]> namesAndIds = dataSource.getChildrenNamesAndIdsViaParentId(currentTwoIds[currentTwoIndex]);
				currentThreeNames = (String[]) namesAndIds.get("names");
			}
			
			mThreeWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, currentThreeNames));
			mThreeWheel.setCurrentItem(currentThreeIndex);
			if (currentThreeNames.length > currentThreeIndex) {
				mCurrentThreeName = currentThreeNames[currentThreeIndex];
			} else {
				mCurrentThreeName = "";
			}
		}
	}

	/**
	 * 刷新第二级滚轮列表数据
	 */
	private void updateTwo() {
		currentOneIndex = mOneWheel.getCurrentItem();
		if (oneNames.length > 0) {
			mCurrentOneName = oneNames[currentOneIndex];
		} else {
			mCurrentOneName = "";
		}
		
		if (mTwoWheel != null) {
			if (showLevelNum >= 2) {
				Map<String, Object[]> namesAndIds = dataSource.getChildrenNamesAndIdsViaParentId(oneIds[currentOneIndex]);
				currentTwoIds = (Integer[]) namesAndIds.get("ids");
				currentTwoNames = (String[]) namesAndIds.get("names");
				
				mTwoWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, currentTwoNames));
				mTwoWheel.setCurrentItem(0);
				
				updateThree();
			}
		}
	}
	
	/**
	 * 刷新第三级滚轮列表数据
	 */
	private void updateThree() {
		currentTwoIndex = mTwoWheel.getCurrentItem();
		if (currentTwoNames.length > 0) {
			mCurrentTwoName = currentTwoNames[currentTwoIndex];
		} else {
			mCurrentTwoName = "";
		}
		
		if (mThreeWheel != null) {
			if (showLevelNum == 3) {
				Map<String, Object[]> namesAndIds = dataSource.getChildrenNamesAndIdsViaParentId(currentTwoIds[currentTwoIndex]);
				currentThreeNames = (String[]) namesAndIds.get("names");
				
				mThreeWheel.setViewAdapter(new ArrayWheelAdapter<String>(context, currentThreeNames));
				mThreeWheel.setCurrentItem(0);
				if (currentThreeNames.length > 0) {
					mCurrentThreeName = currentThreeNames[0];
				} else {
					mCurrentThreeName = "";
				}
			}
		}
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mOneWheel) {
			updateTwo();
		} else if (wheel == mTwoWheel) {
			updateThree();
		} else if (wheel == mThreeWheel) {
			mCurrentThreeName = currentThreeNames[newValue];
			currentThreeIndex = newValue;
		}
	}

}
