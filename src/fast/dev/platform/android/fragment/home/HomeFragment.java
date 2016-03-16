package fast.dev.platform.android.fragment.home;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import fast.dev.platform.android.R;
import fast.dev.platform.android.fragment.base.BaseFragment;
import fast.dev.platform.android.imageloader.UniversalImageLoader;
import in.srain.cube.views.banner.BannerAdapter;
import in.srain.cube.views.banner.SliderBanner;

public class HomeFragment extends BaseFragment {

	private View progressBar;
	private SliderBanner sliderBanner;
	private View case_box;
	private View writ_box;
	private View enterprise_box;
	private View consult_box;
	private View legal_aid_box;
	
	private SharedPreferences entrust_sp;

	private SliderBannerAdapter mBannerAdapter = new SliderBannerAdapter();
	private String[] images = new String[0];
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		setTitleBar(rootView, "律师同行", true);

		initData();
		initViews(rootView);
		initListeners();

		String carousel_images = sys_sp.getString("carousel_images", "");
		if (!TextUtils.isEmpty(carousel_images)) {
			images = carousel_images.split(";");
		} else {
			images = new String[] {"none"};
		}
		setSliderBannerData();
		
		return rootView;
	}

	private void setSliderBannerData() {
		progressBar.setVisibility(View.GONE);
		sliderBanner.setVisibility(View.VISIBLE);
		mBannerAdapter.setData(images);
		mBannerAdapter.notifyDataSetChanged();
		sliderBanner.setDotNum(images.length);
		sliderBanner.beginPlay();
	}
	
	private void initData() {
		entrust_sp = getContext().getSharedPreferences("entrust", Context.MODE_PRIVATE);
	}

	private void initViews(View rootView) {
		progressBar = rootView.findViewById(R.id.progressBar);
		sliderBanner = (SliderBanner) rootView.findViewById(R.id.slider_banner);
		sliderBanner.setAdapter(mBannerAdapter);
		
		case_box = rootView.findViewById(R.id.case_box);
		writ_box = rootView.findViewById(R.id.writ_box);
		enterprise_box = rootView.findViewById(R.id.enterprise_box);
		consult_box = rootView.findViewById(R.id.consult_box);
		legal_aid_box = rootView.findViewById(R.id.legal_aid_box);
	}

	private void initListeners() {
		case_box.setOnClickListener(this);
		writ_box.setOnClickListener(this);
		enterprise_box.setOnClickListener(this);
		consult_box.setOnClickListener(this);
		legal_aid_box.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {/*
		switch (v.getId()) {
		case R.id.case_box:
			entrust_sp.edit().putString("entrustid", "").apply();
			startActivity(new Intent(getContext(), CaseEntrustActivity.class));
			break;
		case R.id.writ_box:
			startActivity(new Intent(getContext(), WritCustomThemeActivity.class));
			break;
		case R.id.enterprise_box:
			startActivity(new Intent(getContext(), EnterpriseServiceActivity.class));
			break;
		case R.id.consult_box:
			startActivity(new Intent(getContext(), ConsultListActivity.class));
			break;
		case R.id.legal_aid_box:
			startActivity(new Intent(getContext(), AddLegalAidStep01Activity.class));
			break;
		}
	*/}

	private class SliderBannerAdapter extends BannerAdapter {

		private String[] images;
		
		public void setData(String[] images) {
			this.images = images;
		}
		
		public String getItem(int position) {
			if (images.length == 0) {
				return null;
			}
			return images[getPositionForIndicator(position)];
		}

		@Override
		public int getPositionForIndicator(int position) {
			if (images.length == 0) {
				return 0;
			}
			return position % images.length;
		}

		@Override
		public View getView(LayoutInflater layoutInflater, int position) {
			final ImageView imageView = (ImageView) layoutInflater.inflate(R.layout.slider_banner_item, null);
			String image = getItem(position);
			if (!TextUtils.isEmpty(image)) {
				imageView.setAdjustViewBounds(false);
				/*imageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						startActivity(new Intent(getContext(), GameActivity.class));
					}
					
				});*/
				//imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				if ("none".equals(image)) {
					mImageLoader.displayImage(R.drawable.default_banner, imageView, R.drawable.loading);
					imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				} else {
					UniversalImageLoader.getInstance(getContext()).displayImage(image, imageView, new ImageLoadingListener() {
						
						@Override
						public void onLoadingStarted(String arg0, View arg1) {
							
						}
						
						@Override
						public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
							
						}
						
						@Override
						public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
							imageView.setScaleType(ScaleType.FIT_XY);
						}
						
						@Override
						public void onLoadingCancelled(String arg0, View arg1) {
							
						}
						
					});
					//mImageLoader.displayImage(image, imageView, R.drawable.no_page);
				}
			}
			return imageView;
		}

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

	}
	
}
