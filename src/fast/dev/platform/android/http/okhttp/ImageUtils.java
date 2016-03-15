package fast.dev.platform.android.http.okhttp;

import java.io.InputStream;
import java.lang.reflect.Field;

import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by zhy on 15/11/6.
 */
public class ImageUtils {
	
	/**
	 * ���InputStream��ȡͼƬʵ�ʵĿ�Ⱥ͸߶�
	 *
	 * @param imageStream
	 * @return
	 */
	public static ImageSize getImageSize(InputStream imageStream) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(imageStream, null, options);
		return new ImageSize(options.outWidth, options.outHeight);
	}

	public static class ImageSize {

		int width;
		int height;

		public ImageSize() {

		}

		public ImageSize(int width, int height) {
			this.width = width;
			this.height = height;
		}

		@Override
		public String toString() {
			return "ImageSize{" + "width=" + width + ", height=" + height + '}';
		}

	}

	public static int calculateInSampleSize(ImageSize srcSize, ImageSize targetSize) {
		// ԴͼƬ�Ŀ��
		int width = srcSize.width;
		int height = srcSize.height;
		int inSampleSize = 1;

		int reqWidth = targetSize.width;
		int reqHeight = targetSize.height;

		if (width > reqWidth && height > reqHeight) {
			// �����ʵ�ʿ�Ⱥ�Ŀ���ȵı���
			int widthRatio = Math.round((float) width / (float) reqWidth);
			int heightRatio = Math.round((float) height / (float) reqHeight);
			inSampleSize = Math.max(widthRatio, heightRatio);
		}
		return inSampleSize;
	}

	/**
	 * ���ImageView���ʵ���ѹ���Ŀ�͸�
	 *
	 * @param view
	 * @return
	 */
	public static ImageSize getImageViewSize(View view) {

		ImageSize imageSize = new ImageSize();

		imageSize.width = getExpectWidth(view);
		imageSize.height = getExpectHeight(view);

		return imageSize;
	}

	/**
	 * ���view�������ĸ߶�
	 *
	 * @param view
	 * @return
	 */
	private static int getExpectHeight(View view) {

		int height = 0;
		if (view == null)
			return 0;

		final ViewGroup.LayoutParams params = view.getLayoutParams();
		// �����WRAP_CONTENT����ʱͼƬ��û���أ�getWidth����Ч
		if (params != null && params.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
			height = view.getWidth(); // ���ʵ�ʵĿ��
		}
		if (height <= 0 && params != null) {
			height = params.height; // ��ò����ļ��е������Ŀ��
		}

		if (height <= 0) {
			height = getImageViewFieldValue(view, "mMaxHeight");// ������õ����Ŀ��
		}

		// ����Ȼ���û�л�ȡ��������У�ʹ����Ļ�Ŀ��
		if (height <= 0) {
			DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
			height = displayMetrics.heightPixels;
		}

		return height;
	}

	/**
	 * ���view�������Ŀ��
	 *
	 * @param view
	 * @return
	 */
	private static int getExpectWidth(View view) {
		int width = 0;
		if (view == null)
			return 0;

		final ViewGroup.LayoutParams params = view.getLayoutParams();
		// �����WRAP_CONTENT����ʱͼƬ��û���أ�getWidth����Ч
		if (params != null && params.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
			width = view.getWidth(); // ���ʵ�ʵĿ��
		}
		if (width <= 0 && params != null) {
			width = params.width; // ��ò����ļ��е������Ŀ��
		}

		if (width <= 0) {
			width = getImageViewFieldValue(view, "mMaxWidth");// ������õ����Ŀ��
		}
		// ����Ȼ���û�л�ȡ��������У�ʹ����Ļ�Ŀ��
		if (width <= 0) {
			DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
			width = displayMetrics.widthPixels;
		}

		return width;
	}

	/**
	 * ͨ�����ȡimageview��ĳ������ֵ
	 *
	 * @param object
	 * @param fieldName
	 * @return
	 */
	private static int getImageViewFieldValue(Object object, String fieldName) {
		int value = 0;
		try {
			Field field = ImageView.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			int fieldValue = field.getInt(object);
			if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
				value = fieldValue;
			}
		} catch (Exception e) {

		}
		return value;

	}

}
