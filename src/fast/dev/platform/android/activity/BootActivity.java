package fast.dev.platform.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.tencent.bugly.crashreport.CrashReport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.account.LoginActivity;
import fast.dev.platform.android.activity.base.BaseActivity;
import in.srain.cube.views.DotView;

public class BootActivity extends BaseActivity {

	private ViewPager boot_pager;
	private DotView dotView;
	private int[] images = new int[] { R.drawable.boot_img_01, R.drawable.boot_img_02, R.drawable.boot_img_03, R.drawable.boot_img_04 };
	private List<ImageView> imageViews = new ArrayList<ImageView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * Bugly
		 */
		CrashReport.setUserSceneTag(getContext(), 11891);
		
		setContentView(R.layout.activity_boot);

		boot_pager = (ViewPager) findViewById(R.id.boot_pager);
		boot_pager.setAdapter(new BootPagerAdapter(getSupportFragmentManager()));
		boot_pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				dotView.setSelected(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
			
		});
		
		dotView = (DotView) findViewById(R.id.dot_view);
		dotView.setNum(images.length);
	}

	private class BootPagerAdapter extends FragmentPagerAdapter {

		public BootPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return new BootFragment(position);
		}

		@Override
		public int getCount() {
			return images.length;
		}

	}

	private class BootFragment extends Fragment {

		private int position;

		public BootFragment(int position) {
			this.position = position;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View root_view = inflater.inflate(R.layout.fragment_boot, container, false);
			ImageView imageView = (ImageView) root_view.findViewById(R.id.image);
//			imageView.setImageDrawable(ImageUtils.newOptimizedBitmapDrawable(getContext(), images[position]));
//			mImageLoader.displayImageWithoutPlaceholder(images[position], imageView);
			imageView.setImageResource(images[position]);
			imageViews.add(imageView);
			if (position == images.length - 1) {
				ImageView btn_start = (ImageView) root_view.findViewById(R.id.btn_start);
				btn_start.setVisibility(View.VISIBLE);
				btn_start.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						sys_sp.edit().putBoolean("been_booted", true).apply();
						startActivity(new Intent(getContext(), LoginActivity.class));
						finishActivity();
					}

				});
			}
			return root_view;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		for (ImageView imageView : imageViews) {
//			ImageUtils.releaseImageViewResouce(imageView);
		}
	}
	
}
