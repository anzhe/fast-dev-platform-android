package fast.dev.platform.android.fragment.find;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.android.volley.Response.Listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.LawyerDetailActivity;
import fast.dev.platform.android.bean.LawyerBean;
import fast.dev.platform.android.bean.business.LawyerBusinessBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.fragment.base.BaseFragment;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.imageloader.UniversalImageLoader;
import fast.dev.platform.android.util.CommonUtils;
import view.RecyclerViewDivider;

public class FindFragment extends BaseFragment {

	private int page_no = 1;

	private List<LawyerBean> lawyerBeans = new ArrayList<LawyerBean>();

	private HashMap<String, String> queryParams = new HashMap<String, String>();

	private MyRecyclerViewAdapter myRecyclerViewAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.fragment_find, container, false);
		setupRecyclerView(rv);
		return rv;
	}

	@Override
	public void onResume() {
		super.onResume();

		queryParams.put("pageno", "1");
		queryParams.put("pagesize", (page_no * 100) + "");
		getLawyerList(false, true);
	}

	private void setupRecyclerView(RecyclerView recyclerView) {
		recyclerView.addItemDecoration(new RecyclerViewDivider(getContext(), LinearLayoutManager.VERTICAL));
		recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
		myRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity());
		recyclerView.setAdapter(myRecyclerViewAdapter);
	}

	public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

		private final TypedValue mTypedValue = new TypedValue();
		private int mBackground;

		public class ViewHolder extends RecyclerView.ViewHolder {

			public final View mView;
			public final ImageView mImageView;
			public final TextView mTextView;

			public ViewHolder(View view) {
				super(view);
				mView = view;
				mImageView = (ImageView) view.findViewById(R.id.avatar);
				mTextView = (TextView) view.findViewById(android.R.id.text1);
			}

			@Override
			public String toString() {
				return super.toString() + " '" + mTextView.getText();
			}

		}

		public MyRecyclerViewAdapter(Context context) {
			context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
			mBackground = mTypedValue.resourceId;
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
			view.setBackgroundResource(mBackground);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			final LawyerBean selectedLawyer = lawyerBeans.get(position);
			holder.mTextView.setText(selectedLawyer.getREL_NAME());

			holder.mView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Context context = v.getContext();
					Intent intent = new Intent(context, LawyerDetailActivity.class);
					intent.putExtra(LawyerDetailActivity.EXTRA_NAME, selectedLawyer);
					context.startActivity(intent);
				}

			});

			UniversalImageLoader.getInstance(getContext()).displayImageForAvatar(selectedLawyer.getPHOTO(),
					holder.mImageView);
		}

		@Override
		public int getItemCount() {
			return lawyerBeans.size();
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
		Listener<String> listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				LawyerBusinessBean lawyerBusinessBean = gson.fromJson(response, LawyerBusinessBean.class);
				if (handleRequestResult(lawyerBusinessBean)) {
					if (clearData) {
						lawyerBeans.clear();
					}
					lawyerBeans.addAll(lawyerBusinessBean.getData().getLstdata());
					myRecyclerViewAdapter.notifyDataSetChanged();
				}

				CommonUtils.dismissProgressDialog();
			}

		};
		String url = CommonData.REMOTE_REQUEST_URL_HTTP + "/if/lawyer/list";
		post(showProgressDialog, url, queryParams, listener, VolleyWrapper.MAX_RETRY_NUM);
	}

}
