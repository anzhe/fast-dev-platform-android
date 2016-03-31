package fast.dev.platform.android.fragment.find;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.android.volley.Response.Listener;
import com.bumptech.glide.Glide;

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
import fast.dev.platform.android.activity.CheeseDetailActivity;
import fast.dev.platform.android.bean.LawyerBean;
import fast.dev.platform.android.bean.business.LawyerBusinessBean;
import fast.dev.platform.android.constant.CommonData;
import fast.dev.platform.android.fragment.base.BaseFragment;
import fast.dev.platform.android.http.volley.VolleyWrapper;
import fast.dev.platform.android.model.Cheeses;
import fast.dev.platform.android.util.CommonUtils;
import fast.dev.platform.android.util.ToastUtils;

public class FindFragment extends BaseFragment {

	private int page_no = 1;

	private List<LawyerBean> lawyerBeans = new ArrayList<LawyerBean>();

	private HashMap<String, String> queryParams = new HashMap<String, String>();

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
		queryParams.put("pagesize", (page_no * 10) + "");
		getLawyerList(false, true);
	}

	private void setupRecyclerView(RecyclerView recyclerView) {
		recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
		recyclerView.setAdapter(
				new SimpleStringRecyclerViewAdapter(getActivity(), getRandomSublist(Cheeses.sCheeseStrings, 30)));
	}

	private List<String> getRandomSublist(String[] array, int amount) {
		ArrayList<String> list = new ArrayList<>(amount);
		Random random = new Random();
		while (list.size() < amount) {
			list.add(array[random.nextInt(array.length)]);
		}
		return list;
	}

	public static class SimpleStringRecyclerViewAdapter
			extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

		private final TypedValue mTypedValue = new TypedValue();
		private int mBackground;
		private List<String> mValues;

		public static class ViewHolder extends RecyclerView.ViewHolder {

			public String mBoundString;

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

		public String getValueAt(int position) {
			return mValues.get(position);
		}

		public SimpleStringRecyclerViewAdapter(Context context, List<String> items) {
			context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
			mBackground = mTypedValue.resourceId;
			mValues = items;
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
			view.setBackgroundResource(mBackground);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			holder.mBoundString = mValues.get(position);
			holder.mTextView.setText(mValues.get(position));

			holder.mView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Context context = v.getContext();
					Intent intent = new Intent(context, CheeseDetailActivity.class);
					intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.mBoundString);
					context.startActivity(intent);
				}

			});

			Glide.with(holder.mImageView.getContext()).load(Cheeses.getRandomCheeseDrawable()).fitCenter()
					.into(holder.mImageView);
		}

		@Override
		public int getItemCount() {
			return mValues.size();
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
				ToastUtils.showLong(getContext(), response);
				LawyerBusinessBean lawyerBusinessBean = gson.fromJson(response, LawyerBusinessBean.class);
				if (handleRequestResult(lawyerBusinessBean)) {
					if (clearData) {
						lawyerBeans.clear();
					}
					lawyerBeans.addAll(lawyerBusinessBean.getData().getLstdata());
				}

				CommonUtils.dismissProgressDialog();
			}

		};
		String url = CommonData.REMOTE_REQUEST_URL_HTTP + "/if/lawyer/list";
		post(showProgressDialog, url, queryParams, listener, VolleyWrapper.MAX_RETRY_NUM);
	}

}
