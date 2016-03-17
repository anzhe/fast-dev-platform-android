package fast.dev.platform.android.fragment.find;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.android.volley.Response.Listener;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.bean.LawyerBean;
import fast.dev.platform.android.bean.base.BasePagedData;
import fast.dev.platform.android.bean.business.LawyerBusinessBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.fragment.base.BaseFragment;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.util.BrowsingHistoryUtils;
import fast.dev.platform.android.util.CommonUtils;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import view.ptr.MyLoadMoreContainer;
import view.ptr.MyLoadMoreHandler;
import view.ptr.MyLoadMoreListViewContainer;

public class FindFragment extends BaseFragment {

	private View btn_find;
	private View btn_calculate;

	private PtrFrameLayout mPtrFrameLayout;
	private MyLoadMoreListViewContainer myLoadMoreListViewContainer;
    private ListView mListView;

	private int page_no = 1;

	private List<LawyerBean> lawyerBeans = new ArrayList<LawyerBean>();
	private LawyerListAdapter lawyerListAdapter;

	private HashMap<String, String> queryParams = new HashMap<String, String>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_find, container, false);

		initViews(root);
		initData();
		initListeners();
		
		queryParams.put("province", location_sp.getString("province", ""));
		queryParams.put("city", location_sp.getString("city", ""));
		queryParams.put("lawerlever", "");
//		getLawyerList(true, true);
		
		return root;
	}

	@Override
	public void onResume() {
		super.onResume();
		
		queryParams.put("pageno", "1");
		queryParams.put("pagesize", (page_no * 10) + "");
		getLawyerList(true, true);
	}
	
	private void initViews(View root) {
		btn_find = root.findViewById(R.id.btn_find);
		btn_calculate = root.findViewById(R.id.btn_calculate);

		mPtrFrameLayout = (PtrFrameLayout) root.findViewById(R.id.load_more_list_view_ptr_frame);
		mPtrFrameLayout.disableWhenHorizontalMove(true);
		mPtrFrameLayout.setLoadingMinTime(1000);
		/*mPtrFrameLayout.postDelayed(new Runnable() {

			@Override
			public void run() {
				mPtrFrameLayout.autoRefresh(false);
			}

		}, 150);*/
		mPtrFrameLayout.setPtrHandler(new PtrHandler() {

			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
				return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
			}

			@Override
			public void onRefreshBegin(PtrFrameLayout frame) {
				page_no = 1;
				queryParams.put("pageno", page_no + "");
				queryParams.put("pagesize", "10");
				getLawyerList(false, true);
			}

		});

		mListView = (ListView) root.findViewById(R.id.load_more_small_image_list_view);

		View headerMarginView = new View(getContext());
		headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));
		mListView.addHeaderView(headerMarginView);

		myLoadMoreListViewContainer = (MyLoadMoreListViewContainer) root.findViewById(R.id.load_more_list_view_container);
		myLoadMoreListViewContainer.useDefaultFooter();
		myLoadMoreListViewContainer.setAutoLoadMore(true);

		lawyerListAdapter = new LawyerListAdapter();
		mListView.setAdapter(lawyerListAdapter);
		myLoadMoreListViewContainer.setLoadMoreHandler(new MyLoadMoreHandler() {

			@Override
			public void onLoadMore(MyLoadMoreContainer loadMoreContainer) {
				page_no = page_no + 1;
				queryParams.put("pageno", page_no + "");
				queryParams.put("pagesize", "10");
				getLawyerList(false, false);
			}

		});
	}

	private void initListeners() {
		btn_find.setOnClickListener(this);
		btn_calculate.setOnClickListener(this);
	}
	
	private void initData() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent();
				LawyerBean lawyerBean = lawyerBeans.get(position - 1);
				intent.putExtra("selected_lawyer", lawyerBean);
//				if (lawyerBean.getLAWER_LEVEL() == CommonData.LAWYER_LEVEL_OFFICIAL_LEGAL_TEAM) {
//					intent.setClass(getActivity(), OfficialLegalTeamDetailActivity.class);
//				} else {
//					intent.setClass(getActivity(), LawyerDetailActivity.class);
//				}
				startActivity(intent);
				
				String userId = null;
				if (CommonUtils.isLawyer(user_sp)) {
					userId = user_sp.getString("lawyerid", "");
				} else {
					userId = user_sp.getString("userid", "");
				}
				BrowsingHistoryUtils.record(userId, lawyerBean.getLAWERID() + "", 6);
			}

		});
	}
	
	@Override
	public void onClick(View v) {/*
		switch (v.getId()) {
		case R.id.btn_find:
			startActivity(new Intent(getActivity(), FindLawyerActivity.class));
			break;
		case R.id.btn_calculate:
			startActivity(new Intent(getActivity(), CalculateCostsActivity.class));
			break;
		}
	*/}

	private class LawyerListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return lawyerBeans.size();
		}

		@Override
		public LawyerBean getItem(int position) {
			return lawyerBeans.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return null;
		}

	}

	/**
	 * 获取律师列表
	 * 
	 * @param showProgressDialog
	 * @param clearData
	 */
	private void getLawyerList(boolean showProgressDialog, final boolean clearData) {
		queryParams.put("longitude", location_sp.getString("longitude", ""));
		queryParams.put("latitude", location_sp.getString("latitude", ""));
		post(showProgressDialog, CommonData.REMOTE_REQUEST_URL_HTTP + "/if/lawyer/list", queryParams, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				LawyerBusinessBean lawyerBusinessBean = gson.fromJson(response, LawyerBusinessBean.class);
				if (handleRequestResult(lawyerBusinessBean)) {
					if (clearData) {
						lawyerBeans.clear();
					}
					lawyerBeans.addAll(lawyerBusinessBean.getData().getLstdata());
					lawyerListAdapter.notifyDataSetChanged();
				}
				
				mPtrFrameLayout.refreshComplete();
				
				BasePagedData<?> pagedData = lawyerBusinessBean.getData();
				boolean isEmpty = pagedData.getTotalrecord() == 0;
				boolean hasMore = pagedData.getCurrentpage() != pagedData.getTotalpage();
				boolean showLoadedFinishText = pagedData.getTotalrecord() > pagedData.getPagesize();
				myLoadMoreListViewContainer.loadMoreFinish(isEmpty, hasMore, showLoadedFinishText);
				
				CommonUtils.dismissProgressDialog();
			}

		}, VolleyWrapper.MAX_RETRY_NUM);
	}
	
}
