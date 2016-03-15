package fast.dev.platform.android.imageloader;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import fast.dev.platform.android.R;

public class PicassoImageLoader {

	private Context mContext;

	public PicassoImageLoader(Context context) {
		this.mContext = context;
	}

	public void displayImage(String uri, ImageView imageView) {
		if (!TextUtils.isEmpty(uri)) {
			Picasso.with(mContext).load(uri).placeholder(R.drawable.no_page).error(R.drawable.no_page).into(imageView);
		}
	}

	public void displayImage(String uri, ImageView imageView, int placeholder) {
		if (!TextUtils.isEmpty(uri)) {
			Picasso.with(mContext).load(uri).placeholder(placeholder).error(R.drawable.no_page).into(imageView);
		}
	}

	public void displayImageForAvatar(String uri, ImageView imageView) {
		if (!TextUtils.isEmpty(uri)) {
			Picasso.with(mContext).load(uri).placeholder(R.drawable.no_avatar).error(R.drawable.no_avatar)
					.into(imageView);
		}
	}

	public void displayImageWithoutPlaceholder(String uri, ImageView imageView) {
		if (!TextUtils.isEmpty(uri)) {
			Picasso.with(mContext).load(uri).error(R.drawable.no_page).into(imageView);
		}
	}

	public void displayImage(int resourceId, ImageView imageView) {
		Picasso.with(mContext).load(resourceId).placeholder(R.drawable.no_page).error(R.drawable.no_page)
				.into(imageView);
	}

	public void displayImage(int resourceId, ImageView imageView, int placeholder) {
		Picasso.with(mContext).load(resourceId).placeholder(placeholder).error(R.drawable.no_page).into(imageView);
	}

	public void displayImageWithoutPlaceholder(int resourceId, ImageView imageView) {
		Picasso.with(mContext).load(resourceId).error(R.drawable.no_page).into(imageView);
	}

}
