package fast.dev.platform.android.imageloader;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

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
						.build();
				ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
						.defaultDisplayImageOptions(options).build();
				mImageLoader.init(config);
			}
		}
		return instance;
	}

	public void displayImage(String uri, ImageView imageView) {
		if (!TextUtils.isEmpty(uri)) {
			mImageLoader.displayImage(uri, imageView);
		}
	}

	public void displayImage(String uri, ImageView imageView, ImageLoadingListener imageLoadingListener) {
		if (!TextUtils.isEmpty(uri)) {
			mImageLoader.displayImage(uri, imageView, imageLoadingListener);
		}
	}

}
