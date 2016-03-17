package fast.dev.platform.android.fragment.second;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.datasource.TypeList;
import fast.dev.platform.android.fragment.base.BaseFragment;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.InputValidateUtils;
import view.WheelDialog;
import view.WheelDialog.OnConfirmBack;

public class HotspotFragment extends BaseFragment {

	private TextView consult_tab;
	private TextView case_entrustment_tab;
	private TextView writ_service_tab;
	private EditText search_et;
	private Button search_btn;
	private WheelDialog type_dialog;

	private int type_pCurrent = 0;;// 对话框回显字段
	private int type_cCurrent = 0;// 子级滚动框回显

	private Fragment currentFragment;

	private String selectedCaseType = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_hotspot, container, false);
		
		initData();
		initViews(root);
		initListeners();
		
		return root;
	}

	private void initListeners() {
		consult_tab.setOnClickListener(this);
		case_entrustment_tab.setOnClickListener(this);
		writ_service_tab.setOnClickListener(this);
		search_btn.setOnClickListener(this);
	}

	private void initViews(View root) {
		consult_tab = (TextView) root.findViewById(R.id.consult_tab);
		case_entrustment_tab = (TextView) root.findViewById(R.id.case_entrustment_tab);
		writ_service_tab = (TextView) root.findViewById(R.id.writ_service_tab);

		search_et = (EditText) root.findViewById(R.id.search_et);
		search_btn = (Button) root.findViewById(R.id.search_btn);

//		currentFragment = new ConsultFragment();
//		((ConsultFragment) currentFragment).setFilter(search_et, selectedCaseType);
		
		getChildFragmentManager().beginTransaction().add(R.id.hotspot_container, currentFragment).commitAllowingStateLoss();
	}

	private void initData() {
		
	}
	
	@Override
	public void onClick(View v) {/*
		super.onClick(v);
		
		switch (v.getId()) {
		case R.id.consult_tab:
			currentFragment = new ConsultFragment();
			((ConsultFragment) currentFragment).setFilter(search_et, selectedCaseType);
			getChildFragmentManager().beginTransaction().replace(R.id.hotspot_container, currentFragment).commitAllowingStateLoss();
			consult_tab.setTextColor(getResources().getColor(R.color.app_primary_color));
			case_entrustment_tab.setTextColor(Color.BLACK);
			writ_service_tab.setTextColor(Color.BLACK);
			break;
		case R.id.case_entrustment_tab:
			currentFragment = new CaseEntrustmentFragment();
			((CaseEntrustmentFragment) currentFragment).setFilter(search_et, selectedCaseType);
			getChildFragmentManager().beginTransaction().replace(R.id.hotspot_container, currentFragment).commitAllowingStateLoss();
			consult_tab.setTextColor(Color.BLACK);
			case_entrustment_tab.setTextColor(getResources().getColor(R.color.app_primary_color));
			writ_service_tab.setTextColor(Color.BLACK);
			break;
		case R.id.writ_service_tab:
			currentFragment = new WritServiceFragment();
			((WritServiceFragment) currentFragment).setFilter(search_et, selectedCaseType);
			getChildFragmentManager().beginTransaction().replace(R.id.hotspot_container, currentFragment).commitAllowingStateLoss();
			consult_tab.setTextColor(Color.BLACK);
			case_entrustment_tab.setTextColor(Color.BLACK);
			writ_service_tab.setTextColor(getResources().getColor(R.color.app_primary_color));
			break;
		case R.id.search_btn:
			CommonUtils.hideSoftKeyboard(search_et);
			if (!InputValidateUtils.validate(getContext(), search_et.getText().toString(), "关键字")) {
				break;
			}
			if (currentFragment instanceof ConsultFragment) {
				((ConsultFragment) currentFragment).refreshData(selectedCaseType);
			} else if (currentFragment instanceof CaseEntrustmentFragment) {
				((CaseEntrustmentFragment) currentFragment).refreshData(selectedCaseType);
			} else if (currentFragment instanceof WritServiceFragment) {
				((WritServiceFragment) currentFragment).refreshData(selectedCaseType);
			}
			break;
		}
	*/}

}
