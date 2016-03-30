package fast.dev.platform.android.imageloader;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.Context;
import android.widget.ImageView;
import fast.dev.platform.android.R;

public class UniversalImageLoader {

	private static UniversalImageLoader instance;
	private static ImageLoader mImageLoader;

	private UniversalImageLoader() {

	}

	public static UniversalImageLoader getInstance(Context context) {
		if (instance == null) {
			instance = new UniversalImageLoader();
			mImageLoader = ImageLoader.getInstance();
			if (!mImageLoader.isInited()) {
				DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
						.showImageForEmptyUri(R.drawable.no_page).showImageOnLoading(R.drawable.no_page)
						.showImageOnFail(R.drawable.no_page).build();
				ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
						.defaultDisplayImageOptions(options).build();
				mImageLoader.init(config);
			}
		}
		return instance;
	}

	public void displayImage(String uri, ImageView imageView) {
		mImageLoader.displayImage(uri, imageView);
	}

	public void displayImage(String uri, ImageView imageView, ImageLoadingListener imageLoadingListener) {
		mImageLoader.displayImage(uri, imageView, imageLoadingListener);
	}

	public void displayImageForAvatar(String uri, ImageView imageView) {
		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
				.showImageForEmptyUri(R.drawable.no_avatar).showImageOnLoading(R.drawable.no_avatar)
				.showImageOnFail(R.drawable.no_avatar).build();
		mImageLoader.displayImage(uri, imageView, options);
	}

	public void displayImageForAvatar(String uri, ImageView imageView, ImageLoadingListener imageLoadingListener) {
		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
				.showImageForEmptyUri(R.drawable.no_avatar).showImageOnLoading(R.drawable.no_avatar)
				.showImageOnFail(R.drawable.no_avatar).build();
		mImageLoader.displayImage(uri, imageView, options, imageLoadingListener);
	}

}
